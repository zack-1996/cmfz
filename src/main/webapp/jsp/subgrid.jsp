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
    <script src="../jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <title>subgrid</title>
</head>
<body>
    <script>
        $(function () {
            // 创建父级JqGrid表格
            $("#table").jqGrid(
                {
                    url :"${pageContext.request.contextPath}/album/findAll",
                    datatype : "json",
                    height : 500,
                    colNames : [ 'Id', '题目','评分','作者','播音','状态','章节数','描述','图片','出版时间','上架时间' ],
                    colModel : [
                        {name:"id",search: false,align:"center"},//这是列属性对象配置信息
                        {name:"title",editable:true,align:"center",editoptions:{required:true}},
                        {name:"score",editable:true,align:"center",editoptions:{required:true}},
                        {name:"author",editable:true,align:"center",editoptions:{required:true}},
                        {name:"broadcast",editable:true,align:"center",editoptions:{required:true}},
                        {name:"status",editable:true,align:"center",formatter:function (data) {
                                if (data=="1"){
                                    return "展示";
                                }else return "冻结";
                            },edittype:"select",editoptions:{value:"1:展示;2:冻结"}
                        },
                        {name:"count",editable:true,align:"center",editoptions:{required:true}},
                        {name:"description",editable:true,align:"center",editoptions:{required:true}},
                        {name:"img",editable:true,align:"center",edittype:"file",
                            formatter:function(value,options,row){
                                return "<img src='"+value+"' style='width:100%'/>";
                            },editoptions:{enctype:"multipart/form-data"}
                        },
                        {name:"publish_date",editable:true,align:"center",editoptions:{required:true}},
                        {name:"create_date",editable:true,align:"center",editoptions:{required:true}},


                    ],
                    rowNum : 2,
                    rowList : [ 8, 10, 20, 30 ],
                    pager : '#page',
                    sortname : 'id',
                    viewrecords : true,
                    sortorder : "desc",
                    multiselect : false,
                    editurl:'${pageContext.request.contextPath}/album/edit',//执行编辑时请求url  (添加 oper:add 删除 del  更新 edit)
                    // 开启多级表格支持
                    subGrid : true,
                    caption : "Subgrid案例",
                    autowidth:true,
                    styleUI:"Bootstrap",
                    // 重写创建子表格方法
                    subGridRowExpanded : function(subgrid_id, row_id) {
                        addTable(subgrid_id,row_id);
                    },
                    // 删除表格方法
                    subGridRowColapsed : function(subgrid_id, row_id) {

                    }
                });
            $("#table").jqGrid('navGrid', '#page',
                {edit : true, add : true, del : true,
                    edittext:"编辑",addtext:"添加",deltext:"删除"
                },
                // {} --> edit {}-->add  {}-->del
                {
                    closeAfterEdit:true,
                    // frm ---> 表单对象
                    beforeShowForm:function (frm) {
                        frm.find("#img").attr("disabled",true);
                    },
                },{
                    closeAfterAdd:true,
                    afterSubmit:function (response,postData) {
                        var albumId = response.responseJSON.albumId;
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/album/upload",
                            datatype:"json",
                            type:"post",
                            data:{albumId:albumId},
                            // 指定的上传input框的id
                            fileElementId:"img",
                            success:function (data) {
                                // 输出上传成功
                                // jqgrid重新载入
                                $("#table").trigger("reloadGrid");
                            }
                        })
                        return postData;
                    }
                },{
                    closeAfterDel:true
                }

            );
        })
        // subgrid_id 下方空间的id  row_id 当前行id数据
        function addTable(subgrid_id,row_id) {

            // 声明子表格|工具栏id
            var subgridTable = subgrid_id + "table";
            var subgridPage = subgrid_id + "page";
            // 根据下方空间id 创建表格及工具栏
            $("#"+subgrid_id).html("<table id='"+subgridTable+"'></table><div style='height: 50px' id='"+subgridPage+"'></div>")
            // 子表格JqGrid声明
            $("#"+subgridTable).jqGrid({
                url : "${pageContext.request.contextPath}/chapter/findAll?album_id="+row_id,
                datatype : "json",
                colNames : [ 'id', 'Name',"File","Size","Time","Create_Time" ],
                colModel : [
                    {name:"id",search: false,align:"center"},//这是列属性对象配置信息
                    {name:"title",editable:true,align:"center",editoptions:{required:true}},
                    {name:"url",editable:true,align:"center",width:"250px",editoptions:{required:true},edittype:"file",
                        formatter:function (value,options,row) {
                            return "<audio controls='controls' src='"+row.url+"'></audio>";
                        }
                    },
                    {name:"size",editable:false,align:"center",editoptions:{required:true}},
                    {name:"time",editable:false,align:"center",editoptions:{required:true}},
                    {name:"create_time",editable:true,align:"center",editoptions:{required:true}},
                ],
                rowNum : 20,
                pager : "#"+subgridPage,
                sortname : 'num',
                sortorder : "asc",
                height : '100%',
                editurl:'${pageContext.request.contextPath}/chapter/edit',//执行编辑时请求url
                styleUI:"Bootstrap",

                autowidth:true
            });
            $("#" + subgridTable).jqGrid('navGrid',
                "#" + subgridPage,
                {edit : true, add : true, del : true,
                    edittext:"编辑",addtext:"添加",deltext:"删除"
                },
                // {} --> edit {}-->add  {}-->del
                {
                    closeAfterEdit:true,
                    // frm ---> 表单对象
                    beforeShowForm:function (frm) {
                        frm.find("#url").attr("disabled",true);
                    },
                },{
                    closeAfterAdd:true,
                    afterSubmit:function (response,postData) {
                        var chapterId = response.responseJSON.chapterId;
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/chapter/upload?album_id="+row_id,
                            datatype:"json",
                            type:"post",
                            data:{chapterId:chapterId},
                            // 指定的上传input框的id
                            fileElementId:"url",
                            success:function (data) {
                                // 输出上传成功
                                // jqgrid重新载入
                                $("#"+subgridTable).trigger("reloadGrid");
                            }
                        })
                        return postData;
                    }
                },{
                    closeAfterDel:true
                });
        }

    </script>
    <table id="table"></table>
    <div style="height: 50px" id="page"></div>
</body>
</html>