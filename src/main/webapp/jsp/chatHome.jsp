<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echarts/china.js" charset="UTF-8"></script>
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
<script>
    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-7b55c608b1e7456fab3b2529c70b7335", //替换为您的应用appkey
    });
    goEasy.subscribe({
        channel: "zt_cmfz", //替换为您自己的channel
        onMessage: function (message) {
            // 手动将 字符串类型转换为 Json类型
            var div2 = $('<div class="chat_topuldiv fr">\n' +
                '<p class="chat_topuldiv_p2 message"><span>' + message.content + '</span></p>\n' +
                '</div>');
            $("#message").append(div2);

        }
    });
    function publishMessage() {
        var message=$("#publishMessages").val();
        goEasy.publish({
            channel: "zt_cmfz", //替换为您自己的channel
            message: message //替换为您想要发送的消息内容
        });
    }
</script>
</head>

<body>
<div id="subscribeMessages" class="chat_auto J_chat_auto">
    &nbsp;
    <div id="" class="chat_topd1">
        <span class="phone_none"></span>

        <p class="connectMessage"> 您已经成功连接GoEasy。</p>
        <span class="phone_none"></span>
    </div>
    <div id="" class="chat_topd1">
        <span class="phone_none"></span>

        <p class="connectMessage">: 您已成功订阅Channel。</p>
        <span class="phone_none"></span>
    </div>
    <div id="message" class="chat_topuly clearfix">

    </div>
    <%--<div id="" class="chat_topuly clearfix">
        <div class="chat_topulava fr">
            <img class="avatarImg" src="/resources/www/images/Avatar.png">
        </div>

    </div>--%>
</div>
<div class="demos_condsend clearfix">
    <input  id="publishMessages" type="text">
    <button  onclick="publishMessage();">Publish</button>
</div>
</body>
</html>