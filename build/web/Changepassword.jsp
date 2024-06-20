<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Change Password</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                width: 300px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                font-weight: bold;
                color: #555;
            }

            .form-group input[type="password"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            button[type="submit"] {
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            button[type="submit"]:hover {
                background-color: #0056b3;
            }
            a{
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                background-color: #0056b3;
            }
        </style>
    </head>
    <body >
        <div class="container">
            <%-- Kiểm tra nếu có thông báo lỗi trong session thì hiển thị --%>
            <% 
                String error = (String) session.getAttribute("error");
                if (error != null && !error.isEmpty()) { 
            %>
            <div style="color: red; margin-bottom: 10px;"><%= error %></div>
            <% 
                    // Xóa thông báo lỗi khỏi session sau khi hiển thị
                    session.removeAttribute("error");
                } 
            %>
            <script>
                function validateForm() {
                    var newPassword = document.getElementById("new-password").value;
                    var confirmNewPassword = document.getElementById("confirm-new-password").value;
                    if (newPassword !== confirmNewPassword) {
                        alert("New password and confirm new password must match.");
                        return false;
                    }
                    return true;
                }
            </script>


            <form action="changepassword" method="post">
                <input type="hidden" name="username" value="<%= session.getAttribute("name") %>">
                <div class="form-group">
                    <label for="old-password">Old Password:</label>
                    <input type="password" id="old-password" name="oldPassword" required>
                </div>
                <div class="form-group">
                    <label for="new-password">New Password:</label>
                    <input type="password" id="new-password" name="newPassword" pattern="^\S+$" title="Password cannot contain whitespace" required>
                </div>
                <div class="form-group">
                    <label for="confirm-new-password">Confirm New Password:</label>
                    <input type="password" id="confirm-new-password" name="confirmNewPassword" pattern="^\S+$" title="Password cannot contain whitespace" required>
                </div>


                <button type="submit">Change Password</button>
                <a href="product">Back</a>
            </form>
        </div>
    </body>
</html>
