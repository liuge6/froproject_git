<!DOCTYPE html>
<#include "../data/common/header.html">
<html>
<head>
    <meta charset="utf-8">
    <title>第一个 ECharts 实例</title>
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
        .left{width: 20%;height: 1200px;background: #001529;}
        .right{width: 79%;height: 1200px;}
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
            height: 4%;
        }
        .touch-model{
            cursor: pointer;
            color: chocolate;
            float: right;
            margin-right: 20px;
        }
        .add-btn{
            margin-left: 20px;
        }
        .show-model{
            overflow: hidden;
        }
        .show-bar-left{
            float: left;
        }
        .show-pie-right{
            width: 50%;
            float: right;
        }
        .table-data-limitData{
            height: 500px;
        }
        .query-param{
            margin-left: 20px;
        }
        .show-line{

        }
        #insertTable tr td:nth-child(1){
            width: 50%;
            text-align: center;
        }
        .input-tr{
            padding: 5px;
            margin: 4px;
        }
        .table-cont{
            margin: 0px 20px;
            background: #fff;
        }
        .normal-input-select {
            width: 180px;
            height: 28px;
        }
        .select-right{
            margin-left: 10px;
        }

    </style>
</head>
<body>
<!--左-->
<div class="left f-left">
    <ul class="left-ul">
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
    <div class="header back-grey" onclick="aaa()">
				<span class="logo">
					<img class="img-logo" src="data:image/jpeg;base64,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">
                    <img class="img-logo" alt="苏宁易购" src="//image1.suning.cn/uimg/cms/img/159642507148437980.png">
                    <div class="layui-btn layui-btn-sm add-btn" onclick="addData()" id="insertData">新增</div>
                </span>
        <!---->
        <span class="loginout" onclick="xxx()">退出</span>
        <span class="user">用户：abc</span>
        <span class="touch-model" onclick="touchModel()">验证一个点击事件有几种触发方式</span>
    </div>

    <#--展示模型柱状图饼图-->
    <div class="show-model">
        <div class="show-bar-left" id="showBar"  style="width: 50%;height:350px;">

        </div>

        <div class="show-pie-right" id="showPie" style="width: 50%;height:350px;">

        </div>
    </div>

    <#--表格数据-->
    <div class="table-data-limitData">
        <div class="table-cont">
            <table id="content-data" lay-data="{id: 'tableData'}"></table>
        </div>
    </div>

    <#--查询条件-->
    <div class="query-param">
        <select name="modules" lay-verify="required" lay-search="" class="normal-input-select">
            <option value="1">layer</option>
            <option value="2">form</option>
            <option value="3">layim</option>
            <option value="4">element</option>
        </select>
        <select name="modules" lay-verify="required" lay-search="" class="normal-input-select select-right">
            <option value="1">7天</option>
            <option value="2">30天</option>
        </select>
    </div>

    <#--折线图展示-->
    <div class="show-line" id="showLine" style="width: 100%;height:280px;">

    </div>

    <#--新增按钮弹窗页面-->
    <div class="add-dialog-cont" style="display:none;overflow-y:auto;" id="exceptionDataAddDiv">
        <form id="exceptionDataAddForm">
            <table id="insertTable">

            </table>
        </form>
    </div>

</div>

<#--页面跳转-->
<script>
    function showDivideExceptionStatistic() {
        window.location.href = "http://localhost:8080/osanddivide/toDivideExeptionDataStatistic";
    }
</script>


