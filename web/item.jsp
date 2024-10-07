<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .feedback-section {
        margin-top: 20px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;
    }

    .feedback-item {
        margin-bottom: 15px;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #fff;
    }

    .feedback-item h2 {
        color: #007bff;
        font-size: 18px;
        margin-bottom: 10px;
    }

    .feedback-item p {
        font-size: 14px;
        color: #555;
        margin-bottom: 5px;
    }

    .btn-edit, .btn-delete {
        width: 100%;
        background-color: #007bff;
        color: #fff;
        border: none;
        padding: 5px 10px;
        margin-right: 5px;
        border-radius: 3px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .btn-edit:hover, .btn-delete:hover {
        background-color: #0056b3;
    }

    .new-feedback {
        margin-top: 20px;
        padding: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #fff;
    }

    .new-feedback h2 {
        color: #007bff;
        font-size: 18px;
        margin-bottom: 10px;
    }

    .new-feedback textarea {
        width: 100%;
        max-width: 100%;
        min-width: 100%;
        height: 100px;
        padding: 10px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    .new-feedback input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        padding: 8px 15px;
        font-size: 14px;
        border-radius: 3px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .new-feedback input[type="submit"]:hover {
        background-color: #0056b3;
    }
    .new-feedback h2{
        font-size: 30px;
    }
</style>

</style>
<html>
    <head>
        <title>KuongStore.com | Item</title>
        <%@include file="link.jsp" %>
    </head>
    <body>
        <c:set var="ac" value="${sessionScope.account}"/>
        <jsp:useBean id="cate" class="dao.CategoryDAO"/>
        <jsp:useBean id="pub" class="dao.ProductDAO"/>
        <c:if test="${not empty account}">
            <%@include file="logoutHeader.jsp" %>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginHeader.jsp" %>
        </c:if>
        <div class="banner-top" style="background-color: gray">
            <div class="container">
                <h3>Thông tin sản phẩm</h3>
                <h4><a href="start">Trang chủ</a><label>/</label>Sản phẩm</h4>
                <div class="clearfix"> </div>
            </div>
        </div>

        <div class="container item_info">
            <c:set var="b" value="${requestScope.book}"/>
            <div class="row">
                <div class="col-md-6" style="max-width: 550px;">
                    <img src="${b.imgLink}" class="img-responsive item_img" alt=""> 
                </div>

                <div class="col-md-6" style="margin-top: 15px; width: 610px;">
                    <form id="form" name="f" action="" method="post">
                        <h1 style="color: red">${b.title}</h1><br>
                        <h2>Nhà xuất bản: ${b.publisher}</h2><br>
                        <h2>Kho: ${b.quantity}</h2>
                        <div class="mid-2">
                            <h2>Đơn giá: </h2>
                            <h2><p><label>${b.price*1.05}</label><em class="item_price">${b.price}VND</em></p></h2>
                            <input type="hidden" value="${b.price}" name="price" id="price">
                            <input type="hidden" value="${b.id}" name="id" id="id">
                            <div class="clearfix"></div>
                            <div class="clearfix"></div>
                        </div>
                        <label for="quantity"><h2>Số lượng: </h2></label>
                        <div class="item_quan">
                            <button style="margin-right: 5px;" type="button" onclick="decrease()">-</button>
                            <input type="text" value="1" name="quantity" id="quantity">
                            <button style="margin-left: 5px;" type="button" onclick="increase()">+</button>
                        </div><br>

                        <div class="col-md-3" style="width: max-content;">
                            <input type="submit" onclick="item('${b.id}')" class="btn btn-success" value="Thêm vào giỏ hàng">   
                            <input type="submit" onclick="buy('${b.id}')" class="btn btn-success"  value="Mua ngay">

                        </div>       
                        <%
                        String err = (String) session.getAttribute("err");
                        if (err != null) {
                            session.removeAttribute("err");
                        %>
                        <div style="color: red"><%= err %></div>
                        <meta http-equiv="refresh" content="3;url=addMoney">
                        <%
                            }
                        %>

                    </form> 
                </div>    
            </div>

            <!-- Feedback Section -->
            <div class="banner-top" style="background-color: gray">
                <div class="container">
                    <h3>Đánh giá từ người dùng</h3>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <!-- Feedback Section -->
            <div class="feedback-section">
                <c:forEach var="feedback" items="${requestScope.feedbackList}">
                    <div class="feedback-item">         
                        <h2>
                            <span>${feedback.userName}:</span> 
                            <span>${feedback.feedback}</span>
                        </h2>
                        <p>Đã phản hồi vào ${feedback.formattedDate}</p>
                        <c:if test="${feedback.accountID == ac.id}">
                            <!--                            <form action="editFeedback" method="get" style="display: inline;">
                                                            <input type="hidden" name="feedbackId" value="${feedback.feedbackID}">
                                                            <button class="btn-edit" type="submit">Sửa</button>
                                                        </form>-->
                            <form action="updateFeedback" method="post">
                                <input type="hidden" name="accountId" value="${ac.id}">
                                <input type="hidden" name="bookId" value="${b.id}">
                                <div class="form-group">
                                    <label for="feedbackText">Nội dung đánh giá:</label>
                                    <textarea class="form-control" id="feedbackText" name="feedbackText" rows="4">${feedback.feedback}</textarea>
                                </div>
                                <button type="submit" class="btn btn-success">Lưu đánh giá</button>
                            </form>

                            <form action="deleteFb" method="post" style="display: inline;">
                                <input type="hidden" name="feedbackId" value="${feedback.feedbackID}">
                                <input type="hidden" name="bid" value="${b.id}">
                                <button class="btn btn-danger" type="submit">Xóa đánh giá</button>
                            </form>
                        </c:if>
                    </div>
                </c:forEach>
            </div>

            <!-- End Feedback Section -->


        </div>

        <br><br><br><br><br><br><!-- <br -->
        <div class="spec">
            <h3>Có thể bạn sẽ thích</h3>
            <div class="ser-t">
                <b></b>
                <span><i></i></span>
                <b class="line"></b>
            </div>
        </div>
        <div class="con-w3l">
            <c:forEach items="${requestScope.listRelatedBook}" var="lrb">
                <div class="col-md-3 m-wthree">
                    <a href="<%=request.getContextPath()%>/item?id=${lrb.id}" class="offer-img">
                        <img src="${lrb.imgLink}" class="img-responsive" alt="">
                    </a>
                    <div class="mid-1">
                        <div class="women">
                            <h6><a href="<%=request.getContextPath()%>/item?id=${lrb.id}">${lrb.title}</a></h6>							
                        </div>
                        <div class="mid-2">
                            <p ><label>${lrb.price*1.05}</label><em class="item_price">${lrb.price}VND</em></p>

                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>                          
            <div class="clearfix"></div>
        </div>

        <br>
        <br>
        <br>
        <br>
        <c:if test="${not empty account}">
            <%@include file="logoutFooter.jsp" %>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginFooter.jsp" %>
        </c:if>
        <script>
            function decrease() {
                var quantity = document.getElementById("quantity");
                if (quantity.value > 1) {
                    quantity.value--;
                }
            }

            function increase() {
                var quantity = document.getElementById("quantity");
                if (quantity.value < ${b.quantity}) {
                    quantity.value++;
                }
            }

            const input = document.getElementById("quantity");
            input.addEventListener("input", function () { //Khong cho nhap chu
                this.value = this.value.replace(/[^0-9]/g, '');
            });

            function feedback(id) {
                if (${not empty account}) {
                    document.f.action = "feedback?id=" + id;
                    document.f.submit();
                } else {
                    alert("Bạn cần đăng nhập để thực hiện tác vụ này!!");
                }
            }

            function buy(id) {
                if (${not empty account}) {
                    if (quantity.value > ${b.quantity}) {
                        alert("Số lượng sản phẩm vượt quá giới hạn cho phép! Xin bạn nhập lại số lượng!");
                        var form = document.getElementById("form");
                        form.onsubmit = function () {
                            return false;
                        };
                    } else {
                        alert("Xác nhận mua sản phầm này!");
                        document.f.action = "buy?id=" + id;
                        document.f.submit();
                    }
                } else {
                    alert("Bạn cần đăng nhập để thực hiện tác vụ này!!");
                }
            }

            function item(id) {
                if (${not empty account}) {
                    if (quantity.value > ${b.quantity}) {
                        alert("Số lượng sản phẩm vượt quá giới hạn cho phép! Xin bạn nhập lại số lượng!");
                        var form = document.getElementById("form");
                        form.onsubmit = function () {
                            return false;
                        };
                    } else {
                        alert("Thêm vào giỏ hàng thành công!");
                        document.f.action = "item?id=" + id;
                        document.f.submit();
                    }
                } else {
                    alert("Bạn cần đăng nhập để thực hiện tác vụ này!!");
                }
            }
        </script>
    </body>
</html>
