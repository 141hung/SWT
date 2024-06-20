/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.CartDetail;
import model.Category;
import model.Item;
import model.Order;
import model.Product;

/**
 *
 * @author phamd
 */
public class ProductDAO extends DBContext {

    //lấy tất cả các sản phẩm từ data
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //lấy các loại product từ cid 
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //lấy sản phẩm mới nhất thêm vào
    public Product getLast() {
        String sql = "select top 1* from product order by productid desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Product> top3Products = new ArrayList<>();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //lấy các sản phẩm bởi cid từ bảng product  
    public List<Product> getAllProductByCategory(int categoryid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product where categoryid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //lấy tất cả thông tin sản phẩm bởi id
    public Product getProductByID(int id) {
        String sql = "select * from product where productid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //Tìm kiếm sản phâmr bởi tên
    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product where [name] like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //
    public List<Product> searchByCategoryID(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where categoryid like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //đăng kí
    public Account Login(String name, String pass) {
        String sql = "SELECT * FROM Account WHERE [name] = ? AND pass =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("accountid");
                String username = rs.getString("name");
                String password = rs.getString("pass");
                int isAdmin = rs.getInt("isAdmin");
                return new Account(id, name, pass, isAdmin);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //check xem account có tồn tại không  
    public Account CheckAccountExits(String name) {
        String sql = "SELECT * FROM Account WHERE [name] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                int isAdmin = rs.getInt(4);
                return new Account(id, name, password, isAdmin);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //hàm cho người dùng đăng kí
    public void singup(String name, String pass, String role) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([name]\n"
                + "           ,[pass]\n"
                + "           ,[isAdmin])\n"
                + "     VALUES (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, pass);
            st.setInt(3, role.equals("admin") ? 1 : 0);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //lấy sản phẩm bởi accountid người bán 
    public List<Product> getAllProductByAccountID(int accountid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product where accountid = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //xóa sản phẩm
    public void DeleteProduct(int productid) {
        String sql = "DELETE FROM Product WHERE productid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //thêm sản phẩm 
    public void AddProduct(String name, String image, double price, String title, String description, int quanlity, int categoryid, int accountid) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([name]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[title]\n"
                + "           ,[description]\n"
                + "           ,[quanlity]\n"
                + "           ,[categoryid]\n"
                + "           ,[accountid])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, image);
            st.setDouble(3, price);
            st.setString(4, title);
            st.setString(5, description);
            st.setInt(6, quanlity);
            st.setInt(7, categoryid);
            st.setInt(8, accountid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //chỉnh sửa sản phẩm 
    public void EditProduct(String name, String image, double price, String title, String description, int quanlity, int categoryid, int productid) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [name] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[quanlity] = ?\n"
                + "      ,[categoryid] = ?\n"
                + " WHERE productid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, image);
            st.setDouble(3, price);
            st.setString(4, title);
            st.setString(5, description);
            st.setInt(6, quanlity);
            st.setInt(7, categoryid);
            st.setInt(8, productid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //
    public Account getAccount(String name, String pass) {
        String sql = "select * from Account where accountid= ? and pass = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //getListByPage 
    public List<Product> getListByPage(List<Product> products, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(products.get(i));
        }
        return arr;
    }

    //
    public void addOrder(Account a, Order order) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            //add bảng Cart
            String sql = "INSERT INTO [dbo].[Cart]\n"
                    + "           ([accountid]\n"
                    + "           ,[date]\n"
                    + "           ,[totalmoney])\n"
                    + "     VALUES (?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getAccountid());
            st.setString(2, date);
            st.setDouble(3, order.getTotalMoney());
            st.executeUpdate();
            //lấy id của cart vua add
            String sql1 = "select top 1 cartid  from Cart order by cartid desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add vào bảng cartline ?
            if (rs.next()) {
                int oid = rs.getInt("cartid");
                for (Item i : order.getItems()) {
                    String sql2 = "INSERT INTO [dbo].[Cartline]\n"
                            + "           ([cartid]\n"
                            + "           ,[productid]\n"
                            + "           ,[price]\n"
                            + "           ,[quanlity])\n"
                            + "     VALUES(?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getId());
                    st2.setDouble(3, i.getPrice());
                    st2.setInt(4, i.getQuanlity());
                    st2.executeUpdate();
                }
            }
            //cập nhật lại số lượng sản phẩm 
            String sql3 = "UPDATE [dbo].[Product]\n"
                    + "   SET [quanlity] = quanlity - ?\n"
                    + " WHERE productid = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : order.getItems()) {
                st3.setInt(1, i.getQuanlity());
                st3.setInt(2, i.getProduct().getId());
                st3.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }

    //check order
    public List<Cart> getOrderByAccountID(int accountid) {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT * FROM Cart where accountid =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart c = new Cart(rs.getInt("cartid"), rs.getInt("accountid"), rs.getString("date"), rs.getDouble("totalmoney"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //check orderdetail by orderid
    public List<CartDetail> getOrderDetailByCartID(int cartid) {
        List<CartDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM Cartline  where cartid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartDetail c = new CartDetail(rs.getInt("cartid"), rs.getInt("productid"), rs.getInt("quanlity"), rs.getDouble("price"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //chance pass
    public void changePassword(String pass, String name) {
        String sql = "UPDATE [dbo].[Account] SET [pass] = ? WHERE [name] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, name);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //lấy Category by id
    public Category getCategoryByID(int categoryid) {
        String sql = "select * from Category where categoryid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //edit category
    public void EditCategory(int id, String name) {
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [categoryname] = ?\n"
                + " WHERE categoryid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // thêm category
    public void AddCategory(int id, String name) {
        String sql = "INSERT INTO [dbo].[Category]\n"
                + "           ([categoryid]\n"
                + "           ,[categoryname])\n"
                + "     VALUES(?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, name);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //check category exits
    public Category checkCategoryExist(int id) {
        String sql = "SELECT * FROM Category WHERE categoryid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                return new Category(cid, cname);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
//delete category

    public void DeleteCategory(int categoryid) {
        String sql = "DELETE FROM Category WHERE categoryid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //thongke
    public List<Product> topSellingProductsForAdmin(int adminAccountId) {
        List<Product> topSellingProducts = new ArrayList<>();
        String sql = "SELECT TOP 5 "
                + "P.productid, "
                + "P.name AS product_name, "
                + "C.categoryname, "
                + "SUM(CL.quanlity) AS total_quantity "
                + "FROM "
                + "Product AS P "
                + "INNER JOIN "
                + "Category AS C ON P.categoryid = C.categoryid "
                + "INNER JOIN "
                + "Cartline AS CL ON P.productid = CL.productid "
                + "INNER JOIN "
                + "Cart AS CA ON CL.cartid = CA.cartid "
                + "INNER JOIN "
                + "Account AS A ON CA.accountid = A.accountid "
                + "WHERE "
                + "A.isAdmin = 1 AND A.accountid = ? "
                + "GROUP BY "
                + "P.productid, P.name, C.categoryname "
                + "ORDER BY "
                + "SUM(CL.quanlity) DESC";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, adminAccountId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productid");
                String productName = rs.getString("productname");
                int totalQuantity = rs.getInt("total_quantity");

                // Create Product object and add it to the list
                Product product = new Product(productId, productName, null, 0.0, null, null, totalQuantity);
                topSellingProducts.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return topSellingProducts;
    }

}
