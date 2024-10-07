<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <!--footer-->
    <div class="footer">
        <div class="container">
            <div class="col-md-3 footer-grid">
                <h3>About Us</h3>
                <p>KuongStore.com - Chuỗi hệ thống bán lẻ sách, truyện, văn phòng phẩm</p>
            </div>
            <div class="col-md-3 footer-grid ">
                <h3>Menu</h3>
                <ul>
                    <li><a href="#">Trang chủ</a></li>
                    <li><a href="#">Thể Loại</a></li>
                    <li><a href="#">Nhà Xuất Bản</a></li>						 
                    <li><a href="#">Liên Hệ</a></li>
                </ul>
            </div>
            <div class="col-md-3 footer-grid ">
                <h3>Dịch vụ khách hàng</h3>
                <ul>
                    <li><a href="shipping">Shipping</a></li>
                    <li>Terms & Conditions</li>
                    <li><a href="faq">Faqs</a></li>
                    <li><a href="contact">Contact</a></li>              						
                </ul>
            </div>
            <div class="col-md-3 footer-grid">
                <h3>My Account</h3>
                <ul>
                    <li><a href="info">${ac.username}</a></li>
                    <li><a href="orderHistory">Đơn của tôi</a></li>
                    <li><a href="wishlist">Giỏ hàng</a></li>

                </ul>
            </div>
            <div class="clearfix"></div>
            <div class="footer-bottom">
                <h2><a href="start"><b>K<br>S<br>T</b>KuongStore<span class="subtitle">Books in multiverse</span></a></h2>
                <p class="fo-para">Sách, truyện, tiểu thuyết, tạp chí, văn phòng phẩm, đồ trang trí và tất cả những gì bạn cần</p>
                <ul class="social-fo">
                    <li><a href="https://www.facebook.com/" class=" face"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href="https://twitter.com/" class=" twi"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    <li><a href="https://www.pinterest.com/" class=" pin"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
                    <li><a href="https://dribbble.com/" class=" dri"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                </ul>
                <div class=" address">
                    <div class="col-md-4 fo-grid1">
                        <a href="https://www.safegate.vn/"><p><i class="fa fa-home" aria-hidden="true"></i>Công ty cổ phần An ninh mạng thông minh SCS</p></a>
                    </div>
                    <div class="col-md-4 fo-grid1">
                        <p><i class="fa fa-phone" aria-hidden="true"></i>+84775319165</p>	
                    </div>
                    <div class="col-md-4 fo-grid1">
                        <p><a href="mailto:CuongDQ.B20AT020@stu.ptit.edu.vn"><i class="fa fa-envelope-o" aria-hidden="true"></i>CuongDQ.B20AT020@stu.ptit.edu.vn</a></p>
                    </div>
                    <div class="clearfix"></div>

                </div>
            </div>
            <div class="copy-right">
                <p> &copy; 2024 KuongStore.com | Design by <a href="https://www.facebook.com/dqc0107">CUONGDQ</a></p>
            </div>
        </div>
        <script src="js/bootstrap.js"></script>
    </div>
    <!-- //footer-->
</html>
