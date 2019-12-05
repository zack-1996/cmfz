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
            url:"${pageContext.request.contextPath}/banner/findAll",//这是请求url
            datatype:"json",//设置响应结果为json格式
            colNames:['ID','Title',"Url","Href","Create_date","Description","Status"],//指定表格标题
            colModel:[  //用来指定json中那个key作为该列的值
                {name:"id",search: false,align:"center"},//这是列属性对象配置信息
                {name:"title",editable:true,align:"center",editoptions:{required:true}},
                {name:"url",editable:true,align:"center",edittype:"file",
                    formatter:function(value,options,row){
                        return "<img src='"+value+"' style='width:100%'/>";
                    },editoptions:{enctype:"multipart/form-data"}
                },
                {name:"href",editable:true,align:"center",editoptions:{required:true}},
                {name:"create_date",editable:true,align:"center",editoptions:{required:true}},
                {name:"description",editable:true,align:"center",editoptions:{required:true}},
                {name:"status",editable:true,align:"center",formatter:function (data) {
                        if (data=="1"){
                            return "展示";
                        }else return "冻结";
                    },edittype:"select",editoptions:{value:"1:展示;2:冻结"}},
            ],
            pager:"#pager",//指定分页工具栏
            rowNum:4,//每页显示记录数  rowNum最是rowList一个子元素
            rowList:[2,5,10],//出现下列列表 选择每页展示记录数
            viewrecords:true,//显示总条数
            editurl:'${pageContext.request.contextPath}/banner/edit',//执行编辑时请求url  (添加 oper:add 删除 del  更新 edit)
            caption:"用户列表",//指定表格标题
            toolbar:[true,'top'],//给表格指定工具栏  如果工具栏位置为top 工具栏id为:t_tableid  下面位置tb_tableid
            multiselect:true,//开启多选
            gridComplete:function () {
                $("#t_empList").empty().append("<button class='btn btn-warning' onclick='saveRow();'>添加</button>")
            }

        }).navGrid(
            '#pager',//参数1: 分页工具栏id
            {edit : true, add : true, del : true,
                edittext:"编辑",addtext:"添加",deltext:"删除"
            },   //参数2:开启工具栏编辑按钮
            // edit add del
            {
                // frm ---> 表单对象
                closeAfterEdit:true,
                // frm ---> 表单对象
                beforeShowForm:function (frm) {
                    frm.find("#url").attr("disabled",true);
                },
            },{
                    closeAfterAdd:true,
                    afterSubmit:function (response,postData) {
                        var bannerID = response.responseJSON.bannerId;
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/banner/upload",
                            datatype:"json",
                            type:"post",
                            data:{bannerId:bannerID},
                            // 指定的上传input框的id
                            fileElementId:"url",
                            success:function (data) {
                                // 输出上传成功
                                // jqgrid重新载入
                            }
                        })
                        return postData;
                    }
                },{
                     closeAfterDel:true
                });


    });



</script>

<div class="col-sm-10">
    <div class="page-header">
        <h1>轮播图片列表</h1>
    </div>
    <!--创建jqgrid-->
    <table id="userList"></table>

    <!--创建分页工具栏-->
    <div style="height: 50px" id="pager">

    </div>
</div>