<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Chi tiết đơn hàng</title>
        <%@include file="link.jsp" %>
    </head>
    <body>
        <c:set var="size" value="${sessionScope.size}"/>
        <c:set var="c" value="${sessionScope.cart}"/>
        <c:set var="ac" value="${sessionScope.account}"/>
        <c:if test="${not empty ac}">
            <%@include file="logoutHeader.jsp" %>
        </c:if>
        <c:if test="${empty ac}">
            <%@include file="loginHeader.jsp" %>
        </c:if>   

        <div class="banner-top" style="background-color: gray">
            <div class="container">
                <h3>Lịch sử đơn hàng</h3>
                <h4><a href="<%=request.getContextPath()%>/start">Trang chủ</a><label>/</label>Lịch sử đơn hàng</h4>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="con-w3l">
            <table class="styled-table">
                <thead>
                    <tr class="row">
                        <th class="t-head col-md-1" style="background-color: #3498db; color: #fff;">STT</th>
                        <th class="t-head col-md-5" style="background-color: #3498db; color: #fff;">Sách</th>
                        <th class="t-head col-md-1" style="background-color: #3498db; color: #fff;">Số lượng</th>
                        <th class="t-head col-md-2" style="background-color: #3498db; color: #fff;">Giá</th>
                        <th class="t-head col-md-2" style="background-color: #3498db; color: #fff;">Thành tiền</th>
                        <th class="t-head col-md-1" style="background-color: #3498db; color: #fff;">Tùy chọn</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.listOrderItems}" var="loi" varStatus="status">
                        <c:forEach items="${requestScope.listBook}" var="lb">
                            <c:if test="${loi.getBookID() == lb.id}">
                                <tr class="cross2 row">
                                    <td class="t-data col-md-1">
                                        <h4>${status.index + 1}</h4>
                                    </td>
                                    <td class="t-data col-md-4">
                                        <div class="col-md-9">
                                            <h4>${lb.title}</h4>
                                        </div>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${loi.quantity}</h4>
                                        <div class="clearfix"></div>
                                    </td>                                               
                                    <td class="t-data col-md-1">
                                        <h4>${lb.price}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${lb.price*loi.quantity}</h4>
                                    </td>
                                    <td class="t-data col-md-2">                                        
                                        <div class="col-md-6">
                                            <div> 
                                                <a href="updateFeedback?bid=${lb.id}"><button class="btn btn-success"><h4>Feedback</h4></button></a>                                                      
                                            </div>
                                        </div> 
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
            <div class="clearfix"></div>
        </div>        
        <c:if test="${not empty ac}">
            <%@include file="logoutFooter.jsp" %>
        </c:if>
        <c:if test="${empty ac}">
            <%@include file="loginFooter.jsp" %>
        </c:if>

        <script src="js/bootstrap.js"></script>
    </body>



</html>
