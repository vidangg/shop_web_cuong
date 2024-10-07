<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>KuongStore.com | Shipping</title>
        <%@include file="link.jsp" %>
    </head>
    <body>
        <c:set var="ac" value="${sessionScope.account}"/>
        <jsp:useBean id="cate" class="dao.CategoryDAO"/>
        <jsp:useBean id="pub" class="dao.PublisherDAO"/>
        <c:if test="${not empty account}">
            <%@include file="logoutHeader.jsp" %>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginHeader.jsp" %>
        </c:if>
        <!--banner-->
        <div class="banner-top" style="background-color: gray">
            <div class="container">
                <h3 >Shipping</h3>
                <h4><a href="start">Trang chủ</a><label>/</label>Shipping</h4>
                <div class="clearfix"> </div>
            </div>
        </div>
        <!-- faqs -->
        <div class="faq-w3 ">
            <div class="container">
                <div class="spec ">
                    <h3>Shipping</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>
                <div class="panel-group" id="accordion">
                    <!-- First Panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title" data-toggle="collapse" data-target="#collapseOne">
                                <span>1.</span> Chi phí vận chuyển được tính như thế nào?
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <div class="panel-body">
                                <p>Chi phí vận chuyển là chi phí cho việc vận chuyển hàng hóa từ địa chỉ cung cấp đến địa chỉ người nhận. Chi phí này được tính phụ thuộc vào số lượng, khối lượng hàng hóa, độ cồng kềnh của đơn hàng, chi phí đóng gói, bảo hiểm và khoảng cách giữa bên cung cấp và bên nhận dựa trên tuyến đường vận chuyển.</p>
                            </div>
                        </div>
                    </div>

                    <!-- Second Panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title" data-toggle="collapse"  data-target="#collapseTwo">
                                <span>2.</span> Đơn hàng của tôi mất bao lâu sẽ đến nơi?
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse">
                            <div class="panel-body">								
                                <p>Từ lúc khách hàng bấm mua hàng đến lúc nhận thì đơn hàng sẽ trải qua từng giai đoạn như: xác nhận đơn hàng, đóng gói, giao cho đơn vị vận chuyển, đơn vị vận chuyển vận chuyển đơn hàng, người mua nhận hàng. Phụ thuộc vào quãng đường vận chuyển, một đơn hàng sẽ mất từ 1 đến 4 ngày để giao đến tận tay người mua. Trong một số trường hợp ngày lễ, Tết, yếu tố thời tiết, dịch bệnh xấu cũng sẽ ảnh hưởng đến thời gian giao hàng.</p>
                            </div>
                        </div>
                    </div>

                    <!-- Third Panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title" data-toggle="collapse" data-target="#collapseThree">
                                <span>3.</span> KuongStore có hỗ trợ vận chuyển quốc tế không?
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse">
                            <div class="panel-body">
                                <p>Rất tiếc, hiện tại KuongStore.com cũng như các đối tác giao hàng của KuongStore.com không hỗ trợ giao hàng quốc tế.</p>
                            </div>
                        </div>
                    </div>
                    <!-- Fourth Panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title" data-toggle="collapse" data-target="#collapseFour">
                                <span>4.</span>Tại sao tôi vẫn chưa nhận được đơn hàng của mình?
                            </h4>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse">
                            <div class="panel-body">
                                <p>Đơn hàng kể từ lúc đặt hàng sẽ mất khoảng từ 1 đến 4 ngày để có thể giao đến tận nơi cho khách hàng. Nếu quá thời hạn trên mà quý khách vẫn chưa nhận được hàng, vui lòng liên hệ số: +8477 531 9165 để được hỗ trợ.</p>
                            </div>
                        </div>
                    </div>                   
                </div>
            </div>	
        </div>
        <!-- // Terms of use -->

        <c:if test="${not empty account}">
            <%@include file="logoutFooter.jsp" %>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginFooter.jsp" %>
        </c:if>
        
    </body>
</html>