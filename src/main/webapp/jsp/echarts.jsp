
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>

    <title>echarts</title>
    <script>
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-7b55c608b1e7456fab3b2529c70b7335", //替换为您的应用appkey
        });

        goEasy.subscribe({
            channel: "zt_cmfz", //替换为您自己的channel
            onMessage: function (message) {
                // 手动将 字符串类型转换为 Json类型
                var data = JSON.parse(message.content);
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });
    </script>
</head>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts '
        },
        tooltip: {},
        legend: {
            data:['man','women']
        },
        xAxis: {
            data: ["1day","7day","30day","1year"]
        },
        yAxis: {},
        series: [],
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    // Ajax异步数据回显
    $.get("${pageContext.request.contextPath}/user/showUsersCount",function (data) {
        myChart.setOption({
            series:[
                {
                    name: 'man',
                    type: 'bar',
                    data: data.men,
                },{
                    name: 'women',
                    type: 'bar',
                    data: data.women,
                }
            ]
        })
    },"json")
</script>
