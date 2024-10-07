<%-- 
    Document   : manageFeedback
    Created on : Jul 12, 2024, 11:49:54 PM
    Author     : QuocCuong
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .styled-table {
        border-collapse: collapse;
        margin: 0 auto;
        justify-content: center;
        align-items: center;
        font-size: 1em;
        font-family: sans-serif;
        max-width: 1300px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    }
    .styled-table thead tr {
        background-color: #009879;
        color: #008000;
        align-items: center;
    }
    .styled-table th,
    .styled-table td {
        padding: 15px 20px;
    }
    .styled-table tbody tr {
        border-bottom: 1px solid #dddddd;
    }

    .styled-table tbody tr:nth-of-type(even) {
        background-color: #f3f3f3;
    }

    .styled-table tbody tr:last-of-type {
        border-bottom: 2px solid #3498db;
    }
    .styled-table tbody tr.active-row {
        font-weight: bold;
        color: #009879;
    }
</style>
<html>
    <head>
        <title>KuongStore.com</title>
        <%@include file="link.jsp"%>
    </head>
    <body>
        <jsp:useBean id="acc" class="dao.AccountDAO"/>
        <jsp:useBean id="fbd" class="dao.FeedbackDAO"/>
        <div class="header">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a href="<%=request.getContextPath()%>/staff"><img style="width: 100%; height: 100px" src="images/Kuongstore.PNG" class="col-1img-head" alt="KuongStore.com|Logo"></a>
                    </div>
                    <div class="col-md-6">
                        <div class="logo">
                            <h1 ><a href="<%=request.getContextPath()%>/staff"><b>K<br>S<br>T</b>KuongStore<span class="subtitle">Books in multiverse</span></a></h1>
                        </div>

                        <div class="head-t">
                            <ul class="card">
                                <c:set var="ac" value="${sessionScope.account}"/>                      
                                <li><a href="info" ><i class="fa fa-user-plus" aria-hidden="true"></i>Nhân viên ${ac.username}</a></li>
                                <li><a href="logout" ><i class="fa fa-arrow-right" aria-hidden="true"></i>Đăng xuất</a></li>
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
                                <li class="active"><a href="manage?mid=5" class=""><span>Feedback</span></a></li>
                            </ul>
                        </div>
                    </nav>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <div class="banner-top" style="background-color: gray">
            <div class="container">
                <h3 >Nhân viên: ${ac.username}</h3>
                <div class="clearfix"> </div>
            </div>
        </div>  

        <div class="tab-head ">
            <div class=" tab-content tab-content-t ">
                <div class="con-w3l">
                    <table class="styled-table">
                        <thead>
                            <tr class="row">
                                <th class="t-head col-md-1" style="background-color: #3498db; color: #fff;">STT</th>
                                <th class="t-head col-md-2" style="background-color: #3498db; color: #fff;">Username</th>
                                <th class="t-head col-md-3" style="background-color: #3498db; color: #fff;">Sách</th>
                                <th class="t-head col-md-3" style="background-color: #3498db; color: #fff;">Feedback</th>
                                <th class="t-head col-md-2" style="background-color: #3498db; color: #fff;">Ngày feedback</th>
                                <th class="t-head col-md-1" style="background-color: #3498db; color: #fff;">Tuỳ chọn</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listFeedback}" var="lc" varStatus="status">
                                <tr class="cross2 row">
                                    <td class="t-data col-md-1">
                                        <h4>${status.count}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${lc.userName}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${lc.bookTitle}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${lc.feedback}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${lc.feedbackDate}</h4>
                                    </td>
                                    <td class="t-data col-md-2">                      
                                        <div class="col-md-6">
                                            <div> 
                                                <input type="hidden" value="${lc.feedbackID}" name="oid${lc.feedbackID}">                                                        
                                                <h4><input type="button" class="btn btn-danger" value="Xóa" onclick="confirmDelete('${lc.feedbackID}')"></h4>                   
                                            </div>

                                        </div> 
                                        <script>
                                            function confirmDelete(feedbackID) {
                                                    var result = confirm("Có chắc chắn muốn xóa feedback này?");
                                                if (result) {
                                                    window.location = "<%=request.getContextPath()%>/deleteFeedback?fid=" + feedbackID;
                                                }
                                            }
                                        </script>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="js/bootstrap.js"></script>
    </body>
</html>