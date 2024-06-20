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
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;
import model.Category;
import model.Item;
import model.Order;
import model.Product;

/**
 *
 * @author phamd
 */
public class ProductServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        ProductDAO productData = new ProductDAO();
        List<Product> products = productData.getAllProduct();
        List<Category> categories = productData.getAllCategory();
        String sortType = request.getParameter("sort");
        if (sortType != null && !sortType.isEmpty()) {
            if (sortType.equals("asc")) {
                Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
            } else if (sortType.equals("desc")) {
                Collections.sort(products, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
            }
        }
        //order
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Order order = new Order(txt, products);
        List<Item> listiItem = order.getItems();
        int n;
        if (listiItem != null) {
            n = listiItem.size();
        } else {
            n = 0;
        }
        //end order
        //Phân trang 
        int page, numperpage = 8;
        int size = products.size();
        int num = (int) Math.ceil((double) size / numperpage); // Số trang, làm tròn lên
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start = (page - 1) * numperpage;
        int end = Math.min(page * numperpage, size);
        List<Product> products1 = productData.getListByPage(products, start, end);
        //end phân trang 
        Product last = productData.getLast();
        //truyền số lượng sp nên cart khi ấn add to cart trên Home.jsp
        request.setAttribute("size", n);
//        request.getRequestDispatcher("Menu.jsp").forward(request, response);
        //
        request.setAttribute("listP", products1); // Truyền danh sách sản phẩm đã phân trang
        request.setAttribute("listC", categories);
        request.setAttribute("p", last);

        // Chuyền num để biết có bn trang
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        //chuyền size lên Menu.jsp
//        request.getRequestDispatcher("Menu.jsp").forward(request, response);
        //
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
        processRequest(request, response);
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
