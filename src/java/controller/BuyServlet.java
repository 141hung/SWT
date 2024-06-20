/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Item;
import model.Order;
import model.Product;

/**
 *
 * @author phamd
 */
public class BuyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productData = new ProductDAO();
        List<Product> products = productData.getAllProduct();
        int id = Integer.parseInt(request.getParameter("id"));
        int requestedQuantity = Integer.parseInt(request.getParameter("number"));
        int availableQuantity = productData.getProductByID(id).getQuanlity();
//        if (requestedQuantity > availableQuantity) {
//            // For example, you can redirect the user to an error page
//            response.sendRedirect("error.jsp");
//            return;
//        }
        // Proceed with adding the product to the cart
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng đến trang Login.jsp
            response.sendRedirect("Login.jsp");
            return;
        } else {
            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null && arr.length > 0) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }

            if (txt.isEmpty()) {
                txt = id + ":" + requestedQuantity;
            } else {
                txt = txt + "/" + id + ":" + requestedQuantity; // Utilisation du point-virgule comme séparateur
            }
            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
            response.sendRedirect("product");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
