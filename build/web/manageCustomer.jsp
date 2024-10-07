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
                                <li class=""><a href="staff" class=""><span>Đơn hàng</span></a></li>
                                <li class=""><a href="manage?mid=1" class=""><span>Sản phẩm</span></a></li>
                                <li class=""><a href="manage?mid=2" class=""><span>Doanh thu</span></a></li>
                                <li class="active"><a href="manage?mid=3" class=""><span>Khách hàng</span></a></li>
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
                    <li class="active"><a href="#tab1" data-toggle="tab">Danh sách khách hàng</a></li>
                    <li class=""><a href="#tab2" data-toggle="tab">Thêm khách hàng mới</a></li> 
                </ul>
            </nav>
            <div class=" tab-content tab-content-t ">
                <div class="tab-pane active text-style" id="tab1">
                    <div class="con-w3l">
                        <table class="styled-table">
                            <tr class="row">
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">STT</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Khách hàng</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">ID</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Tên khách hàng</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Email</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Số điện thoại</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Địa chỉ</th>
                                <th class="t-head col-md-1"style="background-color: #3498db; color: #fff;">Tiền còn lại</th>
                                <th class="t-head col-md-2"style="background-color: #3498db; color: #fff;">Tùy chọn</th>
                            </tr>

                            <c:forEach items="${requestScope.listCustomer}" var="lc" varStatus="status"> 
                                <tr class="cross2 row">
                                    <td class="t-data col-md-1">
                                        <h4>${status.count}</h4>
                                    </td>
                                    <td class="t-data col-md-1"><h4>${lc.username}</h4>
                                        <div class="clearfix"> </div>
                                    </td>
                                    <td class="t-data col-md-1"><h4>${lc.id}</h4>
                                        <div class="clearfix"> </div>
                                    </td>
                                    <td class="t-data col-md-1"><h4>${lc.name}</h4></td>
                                    <td class="t-data col-md-1"><h4>${lc.email}</h4></td>
                                    <td class="t-data col-md-1"><h4>${lc.phone}</h4></td> 
                                    <td class="t-data col-md-1"><h4>${lc.address}</h4></td>
                                    <td class="t-data col-md-1"><h4>${lc.payment}</h4></td>
                                    <td class="t-data col-3">
                                        <div class="col-md-6">
                                            <div> 
                                                <a href="updateCustomer?cid=${lc.id}"><button class="btn btn-success"><h4>Sửa</h4></button></a>                                                      
                                            </div>
                                        </div>                                            
                                        <div class="col-md-6">
                                            <div> 
                                                <input type="hidden" value="${lc.id}" name="oid${lc.id}">                                                        
                                                <h4><input type="button" class="btn btn-danger" value="Xóa" onclick="confirmDelete('${lc.id}')"></h4>                   
                                            </div>

                                        </div> 
                                        <script>
                                            function confirmDelete(id) {
                                                var result = confirm("Có chắc chắn muốn xóa khách hàng này? Khách hàng sẽ không thể xóa nếu tồn tại trong đơn hàng đang mua.");
                                                if (result) {
                                                    window.location = "<%=request.getContextPath()%>/deleteCustomer?cid=" + id;
                                                }
                                            }
                                        </script>
                                    </td>



                                </tr>
                            </c:forEach>
                        </table>
                        <div class="clearfix"></div>
                    </div>
                </div>

                <div class="tab-pane  text-style" id="tab2">    
                    <div class="styled-table_1">
                        <div class="col-md-12">
                            <form id="updateForm" action="updateCustomer" method="post">
                                <table class="table row-md-12">

                                    <tr class="cross2 col">
                                        <td>
                                            <label style="font-size: 2em" for="username">Username</label>
                                            <input type="text" class="form-control input-lg" value="${a.username}" name="username" required>
                                        </td>
                                    </tr>
                                    <tr class="cross2 col">
                                        <td>
                                            <label style="font-size: 2em" for="username">Password</label>
                                            <input type="password" class="form-control input-lg" value="${a.password}" name="password" required>
                                        </td>
                                    </tr>
                                    <tr class="cross2 col">
                                        <td>
                                            <label style="font-size: 2em" for="name">Email</label>
                                            <input type="email" class="form-control input-lg" value="${a.email}" name="email">
                                        </td>
                                    </tr>
                                    <tr class="cross2 col">
                                        <td>
                                            <label style="font-size: 2em" for="payment">Tiền còn lại</label>
                                            <input type="text" class="form-control input-lg"  value="${a.payment}" name="payment" min="0" step="0.01" placeholder="Nhập đạn">
                                        </td>
                                    </tr>
                                    <tr class="cross2 col">
                                        <td><h3><input type="button" class="btn btn-success" value="Lưu thay đổi" onclick="confirmSubmit()">
                                            </h3>
                                        </td>
                                    </tr>
                                </table>  
                            </form>
                            <script>
                                function confirmSubmit() {
                                    var result = confirm("Bạn có chắc chắn lưu thông tin chỉnh sửa?");
                                    if (result) {
                                        document.getElementById("updateForm").submit();
                                    }
                                }
                            </script>
                            <div class="clearfix"></div>
                        </div>   
                    </div>
                </div>

            </div>
        </div>
        <script src="js/bootstrap.js"></script>
    </body>
</html>