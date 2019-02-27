<%--
  Created by IntelliJ IDEA.
  User: XinJi
  Date: 2019/1/29
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <title>绘制组合服务流程图</title>

    <!--<link rel="stylesheet" type="text/css" href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" />-->
    <!--<link rel="stylesheet" type="text/css" href="//cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" />-->

    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="../lib/extjs/ext-base.js"></script>
    <script type="text/javascript" src="../lib/extjs/ext-all.js"></script>
    <script src="https://gojs.net/1.8.10/release/go-debug.js"></script>
    <link rel="styleSheet" href="../lib/extjs/css/ext-all.css" type="text/css">
    <style type="text/css">
        html, body{margin:0; background: #FFF; font-size:14px; line-height: 22px; font-family: "Microsoft YaHei", Helvetica, Arial, sans-serif}
        .flowdiv input{ font-size:12px; line-height: 18px; font-family: "Microsoft YaHei", Helvetica, Arial, sans-serif}
        .flowdiv textarea{ font-size:12px; line-height: 18px; font-family: "Microsoft YaHei", Helvetica, Arial, sans-serif}
    </style>

    <script type="text/javascript" src="../js/combinedWSFlowChart.js"></script>
    <script type="text/javascript" src="../js/combinedWSFlowExtjs.js"></script>

    <link rel="stylesheet" href="../css/combinedWSFlowChart.css">
</head>
<body>
<div id="flowdiv" class="flowdiv">
    <div style="width: 100%; display: flex; justify-content: space-between">
        <div style="width: 180px; margin-right: 2px; background-color: whitesmoke; border: solid 1px #cbcccc">
            <div id="palettediv"  style="width: 180px; height:360px; margin-right: 2px; background-color: whitesmoke; border: solid 1px #cbcccc"></div>
            <%--<div id="atrributediv" style="float:left; width:180px ; height: 330px;">--%>
                <%--<form action="" name="nodeAttribute">--%>
                    <%--<p>属性面板</p>--%>
                    <%--输入：<input type="text" class="nodeAttribute-input-output" id="nodeAttribute-input" placeholder="参数1;参数2;参数3"/><br>--%>
                    <%--输出：<input type="text" class="nodeAttribute-input-output" id="nodeAttribute-output" placeholder="参数1;参数2;参数3"  ><br>--%>
                    <%--业务流程描述：<input type="text" class="nodeAttribute-desc" id="nodeAttribute-desc" placeholder=""><br>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div id="toolbar" style="float:left; width:180px ; height: 30px; text-align:center;">--%>
                <%--<input type="button" value="保存" onclick="saveNode()">--%>
            <%--</div>--%>

        </div>
        <div style="flex-grow: 1; border: solid 1px #cbcccc">
            <div id="top-toolbar" style="flex-grow: 1; height: 50px; border: solid 1px #cbcccc">
                <span style="display:inline-block;vertical-align: middle; height :100%"></span>
                <input type="button" class = 'top-toobar-btn' value="保存组合服务流程" onclick="saveBF()">
                <input type="button" class = 'top-toobar-btn' value="调用组合服务流程" onclick="callWSF()">
                <ul class="nav">
                    <li class="drop-down"><a href="#">退出</a></li>
                    <li class="drop-down"><a id='userId' href="#">未登陆</a>
                        <ul class="drop-down-content">
                            <%--<li><a href="#">退出</a></li>--%>
                        </ul>
                    </li>
                </ul>
            </div>
            <div style="width: 100%; display: flex; justify-content: space-between">
                <div id="diagramdiv" style="flex-grow: 1; height: 620px; border: solid 1px #cbcccc"></div>
                <div style="width: 360px; margin-right: 2px; background-color: whitesmoke; border: solid 1px #cbcccc">
                    <%--<div id="palettediv" style="width: 180px; height:360px; margin-right: 2px; background-color: whitesmoke; border: solid 1px #cbcccc"></div>--%>
                    <div id="attributeDiv" style="float:right; width:360px ;background-color: whitesmoke; border: solid 1px #cbcccc">
                        <div id = "attributePanelDiv"></div>
                    </div>
                    <div id="toolbar" style="float:left; width:360px ; height: 30px; text-align:center;">
                        <input type="button" value="保存" onclick="saveNode()">
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>
<script type="text/javascript">
    document.getElementById('userId').innerText = userId;
</script>
</body>
</html>