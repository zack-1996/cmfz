<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../boot/css/back.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../boot/js/bootstrap.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script>
        //指定富文本框图片上传路径
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',{
                width:'700px',
                // 1. 指定图片上传路径
                uploadJson:"${pageContext.request.contextPath}/article/uploadImg",
                allowFileManager:true,
                afterBlur:function () {
                    this.sync();
                }
            });
        });
    </script>
    <title>持名法舟后台管理系统</title>

</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持名法舟后台管理系统</a>
        </div>
        <div>
            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="">欢迎:Rxx</a></li>
                <li><a href="">退出登录</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                <h3>用户管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/jsp/user.jsp');" class="list-group-item active">用户信息</a></li>
                                <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/jsp/echarts.jsp');" class="list-group-item active">用户注册趋势</a></li>
                                <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/jsp/map.jsp');" class="list-group-item active">用户地区分布</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                <h3>轮播图管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/jsp/banner.jsp');" class="list-group-item active">轮播图信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                <h3>上师管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a>上师信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                <h3>文章管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/jsp/article.jsp');" class="list-group-item active">文章信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                <h3>专辑管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/jsp/subgrid.jsp');" class="list-group-item active">专辑信息</a></li></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-10" id="content">
            <div class="container">
                <div class="jumbotron">
                    <h2>欢迎使用持名法舟后台管理系统！</h2>
                </div>
            </div>
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="../img/3e4d03381f30e924eebbff0d40086e061d95f7b0.jpg" alt="First slide">
                        <div class="carousel-caption">标题 1</div>
                    </div>
                    <div class="item">
                        <img src="../img/009e9dfd5266d016d30938279a2bd40735fa3576.jpg" alt="Second slide">
                        <div class="carousel-caption">标题 2</div>
                    </div>
                    <div class="item">
                        <img src="../img/098ca7cad1c8a786b4e6a0366609c93d71cf5049.jpg" alt="Third slide">
                        <div class="carousel-caption">标题 3</div>
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="panel-footer">
    <h4 style="text-align: center">百知教育 @baizhiedu.com.cn</h4>
</div>
<%--模态框--%>
<div class="modal fade" id="kind" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">文章信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" enctype="multipart/form-data" id="kindfrm">
                    <input name="id" type="text" id="id" hidden>
                    <div class="form-group">
                        <label for="name">标题</label>
                        <input type="text" class="form-control" name="title" id="name" placeholder="请输入名称">
                    </div>
                    <div class="form-group" id="tupian">
                        <label for="inputfile">封面上传</label>
                        <input type="file" name="inputfile" id="inputfile">
                    </div>
                    <div class="form-group">
                        <label for="name">状态</label>
                        <div >
                            <select id="status" name="status">
                                <option value="展示">展示</option>
                                <option value="冻结">冻结</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="shangshi">
                        <label for="name">所属上师</label>
                        <div id="guruList"></div>
                    </div>
                    <div class="form-group" id="chuban">
                        <label for="name">出版时间</label>
                        <input type="text" class="form-control" name="create_date" id="create_date">
                    </div>
                    <div class="form-group" id="shangjia">
                        <label for="name">上架时间</label>
                        <input type="text" class="form-control" name="publish_date" id="publish_date">
                    </div>
                    <div class="form-group">
                        <label for="editor_id">内容</label>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="modal_foot">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>

</html>