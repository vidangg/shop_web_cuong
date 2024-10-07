<%-- 
    Document   : addMoney
    Created on : Jul 18, 2024, 5:07:24 PM
    Author     : QuocCuong
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>KuongStore.com</title>
        <%@include file="link.jsp" %>
    </head>
    <body>
    <c:set var="size" value="${sessionScope.size}"/>
    <c:set var="c" value="${sessionScope.cart}"/>
    <c:set var="ac" value="${sessionScope.account}"/>
    <jsp:useBean id="cate" class="dao.CategoryDAO"/>
    <jsp:useBean id="pub" class="dao.ProductDAO"/>
    <c:if test="${not empty account}">
        <%@include file="logoutHeader.jsp" %>
    </c:if>
    <c:if test="${empty account}">
        <%@include file="loginHeader.jsp" %>
    </c:if>   

    <div class="banner-top"  style="background-color: gray; height: 130px;">
        <div class="container">
            <h3>Nạp tiền</h3>
            <h4><a href="<%=request.getContextPath()%>/start">Trang chủ</a><label>/</label>Nạp tiền</h4>

            <div class="clearfix"></div> 
            <h4><a href="<%=request.getContextPath()%>/addMoney">Nạp đạn</a>

        </div>
    </div>

    <div class="check-out">	 
        <div class="container">	 
            <div class="spec ">
                <h3>Mã thanh toán</h3>
                <div class="ser-t">
                    <b></b>
                    <span><i></i></span>
                    <b class="line"></b>
                </div>
            </div>
            <h3 style="margin-left:300px; margin-bottom: 30px"> Nội dung chuyển khoản: "Tài khoản_Số tiền_Ngày"</h3>
            <img src="images/qrdqc.jfif" alt="alt" style ="height: 600px;display: flex;
            justify-content: center;margin-left: 310px;
            align-items: center;"/>

        </div>
    </div>

    <c:if test="${not empty account}">
        <%@include file="logoutFooter.jsp" %>
    </c:if>
    <c:if test="${empty account}">
        <%@include file="loginFooter.jsp" %>
    </c:if>
</body>
</html>