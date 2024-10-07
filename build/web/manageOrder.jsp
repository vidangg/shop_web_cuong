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
                                <li class="active"><a href="staff" class=""><span>Đơn hàng</span></a></li>
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
        <div class="banner-top" style="background-color: gray">
            <div class="container">
                <h3 >Nhân viên: ${ac.username}</h3>
                <div class="clearfix"> </div>
            </div>
        </div>  

        <div class="tab-head ">
            <nav class="nav-sidebar">
                <ul class="nav tabs ">
                    <li class="active"><a href="#tab1" data-toggle="tab">Chờ xử lý</a></li>
                    <li class=""><a href="#tab2" data-toggle="tab">Đang giao</a></li>
                    <li class=""><a href="#tab3" data-toggle="tab">Đã giao</a></li>
                    <li class=""><a href="#tab4" data-toggle="tab">Đã hủy</a></li> 
                </ul>
            </nav>

            <div class=" tab-content tab-content-t">                  
                <div class="tab-pane active text-style" id="tab1">
                    <div class="con-w3l">
                        <table class="styled-table">
                            <tr class="row">
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">STT</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Mã đơn</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Khách hàng</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">ID</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Tổng giá tiền</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Ngày đặt hàng</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Trạng thái</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Tùy chọn</th>
                            </tr>

                            <c:forEach items="${requestScope.listPendingOrder}" var="lpo" varStatus="status"> 
                                <tr class="cross2 row">
                                    <td class="t-data col-md-1">
                                        <h4>${status.count}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${lpo.id}</h4>
                                    </td>
                                    <c:forEach items="${acc.all}" var="accc">
                                        <c:if test="${accc.id == lpo.accountID}">                                               
                                            <td class="t-data col-md-2"><h4>${accc.username}</h4>
                                                <div class="clearfix"> </div>
                                            </td>
                                            <td class="t-data col-md-1"><h4>${accc.id}</h4>
                                                <div class="clearfix"> </div>
                                            </td>
                                        </c:if>
                                    </c:forEach>

                                    <td class="t-data col-md-1"><h4>${lpo.totalPrice}</h4></td>
                                    <td class="t-data col-md-2"><h4>${lpo.orderDate}</h4></td> 
                                    <td class="t-data col-md-2"><h4>${lpo.status}</h4></td>
                                    <td class="t-data col-md-2">
                                        <div class="col-md-6">
                                            <form id="confirmForm${lpo.id}" action="confirm?oid=${lpo.id}&&confirmId=2" method ="post">
                                                <div>
                                                    <input type="hidden" value="${lpo.id}" name="oid$lpolo.id}">
                                                    <h5><input type="button" class="btn btn-success" value="Xác nhận" onclick="confirmDeliver()"> </h5>                                  
                                                </div>
                                            </form> 
                                        </div>           
                                        <div class="col-md-6">
                                            <form id="cancelForm${lpo.id}" action="cancel?oid=${lpo.id}&&cancelId=2" method ="post">
                                                <div>
                                                    <input type="hidden" value="${lpo.id}" name="oid${lpo.id}">                                                        
                                                    <h5><input type="button" class="btn btn-danger" value="Hủy bỏ" onclick="confirmCancel()"></h5>                                
                                                </div>
                                            </form>
                                            <script>
                                                function confirmCancel() {
                                                    var result = confirm("Bạn có chắc chắn muốn hủy đơn hàng?");
                                                    if (result) {
                                                        document.getElementById("cancelForm${lpo.id}").submit();
                                                    }
                                                }
                                                function confirmDeliver() {
                                                    var result = confirm("Xác nhận đã bàn giao cho đơn vị vận chuyển?");
                                                    if (result) {
                                                        document.getElementById("confirmForm${lpo.id}").submit();
                                                    }
                                                }
                                            </script>
                                        </div>

                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="clearfix"></div>
                    </div>                                                                 
                </div>		  

                <div class="tab-pane text-style" id="tab2">
                    <div class="con-w3l">
                        <table class="styled-table">
                            <tr class="row">
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">STT</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Mã đơn</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Khách hàng</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">ID</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Tổng giá tiền</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Ngày đặt hàng</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Trạng thái</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Tùy chọn</th>
                            </tr>

                            <c:forEach items="${requestScope.listDeliOrder}" var="ldlo" varStatus="status"> 
                                <tr class="cross2 row">
                                    <td class="t-data col-md-1">
                                        <h4>${status.count}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${ldlo.id}</h4>
                                    </td>
                                    <c:forEach items="${acc.all}" var="accc">
                                        <c:if test="${accc.id == ldlo.accountID}">                                               
                                            <td class="t-data col-md-2"><h4>${accc.username}</h4>
                                                <div class="clearfix"> </div>
                                            </td>  
                                            <td class="t-data col-md-1"><h4>${accc.id}</h4>
                                                <div class="clearfix"> </div>
                                            </td>
                                        </c:if>
                                    </c:forEach>
                                    <td class="t-data col-md-1"><h4>${ldlo.totalPrice}</h4></td>
                                    <td class="t-data col-md-2"><h4>${ldlo.orderDate}</h4></td> 
                                    <td class="t-data col-md-2"><h4>${ldlo.status}</h4></td>
                                    <td class="t-data col-md-2">
                                        <div style="margin-left: 30px;">
                                            <form id="confirmForm${ldlo.id}" action="confirm?oid=${ldlo.id}&&confirmId=3" method ="post">
                                                <div>
                                                    <input type="hidden" value="${ldlo.id}" name="oid${ldlo.id}">
                                                    <h5><input type="button" class="btn btn-success" value="Xác nhận đã giao" onclick="confirmDeli()"> </h5>                                  
                                                </div>
                                            </form>
                                            <script>
                                                function confirmDeli() {
                                                    var result = confirm("Xác nhận đã giao đơn hàng thành công? Nếu khách hàng chưa nhận được hàng thì sẽ phải chịu trách nhiệm xử phạt!");
                                                    if (result) {
                                                        document.getElementById("confirmForm${ldlo.id}").submit();
                                                    }
                                                }
                                            </script>
                                        </div>                              
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="clearfix"></div>
                    </div>
                </div>

                <div class="tab-pane text-style" id="tab3">
                    <div class="con-w3l">
                        <table class="styled-table">
                            <tr class="row">
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">STT</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Mã đơn</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Khách hàng</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">ID</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Tổng giá tiền</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Ngày đặt hàng</th>
                                <th class="t-head col-md-3"style="background-color: #3498db; color: #fff;">Trạng thái</th>
                                <!--<th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Tùy chọn</th>-->
                            </tr>

                            <c:forEach items="${requestScope.listDoneOrder}" var="ldo" varStatus="status"> 
                                <tr class="cross2 row">
                                    <td class="t-data col-md-1">
                                        <h4>${status.count}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${ldo.id}</h4>
                                    </td>
                                    <c:forEach items="${acc.all}" var="accc">
                                        <c:if test="${accc.id == ldo.accountID}">                                               
                                            <td class="t-data col-md-1"><h4>${accc.username}</h4>
                                                <div class="clearfix"> </div>
                                            </td>
                                            <td class="t-data col-md-1"><h4>${accc.id}</h4>
                                                <div class="clearfix"> </div>
                                            </td> 
                                        </c:if>
                                    </c:forEach>
                                    <td class="t-data col-md-1"><h4>${ldo.totalPrice}</h4></td>
                                    <td class="t-data col-md-1"><h4>${ldo.orderDate}</h4></td> 
                                    <td class="t-data col-md-1"><h4 style="color: #3498db;">${ldo.status}</h4></td>
                                    <!--                                    <td class="t-data col-md-4">                             
                                                                        </td>-->
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="clearfix"></div>
                    </div>
                </div>

                <div class="tab-pane text-style" id="tab4">
                    <div class="con-w3l">
                        <table class="styled-table">
                            <tr class="row">
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">STT</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Mã đơn</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Khách hàng</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">ID</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Tổng giá tiền</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Ngày đặt hàng</th>
                                <th class="t-head col-md-3"style="background-color: #3498db; color: #fff;">Trạng thái</th>
                                <!--<th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Tùy chọn</th>-->
                            </tr>

                            <c:forEach items="${requestScope.listCanceledOrder}" var="lco" varStatus="status"> 
                                <tr class="cross2 row">
                                    <td class="t-data col-md-1">
                                        <h4>${status.count}</h4>
                                    </td>
                                    <td class="t-data col-md-1">
                                        <h4>${lco.id}</h4>
                                    </td>
                                    <c:forEach items="${acc.all}" var="accc">
                                        <c:if test="${accc.id == lco.accountID}">                                               
                                            <td class="t-data col-md-1"><h4>${accc.username}</h4>
                                                <div class="clearfix"> </div>
                                            </td> 
                                            <td class="t-data col-md-1"><h4>${accc.id}</h4>
                                                <div class="clearfix"> </div>
                                            </td>  
                                        </c:if>
                                    </c:forEach>
                                    <td class="t-data col-md-1"><h4>${lco.totalPrice}</h4></td>
                                    <td class="t-data col-md-1"><h4>${lco.orderDate}</h4></td> 
                                    <td class="t-data col-md-1"><h4 style="color: #3498db;">${lco.status}</h4></td>
                                    <!--                                    <td class="t-data col-md-3">                              
                                                                        </td>-->
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/bootstrap.js"></script>
    </body>
</html>