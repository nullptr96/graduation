<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<body>
<div class="header" id="head">
    <div class="title">???管理系统</div>

</div>

<div class="wrap" id="wrap">
    <div class="logGet">
        <!-- 头部提示信息 -->
        <div class="logD logDtip">
            <p class="p1">登录</p>
        </div>
        <!-- 输入框 -->
        <div class="lgD">
            <form action="" name="userForm">
                <img src="img/logName.png" width="20" height="20" alt=""/>
                <input type="text" name="id" placeholder="输入用户名" />
                <img src="img/logPwd.png" width="20" height="20" alt=""/>
                <input type="text" name = "password" placeholder="输入用户密码" />
                <input type="button" value="登录" onclick="login()">
                <input type="button" value="注册" onclick="register()">
            </form>
        </div>
    </div>
</div>

<div class="footer" id="foot">
    <div class="copyright">
        <p>Copyright © 2018 Qunar.com Inc. All Rights Reserved.</p>
        <div class="img">
            <i class="icon"></i><span>联系邮箱：???</span>
        </div>

        <div class="img">
            <i class="icon1"></i><span>联系地址：???</span>
        </div>

        <div class="img">
            <i class="icon2"></i><span>联系电话：???</span>
        </div>


    </div>

</div>
<script type="text/javascript">
    function login() {
        var form = document.forms[0];
        form.action = "<%=basePath %>user/login";
        form.method = "post";
        form.submit();
    }

    function register() {
        var form = document.forms[0];
        form.action = "<%=basePath %>user/register";
        form.method = "post";
        form.submit();
    }
</script>

</body>
</html>
