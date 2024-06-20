<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Profile</title>
        <link href="css/profile.css" rel="stylesheet" type="text/css">
        <style>
            @import url("https://fonts.googleapis.com/css2?family=Baloo+Paaji+2:wght@400;500&display=swap");

            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh; /* Chiều cao 100% của viewport */
                background-color: #f5f5f5;
                font-family: 'Baloo Paaji 2', cursive;
            }


            .card {
                background-color: #222831;
                height: 39rem;
                width: 50rem;
                border-radius: 5px;
                display: flex;
                flex-direction: column;
                align-items: center;
                color: white;
            }

            .card__name {
                margin-top: 15px;
                font-size: 1.5em;
            }

            .card__image {
                height: 300px;
                width: 300px;
                border-radius: 50%;
                border: 5px solid #272133;
                margin-top: 20px;
                box-shadow: 0 10px 50px rgba(235, 25, 110, 1);
            }


            .draw-border {
                box-shadow: inset 0 0 0 4px #58cdd1;
                color: #58afd1;
                -webkit-transition: color 0.25s 0.0833333333s;
                transition: color 0.25s 0.0833333333s;
                position: relative;
            }

            .draw-border::before,
            .draw-border::after {
                border: 0 solid transparent;
                box-sizing: border-box;
                content: '';
                pointer-events: none;
                position: absolute;
                width: 0rem;
                height: 0;
                bottom: 0;
                right: 0;
            }

            .draw-border::before {
                border-bottom-width: 4px;
                border-left-width: 4px;
            }

            .draw-border::after {
                border-top-width: 4px;
                border-right-width: 4px;
            }

            .draw-border:hover {
                color: #ffe593;
            }

            .draw-border:hover::before,
            .draw-border:hover::after {
                border-color: #eb196e;
                -webkit-transition: border-color 0s, width 0.25s, height 0.25s;
                transition: border-color 0s, width 0.25s, height 0.25s;
                width: 100%;
                height: 100%;
            }

            .draw-border:hover::before {
                -webkit-transition-delay: 0s, 0s, 0.25s;
                transition-delay: 0s, 0s, 0.25s;
            }

            .draw-border:hover::after {
                -webkit-transition-delay: 0s, 0.25s, 0s;
                transition-delay: 0s, 0.25s, 0s;
            }

            .btn {
                background: none;
                border: none;
                cursor: pointer;
                line-height: 1.5;
                font: 700 1.2rem 'Roboto Slab', sans-serif;
                padding: 0.75em 2em;
                letter-spacing: 0.05rem;
                margin: 1em;
                width: 13rem;
            }

            .btn:focus {
                outline: 2px dotted #55d7dc;
            }


            .social-icons {
                padding: 0;
                list-style: none;
                margin: 1em;
            }

            .social-icons li {
                display: inline-block;
                margin: 0.15em;
                position: relative;
                font-size: 1em;
            }

            .social-icons i {
                color: #fff;
                position: absolute;
                top: 0.95em;
                left: 0.96em;
                transition: all 265ms ease-out;
            }

            .social-icons a {
                display: inline-block;
            }

            .social-icons a:before {
                transform: scale(1);
                -ms-transform: scale(1);
                -webkit-transform: scale(1);
                content: " ";
                width: 45px;
                height: 45px;
                border-radius: 100%;
                display: block;
                background: linear-gradient(45deg, #ff003c, #c648c8);
                transition: all 265ms ease-out;
            }

            .social-icons a:hover:before {
                transform: scale(0);
                transition: all 265ms ease-in;
            }

            .social-icons a:hover i {
                transform: scale(2.2);
                -ms-transform: scale(2.2);
                -webkit-transform: scale(2.2);
                color: #ff003c;
                background: -webkit-linear-gradient(45deg, #ff003c, #c648c8);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                transition: all 265ms ease-in;
            }

            .grid-container {
                display: grid;
                grid-template-columns: 1fr 1fr;
                grid-gap: 20px;
                font-size: 1.2em;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <img src="data:image/jpeg;base64,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" alt="Person" class="card__image">
                <p class="card__name"><%= session.getAttribute("name") %></p>
                <div class="profile-info">
                    <div><strong>Password:</strong> <%= session.getAttribute("password") %></div>
                    <div><strong>Account ID:</strong> <%= session.getAttribute("accountid") %></div>
                </div>
                <ul class="social-icons">
                    <li><a href="Changepassword.jsp?username=<%= session.getAttribute("name") %>" class="change-password-link"><i class="fa fa-instagram">Edit</i></a></li>
                    <li><a href="product" class="back-link"><i class="fa fa-twitter">Back</i></a></li>
                </ul>
                <button class="btn draw-border">Follow</button>
                <button class="btn draw-border">Message</button>
            </div>
        </div>

    </body>
</html>
