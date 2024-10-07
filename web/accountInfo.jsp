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

    .btn-save {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 15px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;
    }

    .btn-change-pass {
        background-color: #008CBA;
        color: white;
        border: none;
        padding: 10px 15px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;
    }
    /* Custom styles for the text */
    .custom-container {
        max-width: 600px; /* You can adjust the max-width to your preference */
        margin: 0 auto; /* Center the container */
    }
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
            <c:if test="${ac.isIsStaff() == false}">
                <%@include file="logoutHeader.jsp" %>
            </c:if>
            <c:if test="${ac.isIsStaff() == true}">
                <%@include file="adminHeader.jsp" %>
            </c:if>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginHeader.jsp" %>
        </c:if>
        <div class="banner-top" style="background-color: gray">
            <div class="container">
                <h3 >Thông tin cá nhân</h3>
                <h4><a href="start">Trang chủ</a><label>/</label>Thông tin</h4>
                <div class="clearfix"> </div>
            </div>
        </div>
        <div class="container-fluid custom-container center-div">
            <form id="updateForm" action="update" method="post">
                <table class="table row-md-12">
                    <tr class="col">
                        <th></th>
                    </tr>
                    <tr class="cross2 col">
                        <td class="panel-heading bg-danger">
                            <div class="profile-info">
                                <h2>My account:&nbsp;${ac.username}</h2>
                            </div>

                        </td>
                    </tr>
                    <tr class="cross2 col">
                        <td>
                            <label for="name">Họ và Tên:</label>
                            <input type="text" id="name" class="form-control input-lg" value="${ac.name}" name="name" required>
                        </td>
                    </tr>
                    <tr class="cross2 col">
                        <td>
                            <label for="email">Email:</label>
                            <input type="text" id="email" class="form-control input-lg" value="${ac.email}" name="email" required>
                        </td>
                    </tr>
                    <tr class="cross2 col">
                        <td>
                            <label for="phone">Số điện thoại:</label>
                            <input type="text" id="phone" class="form-control input-lg" value="${ac.phone}" name="phone" required>
                        </td>
                    </tr>
                    <tr class="cross2 col">
                        <td>
                            <label for="address">Địa chỉ:</label>
                            <input type="text" id="address" class="form-control input-lg" value="${ac.address}" name="address" required>
                        </td>
                    </tr>
                    <tr class="cross2 col">
                        <td>
                            <div>
                                <button type="submit" class="btn btn-success" onclick="confirmSubmit()">Lưu thay đổi</button>
                                <a href="changePassword" class="btn btn-danger">Thay đổi mật khẩu</a>
                            </div>
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <script>
            function confirmSubmit() {
                var result = confirm("Bạn có chắc chắn lưu thông tin chỉnh sửa?");
                if (result) {
                    document.getElementById("updateForm").submit();
                }
            }
        </script>
        <c:if test="${not empty account}">
            <c:if test="${ac.isIsStaff() == false}">
                <%@include file="logoutFooter.jsp" %>
            </c:if>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginFooter.jsp" %>
        </c:if>
    </body>
</html>