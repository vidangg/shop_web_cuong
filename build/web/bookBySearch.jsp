<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>KuongStore.com</title>
        <%@include file="link.jsp" %>
    </head>

    <body>
        <c:set var="size" value="${sessionScope.size}"/>
        <c:set var="ac" value="${sessionScope.account}"/>
        <c:set var="spu" value="${requestScope.publisher}"/>
        <c:set var="sk" value="${requestScope.keyword}"/>
        <c:set var="sca" value="${requestScope.category}"/>
        <jsp:useBean id="cate" class="dao.CategoryDAO"/>
        <jsp:useBean id="pub" class="dao.ProductDAO"/>
        <c:if test="${not empty account}">
            <%@include file="logoutHeader.jsp" %>
        </c:if>
        <c:if test="${empty account}">
            <%@include file="loginHeader.jsp" %>
        </c:if>   
        <c:if test="${not empty publisher}">
            <div class="banner-top" style="background-color: gray">
                <div class="container">
                    <h3>Nhà xuất bản: ${spu.name}</h3>               
                    <div class="clearfix"> </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty category}">
            <div class="banner-top" style="background-color: gray">
                <div class="container">
                    <h3>Thể loại: ${sca.name}</h3>               
                    <div class="clearfix"> </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty keyword}">
            <div class="banner-top" style="background-color: gray">
                <div class="container">
                    <h3>Tìm kiếm</h3>               
                    <div class="clearfix"> </div>
                </div>
            </div>
        </c:if>
        <div class="content-top">
            <div class="container ">

                <div class="spec ">
                    <h3>Sản phẩm</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>
                <div class="con-w3l">
                    <table>
                        <c:forEach items="${sessionScope.listBookBySearch}" var="lbs" varStatus="status">
                            <c:if test="${status.count % 4 == 0}">
                                <div class="row">
                                </c:if>
                                <div class="col-md-3 m-wthree">
                                    <a href="<%=request.getContextPath()%>/item?id=${lbs.id}" class="offer-img" >
                                        <img src="${lbs.imgLink}" class="img-responsive" alt="" style="height: 200px">
                                    </a>
                                    <div class="mid-1">
                                        <div class="women">
                                            <h6><a href="<%=request.getContextPath()%>/item?id=${lbs.id}">${lbs.title}</a></h6>							
                                        </div>
                                        <div class="mid-2">
                                            <p ><label>${lbs.price*1.05}</label><em class="item_price">${lbs.price}VND</em></p>

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