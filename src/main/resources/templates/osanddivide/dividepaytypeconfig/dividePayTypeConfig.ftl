<!DOCTYPE html>
<#--<#include "../data/common/header.html">-->
<html>
<head>
    <meta charset="utf-8">
    <title>分成支付方式配置</title>
    <!-- 引入 echarts.js -->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <#--只有css 外部样式用link  像js文件其他文件都用其他都用src-->
<#--    <link rel="stylesheet" href="../layui/css/layui.css">-->
<#--    <script src="../layui/js/layui.all.js"></script>-->
<#--    <script src="../layui/modules/layer.js"></script>-->

   <#-- <link rel="stylesheet" href="../layui/css/layui.css">-->
    <#--<link rel="stylesheet" href="../layui/css/layui.css">-->


    <#--layui样式-->
    <link rel="stylesheet" href="../res/layui/css/layui.css">
    <#--自己的样式-->
    <link rel="stylesheet" href="../res/own/main.css">
    <script src="../res/layui/layui.js"></script>



    <style type="text/css">
        /*总体需要的样式*/
        body{height: 598px;width: 99%;background: white;}
        /*左右结构样式*/
        .left{width: 20%;height: 100%;background: #001529;}
        .right{width: 79%;height: 100%;}
        .f-left{float: left; border: 1px solid red;}
        /*右边区域中的div的样式*/
        .header{height: 15%;margin-bottom: 10px;height: 15%;padding: 15px 15px 0;}
        /*左侧列表*/
        .left-ul{width: 100%;padding:0;margin:0;list-style:none}
        .left-ul-li{    width: 87%;height: 30px;padding: 0;margin: 0;list-style: none;color: hsla(0,0%,100%,.7);padding-left: 25px;margin: 15px 0 0 0;font-size: 13px;}
        .left-ul-li:hover{cursor: pointer;color: greenyellow;}
        .user{}
        .loginout:hover{cursor: pointer;color: black;}
        .title-table tr:first-child td{
            border: 1px solid darkgrey;
            text-align: center;
            padding: 10px;;
            background: grey;
            color: #FFFFFF;
        }
        .contet-table tr td{
            border: 1px solid darkgrey;
            text-align: center;
            padding: 10px;;
        }

        .logo{float: left;}
        .user{float: right;}
        .loginout{margin-left: 30px;color: #999;float: right;}
        .img-logo{height: 41px;}
        .back-grey{
            background: #f5f7f9;
            height: 8%;
        }
        .touch-model{
            cursor: pointer;
            color: chocolate;
            float: right;
            margin-right: 20px;
        }


        .btn-class{
            padding: 3px 12px;
            background-color: #098be7;
            color: #ffff;
            border: none;
            border-radius: 3px;
            width: 80px;
            height: 30px;
            margin-left: 10px;
        }

        .config-param>input{
            padding: 5px;
            border: 1px solid #E5E5E5;
            border-radius: 2px;
        }
        .open-window-config input{
            padding: 5px;
            border: 1px solid #E5E5E5;
            border-radius: 2px;
            width: 380px;
        }
        .open-window-config select{
            padding: 5px 10px;
            height: 30px;
            width: 392px;
            line-height: 30px;
            border: 1px solid #e0e0e0;
        }

        #reportCfDetail select,#reportCfDetail input{
            background: #ebebe4;
        }
        .param-title{
            margin-left: 10px;
        }
        .btn-opt{
            margin:20px 0 0 0;
            padding-right: 40px;
        }
        #configList th,td{
           /* border: 1px solid #ebe7dd;*/
            color: #666666;
           /* position: relative;*/
        }
        .manager-wrap-1{
            height: 8%;
        }
        .pay-table{
            margin: 30px 0px 0px 35px;
            border-collapse: separate;
            border-spacing: 5px 10px;
        }

    </style>

    <#--页面跳转-->
    <script>
        function showDivideExceptionStatistic() {
            window.location.href = "http://localhost:8080/osanddivide/toDivideExeptionDataStatistic";
        }
        function showDividePayTypeConfig() {
            window.location.href = "http://localhost:8080/osanddivide/toPayCodeConfig";
        }
    </script>
