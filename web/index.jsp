<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>KuongStore.com</title>
        <%@include file="link.jsp" %>
    </head>
    <script>
        function startCountdown(duration, display) {
            var timer = duration, hours, minutes, seconds;
            setInterval(function () {
                hours = parseInt(timer / 3600, 10);
                minutes = parseInt((timer % 3600) / 60, 10);
                seconds = parseInt(timer % 60, 10);

                hours = hours < 10 ? "0" + hours : hours;
                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                display.textContent = hours + " : " + minutes + " : " + seconds;

                if (--timer < 0) {
                    timer = duration;
                }
            }, 1000);
        }

        window.onload = function () {
            var countdownDuration = 60 * 60; // 1 hour in seconds
            var display = document.querySelector('#countdown');
            startCountdown(countdownDuration, display);
        };
    </script>   
    <body>
        <c:set var="size" value="${sessionScope.size}"/>
        <c:set var="ac" value="${sessionScope.account}"/>
        <jsp:useBean id="cate" class="dao.CategoryDAO"/>
        <jsp:useBean id="pub" class="dao.PublisherDAO"/>
        <c:if test="${not empty account}">
            <%@include file="logoutHeader.jsp" %>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginHeader.jsp" %>
        </c:if>   


        <div class="search-form">

            <form action="search" method="post">
                <input type="text" placeholder="Search..." name="keyword">
                <input type="submit" value=" " >
            </form>
        </div>
    
    <center>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="first-slide" src="images/banner3.PNG" alt="First slide">
                </div>

                <div class="item">
                    <img class="second-slide" src="images/banner1.PNG" alt="Second slide">
                </div>

                <div class="item">
                    <img class="third-slide " src="images/banner2.PNG" alt="Third slide">
                </div>
            </div>

        </div><!-- /.carousel-->
    </center>
    
    <div class="content-top ">
        <div class="container ">
            <div class="spec ">
                <h3>Sách Sang - Xịn - Mịn</h3>
                <div class="ser-t">
                    <b></b>
                    <span><i></i></span>
                    <b class="line"></b>
                </div>
            </div>

            <div class="tab-head ">
                <nav class="nav-sidebar">
                    <ul class="nav tabs ">
                        <li class="active"><a href="#tab1" data-toggle="tab">Sách mới</a></li>
                        <li class=""><a href="#tab2" data-toggle="tab">Sách hot</a></li> 
                    </ul>
                </nav>

                <div class=" tab-content tab-content-t ">                  
                    <div class="tab-pane active text-style" id="tab1">
                        <div class="con-w3l">
                            <table>
                                <c:forEach items="${requestScope.listNewBook}" var="lnb" varStatus="status">
                                    <c:if test="${status.count % 4 == 0}">
                                        <div class="row">
                                        </c:if>
                                        <div class="col-md-3 m-wthree">
                                            <a href="<%=request.getContextPath()%>/item?id=${lnb.id}" class="offer-img">
                                                <img src="${lnb.imgLink}" class="img-responsive" style="height: 200px" alt="">
                                            </a>
                                            <div class="mid-1">
                                                <div class="women">
                                                    <h6><a href="<%=request.getContextPath()%>/item?id=${lnb.id}">${lnb.title}</a></h6>							
                                                </div>
                                                <div class="mid-2">
                                                    <p ><label>${lnb.price*1.05}</label><em class="item_price">${lnb.price}VND</em></p>

                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${status.count % 4 == 0}">
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </table>
                            <div class="clearfix"></div>
                        </div>                                                                 
                    </div>		  

                    <div class="tab-pane text-style" id="tab2">
                        <div class="con-w3l">
                            <c:forEach items="${requestScope.listHotBook}" var="lhb" varStatus="status">
                                <c:if test="${status.count % 4 == 0}">
                                    <div class="row">
                                    </c:if>
                                    <div class="col-md-3 m-wthree">
                                        <a href="<%=request.getContextPath()%>/item?id=${lhb.id}" class="offer-img">
                                            <img src="${lhb.imgLink}" class="img-responsive" style="height: 200px" alt="${lhb.title}">
                                        </a>
                                        <div class="mid-1">
                                            <div class="women">
                                                <h6><a href="<%=request.getContextPath()%>/item?id=${lhb.id}">${lhb.title}</a></h6>							
                                            </div>
                                            <div class="mid-2">
                                                <p ><label>${lhb.price*1.05}</label><em class="item_price">${lhb.price}VND</em></p>

                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${status.count % 4 == 0}">
                                    </div>
                                </c:if>
                            </c:forEach>

                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <br> 
    <div class="spec">
        <h3>Nhà Xuất Bản</h3>
        <div class="ser-t">
            <b></b>
            <span><i></i></span>
            <b class="line"></b>
        </div>
    </div>
    <!--content-->
    <div class="content-mid">
        <div class="container">
            <div class="col-md-4 m-w3ls">
                <div class="col-md2">
                    <a href="search?pid=8">
                        <img src="images/nxbvh.png" class="img-responsive img" alt="">                          
                    </a>
                </div>
            </div>

            <div class="col-md-4 m-w3ls1">
                <div class="col-md2">
                    <a href="search?pid=7">
                        <img src="images/nxbgd.png" class="img-responsive img" alt="">
                    </a>
                </div>
            </div>

            <div class="col-md-4 m-w3ls1">
                <div class="col-md2">
                    <a href="search?pid=5">
                        <img src="images/nxbnh.jfif" class="img-responsive img" alt="">
                    </a>
                </div>
            </div>

            <div class="col-md-4 m-w3ls">
                <div class="col-md2">
                    <a href="search?pid=4">
                        <img src="images/amakLogo.png" class="img-responsive img" alt="">
                    </a>
                </div>
            </div>
            <div class="col-md-4 m-w3ls">
                <div class="col-md2">
                    <a href="search?pid=4">
                        <img src="images/amakLogo.png" class="img-responsive img" alt="">
                    </a>
                </div>
            </div>
            <div class="col-md-4 m-w3ls">
                <div class="col-md2">
                    <a href="search?pid=4">
                        <img src="images/amakLogo.png" class="img-responsive img" alt="">
                    </a>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!--content-->

    <c:if test="${not empty account}">
        <%@include file="logoutFooter.jsp" %>
    </c:if>
    <c:if test="${empty account}">
        <%@include file="loginFooter.jsp" %>
    </c:if>
</body>
</html>