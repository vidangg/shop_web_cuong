<%-- 
    Document   : faq
    Created on : Jul 16, 2024, 8:49:21 AM
    Author     : QuocCuong
--%>

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
                <h3 >FAQS</h3>
                <h4><a href="start">Trang chủ</a><label>/</label>FAQS</h4>
                <div class="clearfix"> </div>
            </div>
        </div>
        <!-- faqs -->
        <div class="faq-w3 ">
            <div class="container">
                <div class="spec ">
                    <h3>FAQS</h3>
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
                                <span>1.</span> Về Sản Phẩm?
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <div class="panel-body">
                                <p>
                                <h3>Anh Hiêu Xuấn: Làm thế nào để tôi đặt hàng?</h3>
                                Để đặt hàng, bạn chỉ cần chọn sản phẩm mong muốn, thêm vào giỏ hàng và thực hiện thanh toán theo hướng dẫn.
                                <h3>Cô Thị Quân: Tôi có thể hủy đơn hàng sau khi đã đặt không?</h3>
                                Bạn có thể hủy đơn hàng trong vòng 24 giờ sau khi đặt hàng bằng cách liên hệ với bộ phận chăm sóc khách hàng.
                                <h3>Sản phẩm của bạn có bảo hành không?</h3>
                                Có, tất cả các sản phẩm của chúng tôi đều được bảo hành 12 tháng.
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- Second Panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title" data-toggle="collapse"  data-target="#collapseTwo">
                                <span>2.</span> Về Thanh Toán
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse">
                            <div class="panel-body">								
                                <p><h3>Tôi có thể thanh toán bằng những phương thức nào?</h3>
                                Chúng tôi chấp nhận thanh toán qua thẻ tín dụng, chuyển khoản ngân hàng, và thanh toán khi nhận hàng (COD).
                                <h3>Tôi có thể yêu cầu xuất hóa đơn VAT không?</h3>
                                Có, bạn có thể yêu cầu xuất hóa đơn VAT bằng cách điền thông tin chi tiết khi đặt hàng.
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- Third Panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title" data-toggle="collapse" data-target="#collapseThree">
                                <span>3.</span> Về Chính Sách Đổi Trả
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse">
                            <div class="panel-body">
                                <p><h3>Chị HA: Tôi có thể đổi trả sản phẩm không?</h3>

                                    Bạn có thể đổi trả sản phẩm trong vòng 7 ngày kể từ khi nhận hàng, với điều kiện sản phẩm còn nguyên tem và chưa qua sử dụng.
                                    <h3>Cô Vĩ: Phí đổi trả sản phẩm là bao nhiêu?</h3>

                                    Phí đổi trả sản phẩm là miễn phí nếu sản phẩm lỗi từ nhà sản xuất. Nếu đổi trả vì lý do cá nhân, bạn sẽ phải chịu phí vận chuyển.</p>
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