</head>
<body>
    <!--左-->
    <div class="left f-left">
        <ul class="left-ul">
            <li class="left-ul-li" txt="分成支付方式配置" onclick="showDividePayTypeConfig()">分成支付方式配置</li>
            <li class="left-ul-li" txt="分成异常统计" onclick="showDivideExceptionStatistic()">分成异常统计</li>
            <li class="left-ul-li" txt="科目设置组配置" onclick="showSubjectGroup()">科目设置组配置</li>
            <li class="left-ul-li" txt="造异常页面" onclick="showCreateException('仓库信息配置')">造异常页面</li>
            <li class="left-ul-li" txt="分账账单配置" onclick="showInfo('仓库信息配置')">分账账单配置</li>
            <li class="left-ul-li" txt="批量操作页面" onclick="showInfo('仓库信息配置')">批量操作页面</li>
            <li class="left-ul-li" txt="仓库信息配置" onclick="showInfo('仓库信息配置')">仓库信息配置</li>
        </ul>
    </div>

    <!--右-->
    <div class="right f-left" >
        <!--头-->
        <div class="header back-grey" onclick="">
                    <span class="logo">
                        <img class="img-logo" src="data:image/jpeg;base64,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">
                        <img class="img-logo" alt="苏宁易购" src="//image1.suning.cn/uimg/cms/img/159642507148437980.png">
                    </span>
            <span class="loginout" onclick="xxx()">退出</span>
            <span class="user">用户：abc</span>
            <span class="touch-model" onclick="touchModel()">验证一个点击事件有几种触发方式</span>
        </div>

        <#--查询条件-->
        <div class="manager-wrap-1">
            <form action="" method="post" id="queryForm" name="queryForm">
                <p class="manager-title">分成支付方式配置</p>
                <div class="clear-fix configure-select config-param">
                    <span class="param-title">支付方式：</span>
                    <input type="text"  id="payCode" placeholder="请输入支付方式">

                    <span class="param-title">银行类型编码：</span>
                    <input type="text"  id="bankTypeCode" placeholder="请输入银行类型编码">


                    <button type="button" class="btn btn-primary btn-class" id="queryBtn"  onclick="queryPayTypeLimit();">查询</button>
                    <button type="button" class="btn btn-primary btn-class" id="saveBtn"  onclick="saveOpenWindow();">新增</button>

                </div>
            <form>
        </div>

        <#--分页查询数据列表展示页面-->
        <div class="table-data-limitData" style=" height: 80%">
            <div class="table-cont-limit">
                <table id="pay-type-limit-data" lay-data="{id: 'payTypelimitData'}"></table>
            </div>
        </div>

        <#--新增页面弹窗-->
        <div class="pay-type-add" style="display: none" id="payTypeAdd">
            <#--弹窗页面-->
            <table class="pay-table">
                <tr>
                    <td width="20%"><span style="color: red">*</span>支付方式：</td>
                    <td><input id="addPayCode" type="text" class="normal-input" placeholder="请输入支付方式" maxlength="100"></td>
                </tr>
                <tr>
                    <td width="20%">银行种类编码：</td>
                    <td><input id="addBankTypeCode" type="text" class="normal-input" placeholder="请输入银行种类编码" maxlength="100"></td>
                </tr>
            </table>
            <#--保存、返回按钮-->
            <div class="add-open-window" style="text-align: center;margin-top: 40px;">
                <a href="javascript:" class="btn-class" onclick="clickClose();">返回</a>
                <a href="javascript:void(0);" class="btn-class" onclick="savePayTypeConfig();">保存</a>
            </div>
        </div>
    </div>

    <#--增删改查-->
    <script>

        $(function () {
            queryPayTypeLimit();
        });

        function queryPayTypeLimit() {

            var payCode = $("#payCode").val();
            var bankTypeCode = $("#bankTypeCode").val();

            //拼接参数
            var params = {
                payCode : payCode,
                bankTypeCode : bankTypeCode
            };
            var options = {
                id : 'payTypelimitData',
                elem : '#pay-type-limit-data',
                url : '../osanddivide/queryPayTypeLimit',
                method : 'post',
                request : {
                    pageName : 'page', // 页码的参数名称，默认：page
                    limitName : 'rows', // 每页数据量的参数名，默认：limit
                },
                response : {
                    countName : 'total', // 数据总数的字段名称，默认：count
                    dataName : 'rows' // 数据列表的字段名称，默认：data
                },
                where : params,
                cols : [ [
                    {
                        field : 'payCode',
                        title : '支付方式',
                        align : 'center',
                        width : '20%',
                    }, {
                        field : 'bankTypeCode',
                        title : '银行类型编码',
                        align : 'center',
                        width : '20%'
                    }, {
                        field : 'createTime',
                        title : '创建日期',
                        align : 'center',
                        width : '20%'
                    },{
                        field : 'updateTime',
                        title : '更新时间',
                        align : 'center',
                        width : '20%'
                    },{
                        title : '操作',
                        fixed : 'right',
                        align : 'center',
                        width : '20%'
                    }] ],

                cellMinWidth : 10,
                page : true,
                done :function (data) {
                    layer.close(index); // 返回数据关闭loading
                    if (data.code) {
                        layer.alert("查询数据失败，请刷新页面重试，或请联系管理员处理", {
                            icon : 2
                        })
                    }
                }
            };
            layui.use(['table'], function () {
                var table = layui.table;
                if (options.url) {// 调用接口才用load
                    index = layer.load(2); // 添加laoding,0-2两种方式
                }
                table.render(options);
            });
        }

        function saveOpenWindow(){

            //这种方法不能用在springboot 项目里面不能使用
            // index = layer.open({
            //     type: 1,
            //     title: "新增",
            //     shift: 1,
            //     offset: '150px',
            //     area: ['600px', '300px'],
            //     skin: 'layui-layer-rim', //加上边框
            //     content: $('#payTypeAdd'),
            //     cancel: function() {}
            // });


            layui.use("layer", function() {
                var layer = layui.layer;
                layer.open({
                    type : 1,
                    skin : 'layui-layer-molv',
                    shift: 1,
                    offset: '150px',
                    area : [ '480px','300px' ], // 只设置宽度 如果要设置宽度、高度： ['500px','300px']
                    content : $("#payTypeAdd"), // 这里content是一个html
                    title : "新增支付方式",
                    yes : function(index, layero) {
                        savePayTypeConfig();
                    },
                    success : function() {
                        // 清空表单数据
                        /*$('#payTypeAdd')[0].reset();*/
                    }
                });
            });
        }

        function savePayTypeConfig() {
            var addPayCode = $("#addPayCode").val();
            var addBankTypeCode = $("#addBankTypeCode").val();
            $.ajax({
                url:'../osanddivide/savePayTypeConfig',
                type:'post',
                data:{
                    payCode: addPayCode,
                    bankTypeCode: addBankTypeCode
                },
                success: function (data) {
                    if (data) {
                        if (data.success) {
                            alertInfo(data.message, 1);
                        } else {
                            alertInfo(data.message, 2);
                        }
                    }
                }
            });
        }




    </script>

    <#--信息提示框、关闭-->
    <script>
        // 信息提示弹窗
        function alertInfo(info, icon) {
            layer.alert(info, {
                // 样式类名 自定义样式
                skin: 'layui-layer-molv',
                // 是否有关闭按钮
                closeBtn: 0,
                icon: icon,
                yes: function (index) {
                    layer.close(index);
                    window.location.reload();
                }
            });
        }

        // 自定义失败弹框
        function errorMsg(msg, icon) {
            layer.alert(msg, {
                title: "出错了",
                move: false,
                shift: 6,
                icon: icon
            });
        }

        //  执行关闭
        // function clickClose() {
        //     parent.layer.close(index);
        // }

        function clickClose() {
            layer.closeAll();
        }
    </script>
</body>
</html>
