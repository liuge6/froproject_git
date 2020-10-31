<!DOCTYPE html>
<html>
<#include "../data/common/header.html">
<head>
    <meta charset="{CHARSET}">
    <title></title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../res/layui/css/layui.css">
    <link rel="stylesheet" href="../layui/js/layui.js">


    <script type="text/javascript" src="./layui/js/layui.js" ></script>
    <style type="text/css">
        /*总体需要的样式*/
        body{height: 598px;width: 99%;background: white;}
        /*左右结构样式*/
        .left{width: 20%;height: 598px;background: #001529;}
        .right{width: 79%;height: 598px;}
        .f-left{float: left;}
        /*右边区域中的div的样式*/
        .header{height: 15%;margin-bottom: 10px;height: 15%;padding: 15px 15px 0;}
        .content{height: 57%;padding: 15px;margin-bottom: 15px;}
        .fotter{height: 15%;}
        /*左侧列表*/
        .left-ul{width: 100%;padding:0;margin:0;list-style:none}
        .left-ul-li{    width: 87%;height: 30px;padding: 0;margin: 0;list-style: none;color: hsla(0,0%,100%,.7);padding-left: 25px;margin: 15px 0 0 0;font-size: 13px;}
        .left-ul-li:hover{cursor: pointer;color: #FFFFFF;}
        .user{}
        .loginout:hover{cursor: pointer;color: black;}
        .fotter {
            height: 16%;
            text-align: center;
            line-height: 89px;
            font-size: 13px;
            color: #666;
        }
        .contet-table{
            width: 100%;
            padding: 10px;
            border-collapse: collapse;
        }
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
        .img-logo{    height: 50px;width: auto;}
        .back-grey{background: #f5f7f9;}
        .table-div{height: 75%;overflow-y: scroll;}
        .page-div{height: 10%;}
        .title-table-div{}


        .data-num{width: 10%;}
        .data-title{width: 35%;}
        .data-price{width: 15%;}
        .data-author{width: 15%;}
        .data-option{}

        .scrollbar {
            width : 15px;
            height: 300px;
            margin: 0 auto;
        }
        .test-1::-webkit-scrollbar {
            /*滚动条整体样式*/
            width : 10px;  /*高宽分别对应横竖滚动条的尺寸*/
            height: 1px;
        }
        .test-1::-webkit-scrollbar-thumb {
            /*滚动条里面小方块*/
            border-radius: 10px;
            box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
            background   : #535353;
        }
        .test-1::-webkit-scrollbar-track {
            /*滚动条里面轨道*/
            box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            background   : #ededed;
        }
    </style>
</head>
<body>
<!--左-->
<div class="left f-left">
    <ul class="left-ul">
        <li class="left-ul-li" txt="我是文档">文档</li>
        <li class="left-ul-li" txt="qq群">qq群</li>
        <li class="left-ul-li" txt="下载列表">下载列表</li>
    </ul>
</div>

<!--右-->
<div class="right f-left" >
    <!--头-->
    <div class="header back-grey" onclick="aaa()">
				<span class="logo">
					<img class="img-logo" src="data:image/jpeg;base64,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">
				</span>
        <!---->
        <span class="loginout">退出</span>
        <span class="user">用户：abc</span>
    </div>

    <!--内容-->
    <div class="content back-grey">
        <div class="title-table-div">
            <table class="contet-table title-table">
                <tr >
                    <td class="data-num">序号</td>
                    <td class="data-title" style="    width: 34%;">标题</td>
                    <td class="data-price">价格</td>
                    <td class="data-author">作者</td>
                    <td class="data-option">操作</td>

                </tr>
            </table>
        </div>
        <div class="table-div">
            <table class="contet-table" id="dataContent">
                <!--<tr >
                    <td class="data-num">1</td>
                    <td class="data-title">java编程思想</td>
                    <td class="data-price">100</td>
                    <td class="data-author">XXX</td>
                    <td class="data-option">
                        <span>编辑</span>
                        <span>删除</span>
                    </td>
                </tr>
                <tr >
                    <td class="data-num">2</td>
                    <td class="data-title">java编程思想</td>
                    <td class="data-price">100</td>
                    <td class="data-author">XXX</td>
                    <td class="data-option">
                        <span>编辑</span>
                        <span>删除</span>
                    </td>
                </tr>
                <tr >
                    <td class="data-num">3</td>
                    <td class="data-title">java编程思想</td>
                    <td class="data-price">100</td>
                    <td class="data-author">XXX</td>
                    <td class="data-option">
                        <span>编辑</span>
                        <span>删除</span>
                    </td>
                </tr>-->
            </table>

            <div class="page-div"></div>
        </div>

        <!--脚-->
        <div class="fotter back-grey" >
            <div id="bottomTxt"></div>
            <div class="sss" style="height: 300px;width: 100%;">
                <span>标题</span>
                <input id="bTitle" type="text"/>
                <span>价格</span>
                <input id="bPrice" type="text"/>
                <span>作者</span>
                <input id="bAuth" type="text"/>
                <button type="button" class="layui-btn layui-btn-xs" onclick="queryInfo()">测试</button>
                <span id="saveEdit"  class="layui-btn layui-btn-warm layui-btn-xs" onclick="saveEdit(this)" bId="">保存</span>

                <div>
                    <a href="http://localhost:8080/book-info/testExport" target="_blank" style="color: #5FB878" class="la"><i class="layui-icon"></i>导出_sml</a>
                </div>

                <div>
                    <a href="http://localhost:8080/book-info/testExportByTwoIdea" target="_blank" style="color: #5FB878" class="la"><i class="layui-icon"></i>导出_sml</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="application/javascript">
    //		function txtAlert(data) {
    //			alert("我是：" + $(data).attr("txt"));
    //		}

    //		$(".left-ul-li").click(function(e) {
    //			alert("我是：" + $(this).attr("txt"));
    //		});

    $("ul").on("click", ".left-ul-li", function(e) {
        alert("我是：" + $(this).attr("txt"));
    });
</script>

<script type="application/javascript">
    $(function() {

        //获取图书数据列表
        getInfos();
    });

    //获取图书数据列表
    function getInfos() {
        $.ajax({
            url: "${path}/book-info/book-info-list",
            type: "POST",
            data: {},
            dataType: "html",
            success: function(msg) {
                $("#dataContent").html(msg);
            }
        });
    }



    //		function loginoput(e) {
    //			alert("退出成功！");
    //			e.stopPropagation();
    //			alert("退出成aaaaaaa功！");
    //		}


    //		$(".loginout").click(function(e) {
    //			alert("退出成功！");
    //			e.stopPropagation();
    //		});

    $(".loginout").click(function(e) {
        alert("退出成功！");
        e.stopPropagation();
    });


    //		$(document).on("click", ".loginout", function(e) {
    //			alert("退出成功！");
    //			e.stopPropagation();
    //		});




    function aaa() {
        alert("123");
    }

    $(function () {

    });

    $("#deletefileupload").on("click", function() {
        alert(1)
    });

</script>


<!--查询所有数据-->
<script type="application/javascript">
    function queryInfo() {

    }
</script>

</html>
