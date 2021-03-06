
<%--
  Created by IntelliJ IDEA.
  User: alailispring
  Date: 2018/5/13
  Time: 上午1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>学生列表</title>
    <%
        String context = request.getContextPath();
    %>
    <link href="../css/pagination.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="../js/jquery.pagination.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#News-Pagination").pagination(${result.totalRecord}, {
                items_per_page:${result.pageSize},
                current_page:${result.currentPage}-1,
                num_display_entries:8,
                next_text:"下一页",
                prev_text:"上一页",
                num_edge_entries:2,
                callback:handlePaginationClick
            });
            /*设置学生的默认性别*/
            $("#gender").val("${gender}");
        });
        /*单击回调函数*/
        function handlePaginationClick(new_page_index, pagination_container) {
            $("#stuForm").attr("action", "<%=context %>/jdbcSql/jdbcSqlServlet?pageNum="+(new_page_index+1));
            $("#stuForm").submit();
            return false;
        }
    </script>
</head>
<body>
<div style="margin-left: 100px; margin-top: 100px;">
    <div>
        <font color="red">${errorMsg }</font>
    </div>
    <div>
        <form action="<%=context %>/jdbcSql/jdbcSqlServlet"   id="stuForm"  method="post">
            姓名
            <input type="text" name="stuName" id="stu_name" style="width:120px" value="${stuName }">
            &nbsp;
            性别
            <select name="gender" id="gender" style="width:80px">
                <option value="0">全部</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
            &nbsp;&nbsp;
            <input type="submit" value="查询" onclick="submitForm();">
        </form>
    </div>
    <br>
    学生信息列表：<br>
    <br>
    <!-- 后台返回结果为空 -->
    <c:if test="${fn:length(result.dataList) eq 0 }">
        <span>查询的结果不存在</span>
    </c:if>

    <!-- 后台返回结果不为空 -->
    <c:if test="${fn:length(result.dataList) gt 0 }">
        <table border="1px" cellspacing="0px"
               style="border-collapse: collapse">
            <thead>
            <tr height="30">
                <th width="130">姓名</th>
                <th width="130">性别</th>
                <th width="130">年龄</th>
                <th width="190">家庭地址</th>
            </tr>
            </thead>
            <c:forEach items="${result.dataList }" var="student">
                <tr>
                    <td><c:out value="${student.stuName }"></c:out></td>
                    <td>
                        <c:if test="${ student.gender eq 1}">男</c:if>
                        <c:if test="${ student.gender eq 2}">女</c:if>
                    </td>
                    <td><c:out value="${student.age }"></c:out></td>
                    <td><c:out value="${student.address }"></c:out></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div id="News-Pagination"></div>
    </c:if>
</div>
</body>
</html>
