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
                <h3>Lịch sử đơn hàng</h3>
                <h4><a href="<%=request.getContextPath()%>/start">Trang chủ</a><label>/</label>Lịch sử đơn hàng</h4>
                
                <div class="clearfix"></div> 
                <h4><a href="<%=request.getContextPath()%>/addMoney">Nạp đạn</a>
                <h3>Số tiền còn lại: ${ac.payment}</h3> 
            </div>
        </div>

        <div class="check-out">	 
            <div class="container">	 
                <div class="spec ">
                    <h3>Đơn hàng</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>

                <table class="table" style="text-align: center">
                    <tr class="row">
                        <th class="t-head col-md-1" style="text-align: center;">STT</th>
                        <th class="t-head col-md-1" style="text-align: center;">Mã đơn</th>
                        <th class="t-head col-md-3" style="text-align: center;">Đơn hàng</th>
                        <th class="t-head col-md-2" style="text-align: center;">Tổng giá tiền</th>
                        <th class="t-head col-md-2" style="text-align: center;">Ngày đặt hàng</th>
                        <th class="t-head col-md-2" style="text-align: center;">Trạng thái</th>
                        <th class="t-head col-md-1" style="text-align: center;">Tùy chọn</th>
                    </tr>

                    <c:forEach items="${requestScope.listOrder}" var="lo" varStatus="status"> 
                        <tr class="row">
                            <td class="t-data col-md-1">
                                <h4>${status.count}</h4>
                            </td>
                            <td class="t-data col-md-1">
                                <h4>${lo.id}</h4>
                            </td>
                            <td class="t-data col-md-3">
                                <c:forEach items="${requestScope.listOrderItems}" var="loi">
                                    <c:if test="${lo.id == loi.getOrderID()}">
                                        <c:forEach items="${requestScope.listBook}" var="lb">
                                            <c:if test="${loi.getBookID() == lb.getId()}">
                                                <p style="text-align: left; font-size: 12.5px">"${lb.getTitle()}" x ${loi.getQuantity()}</p>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                                <div class="clearfix"> </div>
                            </td>
                            <td class="t-data col-md-2"><h4>${lo.totalPrice}</h4></td>
                            <td class="t-data col-md-2"><h4>${lo.orderDate}</h4></td> 
                            <td class="t-data col-md-2">
                                <c:if test="${lo.status == 'Đã hoàn thành'}">
                                    <h4>Đã nhận hàng</h4>
                                    <form action="viewOrderDetails" method="get">
                                        <input type="hidden" name="oid" value="${lo.id}">
                                        <input type="submit" value="Xem đơn">
                                    </form>
                                </c:if>
                                <c:if test="${lo.status != 'Đã hoàn thành'}">
                                    <h4>${lo.status}</h4>
                                </c:if>
                            </td>
                            <td class="t-data col-md-1">
                                <div class="col-md-8">
                                    <c:if test="${lo.status == 'Đang giao'}">
                                        <form id="confirmForm${lo.id}" action="confirm?oid=${lo.id}&&confirmId=1" method ="post">
                                            <div>
                                                <input type="hidden" value="${lo.id}" name="oid${lo.id}">
                                                <input type="button" style="background-color: #4CAF50; color: white; border: none; text-align: center" value="Đã nhận được hàng" onclick="confirmDeli()">                               
                                            </div>
                                        </form>
                                        <script>
                                            function confirmDeli() {
                                                var result = confirm("Xác nhận đã nhận hàng? Bạn sẽ không thể hoàn trả sau khi xác nhận.");
                                                if (result) {
                                                    document.getElementById("confirmForm${lo.id}").submit();
                                                }
                                            }
                                        </script>
                                    </c:if>
                                    <c:if test="${lo.status == 'Chờ xử lý'}">
                                        <form id="cancelForm${lo.id}" action="cancel?oid=${lo.id}&&cancelId=1" method ="post">
                                            <div>
                                                <input type="hidden" value="${lo.id}" name="oid${lo.id}">
                                                <input type="button" style="background-color: #4CAF50; color: white; border: none; text-align: center" value="Hủy đơn" onclick="confirmSubmit()">                                   
                                            </div>
                                        </form>
                                        <script>
                                            function confirmSubmit() {
                                                var result = confirm("Bạn có chắc chắn muốn hủy đơn hàng?");
                                                if (result) {
                                                    document.getElementById("cancelForm${lo.id}").submit();
                                                }
                                            }
                                        </script>        
                                    </c:if>

                                </div>                              
                            </td>
                        </tr>
                    </c:forEach>
                </table>
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