<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>KuongStore.com|Giỏ hàng</title>
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

        <div class="banner-top"  style="background-color: gray; height: 130px">
            <div class="container">
                <h3 >Giỏ hàng</h3>
                <h4><a href="start">Trang chủ</a><label>/</label>Giỏ hàng</h4>
                <div class="clearfix"> </div>
                <h4><a href="<%=request.getContextPath()%>/addMoney">Nạp đạn</a>
                <h3>Số tiền còn lại: ${ac.payment}</h3> 
            </div>
        </div>

        <div class="check-out">	 
            <div class="container">	 
                <div class="spec ">
                    <h3>Giỏ hàng</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>

                <table class="table ">
                    <tr class="row">
                        <th class="t-head head-it  col-md-5" style="text-align: center;">Sản phẩm</th>
                        <th class="t-head col-md-2" style="text-align: center;">Giá tiền</th>
                        <th class="t-head col-md-2" style="text-align: center;">Số lượng</th>
                        <th class="t-head col-md-3" style="text-align: center;">Tùy chọn</th>
                    </tr>

                    <c:forEach items="${c.items}" var="ci">
                        <tr class="cross2 row">
                            <td class="t-data ring-in col-md-5">                          
                                <div class="col-md-3">
                                    <a href="item?id=${ci.book.id}" class="at-in"><img src="${ci.book.imgLink}" class="img-cart img-responsive" alt=""></a>
                                </div>
                                <div class="sed col-md-9">
                                    <h5>${ci.book.title}</h5>
                                </div>
                                <div class="clearfix"> </div>
                            </td>
                            <td class="t-data col-md-2" style="text-align: center;">${ci.price}</td>
                            <td class="t-data col-md-2" style="text-align: center;">
                                <div class="quantity" style="margin-left: 14px"> 
                                    <div class="quantity-select">            
                                        <a href="process?num=-1&&id=${ci.book.id}"><div class="entry value-minus">&nbsp;</div></a>
                                        <div class="entry value"><span class="span-1">${ci.quantity}</span></div>									
                                        <a href="process?num=1&&id=${ci.book.id}"><div class="entry value-plus active">&nbsp;</div></a>
                                    </div>
                                </div>

                            </td>                    
                            <td class="t-data col-md-3" style="text-align: center;">
                                <div class="col-md-3">
                                    <form id="buyOneForm" action="buy" method ="post">
                                        <div>
                                            <input type="hidden" value="${ci.book.id}" name="bid" id="bid">
                                            <input type="hidden" value="${ci.quantity}" name="bquantity" id="bquantity">
                                            <input type="hidden" value="${ci.price}" name="bquantity" id="bprice">
                                            <input type="button" style="background-color: #4CAF50; color: white; border: none; margin-left: 15px" onclick="confirmBuy()" value="MUA">                             
                                        </div>
                                    </form>

                                    <script>
                                        function confirmBuy() {
                                            
                                            var result = confirm("Bạn có chắc chắn muốn mua sản phẩm?");
                                            if (result) {
                                                document.getElementById("buyOneForm").submit();
                                            }
                                        }
                                    </script>
                                </div>
                                <div class="col-md-3"></div>
                                <div class="col-md-6">
                                    <form id="cancelOneForm" action="process" method ="post">
                                        <input type="hidden" value="${ci.book.id}" name="id" >
                                        
                                        <input type="button" style="background-color: #4CAF50; color: white; border: none; margin-left: -25px" onclick="confirmDel()" value="XOÁ">
                                    </form>
                                </div>
                                <script>
                                    function confirmDel() {
                                        var result = confirm("Bạn có chắc chắn muốn xóa sản phẩm khỏi giỏ hàng?");
                                        if (result) {
                                            document.getElementById("cancelOneForm").submit();
                                        }
                                    }
                                </script>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${not empty requestScope.totalPrice}">
                        <tr class="cross2 row">
                            <td class="t-data ring-in col-md-5">
                                <h4><b>Tổng giá tiền:</b></h4>
                            </td>
                            <td class="t-data col-md-2" style="text-align: center;"><b>${requestScope.totalPrice}</></td>
                            <td class="t-data col-md-2">
                            </td>                    
                            <td class="t-data col-md-3" style="text-align: center;">
                                <div class="col-md-5">
                                    <form id="buyForm" action="buy" method ="post">
                                        <div>
                                            <input type="hidden" value="checkout" name="checkout">
                                            <input type="button" style="background-color: #4CAF50; color: white; border: none" value="MUA HẾT" onclick="confirmBuyAll()">                           
                                        </div>
                                    </form>
                                </div>
                                <script>
                                    function confirmBuyAll() {
                                        var result = confirm("Bạn có chắc chắn muốn mua tất cả sản phẩm trong giỏ?");
                                        if (result) {
                                            document.getElementById("buyForm").submit();
                                        }
                                    }
                                </script>
                                <div class="col-md-7">
                                    <form id="cancelForm" action="cancel" method ="post">
                                        <input type="hidden" value="clear" name="clear">
                                        <input type="button" style="background-color: #4CAF50; color: white; border: none" value="XOÁ HẾT" onclick="confirmDelAll()">                                   
                                    </form>
                                </div>
                                <script>
                                    function confirmDelAll() {
                                        var result = confirm("Bạn có chắc chắn muốn xóa tất cả sản phẩm trong giỏ?");
                                        if (result) {
                                            document.getElementById("cancelForm").submit();
                                        }
                                    }
                                </script>
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

                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
        <div>

        </div>
        <c:if test="${not empty account}">
            <%@include file="logoutFooter.jsp" %>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginFooter.jsp" %>
        </c:if>
    </body>
</html>