<#--新增页面-->
<script>
    //循环获取默认表名
    $(function () {
        var tableList =["nbdivide_exception_cloudcard_handle", "nbdivide_exception_co_handle"
            , "nbdivide_exception_fi_handle"
            , "nbdivide_exception_receipt_handle", "nbdivide_exception_restore_bill_handle"
            ];

        var _html = "";
        for(var i = 0;i < tableList.length;i++) {
            _html += "<tr>";
            _html += "<td>";
            _html += "<span  id='tableNameAdd' name=\"tableName\" class=\"input-tr\">" + tableList[i] + "</span>";
            _html += "</td>";
            _html += "<td>";
            _html += "<input type=\"text\" optTableName=" + tableList[i] + " class=\"table-data input-tr\" name=\"tax\" placeholder=\"请输入异常量\"/>";
            _html += "</td>";
            _html += "</tr>";
        };
        $("#insertTable").html(_html);
    });

    /*新增数据弹框*/
    function addData() {
        layui.use("layer", function() {
            var layer = layui.layer;
            layer.open({
                type : 1,
                skin : 'layui-layer-molv',
                area : [ '480px','300px' ], // 只设置宽度 如果要设置宽度、高度： ['500px','300px']
                content : $("#exceptionDataAddDiv"), // 这里content是一个html
                title : "新增异常展示",
                btn : [ '保存', '取消' ],
                yes : function(index, layero) {
                    addSave();
                    layer.close(index);
                },
                success : function() {
                    // 清空表单数据
                    $('#exceptionDataAddForm')[0].reset();
                }
            });
        });
    }

    /**
     * 新增数据保存
     * 前台数据以 数组格式传到后台 用join拼接下
     */
    function addSave() {
        var dataTableList = [];
        var valueList = [];
        $(".table-data").each(function (index, dom) {
            var tableNmae = $(dom).attr("optTableName");
            var value = $(dom).val();

            if(!value) {
                value = -1;
            } else if(parseInt(value) == 0) {
                value = 0;
            }
            dataTableList.push(tableNmae);
            valueList.push(value);
        });
        // console.log(dataTableList.join("-"));
        // console.log(valueList.join("&"));
        // 序列化表格内的内容为字符串
        $.ajax({
            type : 'post',
            url : "${path}/osanddivide/addExceptionData",
            data : {
                'tableListStr': dataTableList.join("-"),
                'valueListStr': valueList.join("&")
            },
            async : false,
            success : function(data) {
                if (data.success) {
                    alertInfo(data.message, 1);
                } else {
                    alertInfo(data.message, 2);
                }
            }
        });
    }

    /**
     * 提示信息弹窗
     * @param info
     */
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
            },

        });
    }

</script>

<#--表格-->
<script type="text/javascript">

    var tableName = $("#tableName select[name='tableName']").val();
    var value = $(".table-data input[name='tax']").val();

    $(function () {
        queryByLimitData();
    })
    function queryByLimitData() {

        // 拼接参数
        var params = {
            tableName : tableName,
            exceptionAllgross : value
        };
        var options = {
            id : 'tableData',
            elem : '#content-data',
            url : "${path}/osanddivide/queryByLimitData?random=" + Math.random(),
            method : "post",
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
                    field : 'tableName',
                    title : '表名',
                    align : 'center',
                    width : '30%',
                }, {
                    field : 'exceptionAllgross',
                    title : '累积总量',
                    align : 'center',
                    width : '25%'
                }, {
                    field : 'createDate',
                    title : '创建日期',
                    align : 'center',
                    width : '25%'
                },{
                    field : 'createTime',
                    title : '创建时间',
                    align : 'center',
                    width : '20%'
                },] ],
            cellMinWidth : 80,
            page : true, // 开启分页
            done : function(res, curr, count) {
                layer.close(index); // 返回数据关闭loading
                if (res.code) {
                    layer.alert("查询数据失败，请刷新页面重试，或请联系管理员处理", {
                        icon : 2
                    })
                }
                var date = new Date();
                var time = date.toLocaleDateString().replaceAll("/", "-");

                //判断昨天数据或者前天数据是否
                if (res.rows.length == 0) {
                    $("#titleInfo").show();
                    return false;
                }
                //获取后台传过来的list中的数据
                if (res.rows[0].createDate === time) {
                    $("#titleInfo").hide();
                } else {
                    $("#titleInfo").show();
                }
            }
        };
        layui.use([ 'table' ], function() {
            var table = layui.table;
            if (options.url) {// 调用接口才用load
                index = layer.load(2); // 添加laoding,0-2两种方式
            }
            table.render(options);
        });
    }
</script>




