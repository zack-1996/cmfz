<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<!--引入jqgrid核心js-->
<script src="${pageContext.request.contextPath}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
<!--引入jqgrid国际化js-->
<script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>

<script>
    $(function () {
        //初始化表格
        $("#userList").jqGrid({
            styleUI:"Bootstrap",//设置bootstrap风格样式表格
            autowidth:true,
            height:400,
            url:"${pageContext.request.contextPath}/article/findAll",//这是请求url
            datatype:"json",//设置响应结果为json格式
            colNames:['ID','Title',"Status","Img","content","Create_date","Publish_date","Author","操作"],//指定表格标题
            colModel:[  //用来指定json中那个key作为该列的值
                {name:"id",search: false,align:"center"},//这是列属性对象配置信息
                {name:"title",editable:true,align:"center",editoptions:{required:true}},
                {name:"status",editable:true,align:"center",edittype:"select"},
                {name:"img",editable:true,align:"center",edittype:"file",
                    formatter:function(value,options,row){
                        return "<img src='"+value+"' style='width:100%'/>";
                    },editoptions:{enctype:"multipart/form-data"}
                },
                {name:"content",editable:true,align:"center",editoptions:{required:true}},
                {name:"create_date",editable:true,align:"center",editoptions:{required:true}},
                {name:"publish_date",editable:true,align:"center",editoptions:{required:true}},
                {name:"guru_id",editable:true,align:"center",editoptions:{required:true},
                    formatter:function (value,options,row) {
                        return row.guru.name;
                    },edittype:"select",editoptions:{
                        dataUrl:"${pageContext.request.contextPath}/guru/findAuthor"
                    }
                },
                {name:'option',formatter:function (cellvalue, options, rowObject) {
                        var result = '';
                        result += "<a href='javascript:void(0)' onclick=\"showModel('" + rowObject.id + "')\" class='btn btn-lg' title='查看详情'> <span class='glyphicon glyphicon-th-list'></span></a>";
                        return result;
                    }},

            ],
            pager:"#pager",//指定分页工具栏
            rowNum:4,//每页显示记录数  rowNum最是rowList一个子元素
            rowList:[2,5,10],//出现下列列表 选择每页展示记录数
            viewrecords:true,//显示总条数
            editurl:'${pageContext.request.contextPath}/article/edit',//执行编辑时请求url  (添加 oper:add 删除 del  更新 edit)
            caption:"用户列表",//指定表格标题
            toolbar:[true,'top'],//给表格指定工具栏  如果工具栏位置为top 工具栏id为:t_tableid  下面位置tb_tableid
           /* multiselect:true,//开启多选*/
            dataUrl: "${pageContext.request.contextPath}/article/edit",

        });
        $("#userList").jqGrid('navGrid', "#pager", {
            add:false,
            edit:false,
            del: true,
            deltext: "删除"
        });


    });
    function showModel(id) {
        // 返回指定行的数据，返回数据类型为name:value，name为colModel中的名称，value为所在行的列的值，如果根据rowid找不到则返回空。在编辑模式下不能用此方法来获取数据，它得到的并不是编辑后的值
        var data = $("#userList").jqGrid("getRowData",id);
        $("#id").val(data.id);
        $("#name").val(data.title);
        $("#status").val(data.status);
        $("#tupian").attr("hidden","hidden");
        $("#shangshi").attr("hidden","hidden");
        $("#chuban").attr("hidden","hidden");
        $("#shangjia").attr("hidden","hidden");
        $("#editor_id").val(data.content);
        // KindEditor 中的赋值方法 参数1: kindeditor文本框 的id
        KindEditor.html("#editor_id",data.content);
        $("#modal_foot").html("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>"+
            "<button type=\"button\" class=\"btn btn-primary\" onclick=\"updateArticle()\" data-dismiss=\"modal\">修改</button>")
        $("#kind").modal("show");
    }

</script>
<script>
    // 打开模态框
    function addArticle() {
        // 清除表单内数据
        $("#kindfrm")[0].reset();
        // kindeditor 提供的数据回显方法  通过"" 将内容设置为空串
        KindEditor.html("#editor_id", "");
        // 未提供查询上师信息 发送ajax请求查询
        $("#modal_foot").html("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>"+
            "<button type=\"button\" class=\"btn btn-primary\" onclick=\"insertArticle()\" data-dismiss=\"modal\">添加</button>")
        $("#kind").modal("show");
    }
    // 编辑文章--更新回显


    //处理事件，作者回显
    $("#kind").on('show.bs.modal',function(data){
        //ajax请求  查询数据   渲染数据
        $.get("${pageContext.request.contextPath}/guru/findAuthor",function (data) {
            $('#guruList').empty();
            $('#guruList').append(data);
        });
    });


    // 添加文章
    function insertArticle() {
        $.ajaxFileUpload({
            url:"${pageContext.request.contextPath}/article/addArticle",
            datatype:"json",
            type:"post",
            fileElementId:"inputfile",
            // ajaxFileUpload 不支持序列化数据上传id=111&&title="XXX"
            //                只支持 Json格式上传数据
            // 解决方案 : 1.更改 ajaxFileUpload 源码 2. 手动封装Json格式
            data:{id:$("#formid").val(),title:$("#name").val(),guru_id:$("#guru").val(),status:$("#status").val(),content:$("#editor_id").val(),create_date:$("#create_date").val(),publish_date:$("#publish_date").val()},
            success:function (data) {

            }
        })
    }
    //更新文章
    function updateArticle() {
        $.ajaxFileUpload({
            url:"${pageContext.request.contextPath}/article/updateArticle",
            datatype:"json",
            type:"post",
            fileElementId:"inputfile",
            // ajaxFileUpload 不支持序列化数据上传id=111&&title="XXX"
            //                只支持 Json格式上传数据
            // 解决方案 : 1.更改 ajaxFileUpload 源码 2. 手动封装Json格式
            data:{id:$("#id").val(),title:$("#name").val(),status:$("#status").val(),content:$("#editor_id").val()},
            success:function (data) {

            }
        })
    }

</script>


<div class="col-sm-10">
    <div class="page-header">
        <h1>轮播图片列表</h1>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a>文章列表</a> </li>
        <li>
        <li><a onclick="addArticle()">添加文章</a></li>
        </li>
    </ul>
    <!--创建jqgrid-->
    <table id="userList"></table>

    <!--创建分页工具栏-->
    <div style="height: 50px" id="pager">

    </div>
</div>

