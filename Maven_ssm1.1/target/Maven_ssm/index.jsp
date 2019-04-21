<%--
  Created by IntelliJ IDEA.
  User: lj
  Date: 2019/4/11
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <title>view back client</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src=" static/js/bootstrap.min.js"></script>
    <script>
        //加载完成以后的ajax请求
$(function () {
    re_page(1);
})
        //Ajax
        function re_page(pn) {
            $.ajax({
                url:"empS",
                data:"pn="+pn,
                type:"GET",
                success:function (result) {
                     //解析JSon
                    build_emps_table(result);
                    build_page_info(result);
                    build_page_nav(result)
                }
            })
        }
        //表格
        function build_emps_table(result) {
            $("#emps_table tbody").empty();
            var emps=result.extend.pageInfo.list;
            $.each(emps,function (index,item) {
                var empIdTd=$("<td></td>").append(item.empId);
                //alert(empIdTd);
                var empNameTd=$("<td></td>").append(item.empName);
                var genderTd=$("<td></td>").append(item.gender=='M'?"男":"女");
                var emailTd=$("<td></td>").append(item.email);
                var departmentTd=$("<td></td>").append(item.department.deptName);
                var btnFTd=$("<button></button>").addClass(" btn btn-primary btn-sm")
                    .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑")
                var btnSTd=$("<button></button>").addClass("btn btn-danger btn-sm")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除")
                var btn=$("<td></td>").append(btnFTd).append(" ").append(btnSTd);
                var tr= $("<tr></tr>").append(empIdTd)
                        .append(empNameTd)
                        .append(genderTd)
                        .append(emailTd)
                        .append(departmentTd)
                        .append(btn);
                $("#emps_table tbody").append(tr);


            })
        }
        //解析分页信息
        function build_page_info(result) {
            $("#page_info_area").empty();
            $("#page_info_area").append(" 当前"+result.extend.pageInfo.pageNum+"页，总共"+result.extend.pageInfo.pages+"页，总"+result.extend.pageInfo.total+"条记录 " )
        }
        //解析分页条
        function build_page_nav(result) {
            $("#page_nav_area").empty();
            var ul=$("<ul></ul>").addClass("pagination");
            var fisrtPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
            fisrtPageLi.click(function () {
                re_page(1)
            })
            var prePageLi=$("<li></li>").append($("<a></a>").append("<<"));

            if(result.extend.pageInfo.hasPreviousPage==false){
                fisrtPageLi.addClass("disabled");
                prePageLi.addClass("disabled");
            }
            else {
                prePageLi.click(function () {
                    re_page(result.extend.pageInfo.pageNum-1);
                })
            }
            var nextPageLi=$("<li></li>").append($("<a></a>").append(">>"));
            var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
            lastPageLi.click(function () {
                re_page(result.extend.pageInfo.total);
            })
            if(result.extend.pageInfo.hasNextPage==false){
                nextPageLi.addClass("disabled");
                lastPageLi.addClass("disabled");
            }
            else {
                nextPageLi.click(function () {
                    re_page(result.extend.pageInfo.pageNum+1);
                })
            }
            ul.append(fisrtPageLi).append(prePageLi);
            $.each(result.extend.pageInfo.navigatepageNums,function (index,item) {
                var numLi=$("<li></li>").append($("<a></a>").append(item));
                if(result.extend.pageInfo.pageNum==item){
                    numLi.addClass("active");
                }
                numLi.click(function () {
                    re_page(item);
                })
                ul.append(numLi)
            })
            ul.append(nextPageLi).append(lastPageLi);
            var nalEle=$("<nav></nav>").append(ul);
            $("#page_nav_area").append(nalEle);
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 log">
            <h1>SSM_curd &nbsp; Bootstrap</h1>
        </div>
    </div>
    <div class="row ">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <div class="row col-md-12">
        <table class="table table-hover" id="emps_table">
            <thead>
            <tr>
                <th>#</th>
                <th>empName</th>
                <th>gender</th>
                <th>email</th>
                <th>deptName</th>
                <th> 操作  </th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="col-md-6" id="page_info_area">

        </div>
        <div class="col-md-6" id="page_nav_area">

        </div>
    </div>
</div>
</body>
</html>
