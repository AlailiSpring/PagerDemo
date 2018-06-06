<%--
  Created by IntelliJ IDEA.
  User: alailispring
  Date: 2018/6/6
  Time: 下午9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>登陆加密页面</title>
    <%
        String context = request.getContextPath();
    %>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=context%>/css/myLoginStyle.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=context%>/js/aes.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#submitBtn").bind("click",submitData);
        });

        function submitData() {
            var username = $("#username").val();
            var password = $("#password").val();

            var encryptUsername = encrypt(username);
            var encryptPassword = encrypt(password);

            $.ajax({
                type: "POST",
                url: "<%=context%>/cryptoServlet",
                data: {username:encryptUsername,password:encryptPassword},
                success: function () {
                }
            });
            return false;
        }

        function encryptByDES(message, key) {
            var keyHex = CryptoJS.enc.Utf8.parse(key);
            var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            return encrypted.toString();
        }

        /**
         * 加密（需要先加载lib/aes/aes.min.js文件）
         * @param word
         * @returns {*}
         */
        function encrypt(word){
            var key = CryptoJS.enc.Utf8.parse("abcdefgabcdefg12");
            var srcs = CryptoJS.enc.Utf8.parse(word);
            var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
            return encrypted.toString();
        }

        /**
         * 解密
         * @param word
         * @returns {*}
         */
        function decrypt(word){
            var key = CryptoJS.enc.Utf8.parse("abcdefgabcdefg12");
            var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
            return CryptoJS.enc.Utf8.stringify(decrypt).toString();
        }

    </script>


</head>
<body>
<form action="<%=context%>/cryptoServlet" id="cryptoForm" method="post">
    <div class="container">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" id="login_form">
                <h3 class="form-title">欢迎登陆</h3>
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="用户名" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="密码" id="password" name="password" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" name="remember" value="1"/>记住我
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success pull-right" name="submit" id="submitBtn">登录</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
