<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <div class="header">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <a href="<%=request.getContextPath()%>/staff"><img style="width: 100%" src="images/Kuongstore.PNG" class="col-1img-head" alt="KuongStore.com|Logo"></a>
                </div>
                <div class="col-md-6">
                    <div class="logo">
                        <h1 ><a href="<%=request.getContextPath()%>/staff"><b>K<br>S<br>T</b>KuongStore<span class="subtitle">Books in multiverse</span></a></h1>
                    </div>

                    <div class="head-t">
                        <ul class="card">
                            <c:set var="ac" value="${sessionScope.account}"/>                      
                            <li><a href="logout" ><i class="fa fa-arrow-right" aria-hidden="true"></i>Đăng xuất</a></li>
                            <li><a href="info" ><i class="fa fa-user-plus" aria-hidden="true"></i>Nhân viên: ${ac.username}</a></li>
                        </ul>	
                    </div>

                    <div class="header-ri">
                        <ul class="social-top">
                            <li><a href="https://www.facebook.com/" class="icon facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                            <li><a href="https://twitter.com/" class="icon twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                            <li><a href="https://www.pinterest.com/" class="icon pinterest"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
                            <li><a href="https://dribbble.com/" class="icon dribbble"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                        </ul>	
                    </div>

                </div>   
            </div>

            <div class="nav-top">
                <nav class="navbar navbar-default">
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav">                            
                            <li class=""><a href="staff" class=""><span>Đơn hàng</span></a></li>
                            <li class=""><a href="manage?mid=1" class=""><span>Sản phẩm</span></a></li>
                            <li class=""><a href="manage?mid=2" class=""><span>Doanh thu</span></a></li>
                            <li class=""><a href="manage?mid=3" class=""><span>Khách hàng</span></a></li>
                            <li class=""><a href="manage?mid=4" class=""><span>Nhân viên</span></a></li>
                            <li class=""><a href="manage?mid=5" class=""><span>Feedback</span></a></li>
                        </ul>
                    </div>
                </nav>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</html>
