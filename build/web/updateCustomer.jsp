<%-- 
    Document   : updateCustomer
    Created on : Jul 9, 2024, 11:54:49 AM
    Author     : QuocCuong
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    table {
        width: 100%;
    }

    label {
        display: block;
        margin-bottom: 5px;
        font-size: 16px;
    }

    input {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        box-sizing: border-box;
    }

    /* Custom styles for the text */
    .custom-container {
        max-width: 900px; /* You can adjust the max-width to your preference */
        margin: 0 auto; /* Center the container */
    }

    .password-container {
        position: relative;
    }

    .password-container .eye-icon {
        position: absolute;
        top: 50%;
        right: 10px;
        transform: translateY(-50%);
        cursor: pointer;
        font-size: 1.5em;
    }


    .password-container .eye-icon img {
        width: 20px; /* Điều chỉnh kích thước của icon mắt */
        height: auto;
    }

</style>
<html>
    <head>
        <title>KuongStore.com</title>
        <%@include file="link.jsp"%>
    </head>
    <body>
        <jsp:useBean id="pub" class="dao.PublisherDAO"/>
        <jsp:useBean id="acd" class="dao.AccountDAO"/>
        <div class="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a href="<%=request.getContextPath()%>/staff"><img style="width: 100px; height: 100px" src="images/Kuongstore.PNG" class="col-1img-head" alt="KuongStore.com|Logo"></a>
                    </div>
                    <div class="col-md-6">
                        <div class="logo">
                            <h1 ><a href="<%=request.getContextPath()%>/staff"><b>K<br>S<br>T</b>KuongStore<span class="subtitle">Books in multiverse</span></a></h1>
                        </div>

                        <div class="head-t">
                            <ul class="card">
                                <c:set var="ac" value="${sessionScope.account}"/>                      
                                <li><a href="info" ><i class="fa fa-user-plus" aria-hidden="true"></i>Nhân viên ${ac.username}</a></li>
                                <li><a href="logout" ><i class="fa fa-arrow-right" aria-hidden="true"></i>Đăng xuất</a></li>
                            </ul>	
                        </div>

                        <div class="header-ri">
                            <ul class="social-top">
                                <li class="icon facebook"><i class="fa fa-facebook" aria-hidden="true"></i><span></span></a></li>
                                <li class="icon twitter"><i class="fa fa-twitter" aria-hidden="true"></i><span></span></a></li>
                                <li class="icon pinterest"><i class="fa fa-pinterest-p" aria-hidden="true"></i><span></span></a></li>
                                <li class="icon dribbble"><i class="fa fa-dribbble" aria-hidden="true"></i><span></span></a></li>
                            </ul>	
                        </div>

                    </div>   
                </div>   

                <div class="nav-top">
                    <nav class="navbar navbar-default">
                        <div class="navbar-header nav_2">
                            <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div> 

                        <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                            <ul class="nav navbar-nav">                            
                                <li class=""><a href="staff" class=""><span>Đơn hàng</span></a></li>
                                <li class=""><a href="manage?mid=1" class=""><span>Sản phẩm</span></a></li>
                                <li class=""><a href="manage?mid=2" class=""><span>Doanh thu</span></a></li>
                                <li class="active"><a href="manage?mid=3" class=""><span>Khách hàng</span></a></li>
                                <li class=""><a href="manage?mid=4" class=""><span>Nhân viên</span></a></li>
                                <li class=""><a href="manage?mid=5" class=""><span>Feedback</span></a></li>
                            </ul>
                        </div>
                    </nav>
                    <div class="clearfix"></div>
                </div>

            </div>
            <div class="banner-top" style="background-color: gray">
                <div class="container">
                    <h3 >Nhân viên: ${ac.username}</h3>
                    <div class="clearfix"> </div>
                </div>
            </div>  
            <br><br><br>
            <div class="container-fluid custom-container center-div">
                <c:set var="a" value="${requestScope.account}"/>
                <div class="row">
                    <div class="col-md-8" >
                        <form id="updateForm" action="updateCustomer" method="post">
                            <table class="table row-md-12" style="text-align: left">
                                <input type="hidden" class="form-control input-lg" value="${a.id}" name="id" required>
                                <tr class="cross2 col">
                                    <td>
                                        <label style="font-size: 2em" for="username">Username</label>
                                        <input type="text" class="form-control input-lg" value="${a.username}" name="username" required>
                                    </td>
                                </tr>
                                <tr class="cross2 col">
                                    <td>
                                        <label style="font-size: 2em" for="password">Password</label>
                                        <div class="password-container">
                                            <input type="password" id="password" class="form-control input-lg" value="${a.password}" name="password" required>
                                            <i id="togglePassword" class="eye-icon">&#128065;</i>
                                        </div>
                                    </td>
                                </tr>

                                <tr class="cross2 col">
                                    <td>
                                        <label style="font-size: 2em" for="name">Tên</label>
                                        <input type="text" class="form-control input-lg" value="${a.name}" name="name" required>

                                    </td>
                                </tr>
                                <tr class="cross2 col">
                                    <td>
                                        <label style="font-size: 2em" for="name">Email</label>
                                        <input type="email" class="form-control input-lg" value="${a.email}" name="email">
                                    </td>
                                </tr>
                                <tr class="cross2 col">
                                    <td>
                                        <label style="font-size: 2em" for="name">Số điện thoại</label>
                                        <input type="text" class="form-control input-lg" value="${a.phone}" name="phone">
                                    </td>
                                </tr>
                                <tr class="cross2 col">
                                    <td>
                                        <label style="font-size: 2em" for="name">Địa chỉ</label>
                                        <input type="text" class="form-control input-lg" value="${a.address}" name="address">
                                    </td>
                                </tr>
                                <tr class="cross2 col">
                                    <td>
                                        <label style="font-size: 2em" for="payment">Tiền còn lại</label>
                                        <input type="text" class="form-control input-lg" value="${a.payment}" name="payment">
                                    </td>
                                </tr>

                                <tr class="cross2 col">
                                    <td><h3><input type="button" class="btn btn-success" value="Lưu thay đổi" onclick="confirmSubmit()">
                                        </h3>
                                    </td>
                                </tr>
                            </table>  
                        </form>
                        <script>
                            document.getElementById('togglePassword').addEventListener('mouseenter', function () {
                                const passwordField = document.getElementById('password');
                                passwordField.type = 'text';
                                this.innerHTML = '&#128064;'; // Change to open eye icon
                            });

                            document.getElementById('togglePassword').addEventListener('mouseleave', function () {
                                const passwordField = document.getElementById('password');
                                passwordField.type = 'password';
                                this.innerHTML = '&#128065;'; // Change to closed eye icon
                            });


                            function confirmSubmit() {
                                var result = confirm("Bạn có chắc chắn lưu thông tin chỉnh sửa?");
                                if (result) {
                                    document.getElementById("updateForm").submit();
                                }
                            }
                        </script>
                        <div class="clearfix"></div>
                    </div>    
                </div>
            </div>
            <br><br><br>
            <script src="js/bootstrap.js"></script>
    </body>
</html>