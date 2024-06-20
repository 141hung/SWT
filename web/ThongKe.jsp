<%-- 
    Document   : ThongKe
    Created on : Mar 14, 2024, 4:43:44 AM
    Author     : admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thống kê</title>
    </head>
    <body>
        <h1>Top Selling Products</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Total Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${topSellingProducts}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.totalQuantity}</td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
    </body>
</html>