<#--柱形图-->
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('showBar'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '第一个 ECharts 实例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

<#--饼图-->
<script>

    var myChart = echarts.init(document.getElementById('showPie'));
    var data = genData(50);

    option = {
        title: {
            text: '同名数量统计',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: data.legendData,

            selected: data.selected
        },
        series: [
            {
                name: '姓名',
                type: 'pie',
                radius: '55%',
                center: ['40%', '50%'],
                data: data.seriesData,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };




    function genData(count) {
        var nameList = [
            '赵', '钱', '孙', '李', '周', '吴', '郑', '王', '冯', '陈', '褚', '卫', '蒋', '沈', '韩', '杨', '朱', '秦', '尤', '许', '何', '吕', '施', '张', '孔', '曹', '严', '华', '金', '魏', '陶', '姜', '戚', '谢', '邹', '喻', '柏', '水', '窦', '章', '云', '苏', '潘', '葛', '奚', '范', '彭', '郎', '鲁', '韦', '昌', '马', '苗', '凤', '花', '方', '俞', '任', '袁', '柳', '酆', '鲍', '史', '唐', '费', '廉', '岑', '薛', '雷', '贺', '倪', '汤', '滕', '殷', '罗', '毕', '郝', '邬', '安', '常', '乐', '于', '时', '傅', '皮', '卞', '齐', '康', '伍', '余', '元', '卜', '顾', '孟', '平', '黄', '和', '穆', '萧', '尹', '姚', '邵', '湛', '汪', '祁', '毛', '禹', '狄', '米', '贝', '明', '臧', '计', '伏', '成', '戴', '谈', '宋', '茅', '庞', '熊', '纪', '舒', '屈', '项', '祝', '董', '梁', '杜', '阮', '蓝', '闵', '席', '季', '麻', '强', '贾', '路', '娄', '危'
        ];
        var legendData = [];
        var seriesData = [];
        var selected = {};
        for (var i = 0; i < count; i++) {
            name = Math.random() > 0.65
                ? makeWord(4, 1) + '·' + makeWord(3, 0)
                : makeWord(2, 1);
            legendData.push(name);
            seriesData.push({
                name: name,
                value: Math.round(Math.random() * 100000)
            });
            selected[name] = i < 6;
        }

        return {
            legendData: legendData,
            seriesData: seriesData,
            selected: selected
        };

        function makeWord(max, min) {
            var nameLen = Math.ceil(Math.random() * max + min);
            var name = [];
            for (var i = 0; i < nameLen; i++) {
                name.push(nameList[Math.round(Math.random() * nameList.length - 1)]);
            }
            return name.join('');
        }
    }

    myChart.setOption(option);
</script>

<#--折线图-->
<script>
    var myChart = echarts.init(document.getElementById('showLine'));
    option = {
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
        }]
    };

    myChart.setOption(option);

</script>



<script>
    /*方式一 ：若没有onclick方式时*/
    $(".touch-model").on("click", function () {
        alert("第一种验证触发方式");
    })

    /*方式二 ：若没有onclick方式时，，第二种写法*/
    $(".touch-model").click(function () {
        alert("这是第二种方式");
    })

    /*方式三 ：若有onclick方式时，，第三种写法*/
    function touchModel() {
        alert("这是第三种方式");
    }

    /*一进页面就刷新则是这个写法*/
    // $(function () {
    //     touchModel();
    // })

    /*应用场景，一定要使用 $(this) 选中当前的窗口，若不加则不能出现此效果*/
    // $(".left-ul").on("click", ".left-ul-li", function () {
    //     alert("我是" + $(this).attr("txt"));
    // })

    /*验证窗口的触发事件*/
    // function aaa() {
    //     alert("这是图片的触发事件，老板你好！");
    // }
    /*验证窗口的触发事件*/
    // $(document).on("click", function () {
    //     alert("第一种验证触发方式document");
    // })

</script>
<script type="application/javascript">

    //		function txtAlert(data) {
    //			alert("我是：" + $(data).attr("txt"));
    //		}

    //		$(".left-ul-li").click(function(e) {
    //			alert("我是：" + $(this).attr("txt"));
    //		});

    // $("ul").on("click", ".left-ul-li", function(e) {
    //     alert("我是：" + $(this).attr("txt"));
    // });
</script>
<script>
    $(".left-ul").on("click", ".left-ul-li", function (txt) {
        var href = $(this).find("a").attr('href');
        $('#left-ul-li').empty();
        $.ajax({
            url:"${path}/osanddivide" + href,
            type:"post",
            success:function () {
                alert("是否跳了新页面");
            }
        });

    })
</script>
<#--兄弟浩的jquery逻辑-->
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


    $(document).on("click", ".loginout", function(e) {
        alert("退出成功！");
        e.stopPropagation();
    });

</script>

</body>
</html>
