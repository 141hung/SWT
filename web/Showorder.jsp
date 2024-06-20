<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                padding: 20px;
            }
            h2 {
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #fff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            th, td {
                padding: 12px 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #f2f2f2;
                color: #333;
            }
            tr:hover {
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>
         <a href="cart" class="btn btn-secondary custom-btn">Back</a>
        <h2>Order Details</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cartDetails}" var="cd">
                    <tr>
                        <td>${cd.productid}</td>
                        <td>${cd.quantity}</td> <!-- Thêm dấu } vào cuối biểu thức -->
                        <td>${cd.price}</td> <!-- Thêm dấu } vào cuối biểu thức -->
                        <td>${cd.quantity * cd.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
