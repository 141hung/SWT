<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--        <form action="" name="f" method="post">-->
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="product">Home</a></li>
                                        <li class="breadcrumb-item"><a href="product">Category</a></li>
                                        <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                <jsp:include page="Left.jsp"></jsp:include>
                    <div class="col-sm-9">
                        <div class="container">
                            <div class="card">
                                <div class="row">
                                    <aside class="col-sm-5 border-right">
                                        <article class="gallery-wrap"> 
                                            <div class="img-big-wrap">
                                                <div> <a href="#"><img src="${detail.image}"></a></div>
                                        </div> 
                                    </article> 
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>
                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">US $</span><span class="num">${detail.price*2}</span>
                                            </span> 
                                        </p>
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>${detail.description} </p></dd>
                                        </dl>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <input type="text"  class="form-control form-control-sm" style="width:70px;" value="${detail.quanlity}">
                                                        </input>
                                                    </dd>
                                                </dl>
                                            </div> 
                                        </div> 
                                        <hr>
                                        <form action="buy" method="post">
                                            <input type="hidden" name="id" value="${detail.id}">
                                            <c:choose>
                                                <c:when test="${detail.quanlity <= 0}">
                                                    <button type="button" class="btn btn-danger btn-block" disabled>Hết hàng</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="form-group">
                                                        <label for="quantity">Số lượng:</label>
                                                        <input type="number" id="quantity" name="number" value="1" min="1" max="${detail.quanlity}" class="form-control">
                                                    </div>
                                                    <button type="submit" class="btn btn-success btn-block">Mua hàng</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>

                                    </article> 
                                </aside>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>   
        <!--        </form>-->
    </body>
</html>