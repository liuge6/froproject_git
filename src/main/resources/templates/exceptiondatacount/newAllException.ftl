<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>异常数据统计</title>
    <!-- 引入 echarts.js -->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../res/layui/css/layui.css">
    <link rel="stylesheet" href="../layui/js/layui.js">

    <style type="text/css">
        .analyse-content{
            width: 100%;
        }
        .line-div{
            height: 460px;
            width: 100%;
        }
        .query-param{
            text-align: left;
            height: 60px;
            width: 100%;
            margin-top: 20px;
        }
        .bar-pie-div{
            overflow: hidden;
            margin-top: 20px;
        }
        .pie-chart{
            height: 460px;
            width: 50%;
            float: right;
        }
        .bar-chart{
            height: 460px;
            width: 50%;
            float: left;
        }
        .data-table{
            height: 400px;
            width: 100%;
        }
        .input-tr{
            padding: 5px;
            margin: 10px;
        }
        #insertTable tr td:nth-child(1){
            width: 50%;
            text-align: center;
        }
        #insertTable{
            margin: ;
        }

    </style>
</head>
<body>

<div class="crumb"><span style="color:#00a7eb;font-weight:600;font-size:16px">异常数据统计展示图表</span></div>
<#--页面布局内容-->
<div class="analyse-content">

    <#--查询条件-->
    <div class="query-param">
        <form id="exceptionForm" enctype="multipart/form-data" method="post" class="layui-form">
            <#--异常表名-->
            <div class="layui-inline">

                <div class="layui-input-inline" style="width:300px;margin-right: 30px">
                    <select name="tableName" lay-verify="">
                        <option value="">--请选择表--</option>
                    </select>
                </div>

                <#--查询按钮-->
                <div class="theme-btn" id="query-btn" onclick="query()"><i class="icon-chaxun theme-icon">查询</i></div>
                <div class="transparent-btn"  onclick='addData()'><i class="icon-xinzeng transparent-icon"></i>新增</div>
                <div id="addButtom" value="新增2" style="color: red" onclick="addData()">新增按钮</div>
            </div>
        </form>
    </div>

    <#--折线-->
    <div class="line-div" id="maintest2">

    </div>

    <#--柱状图及饼图 div-->
    <div class="bar-pie-div">

        <#--柱状图div-->
        <div class="bar-chart" id="maintest3">

        </div>
        <#--饼图div-->
        <div class="pie-chart" id="maintest5">

        </div>
    </div>

    <#--表格-->
    <div class="data-table">
        <div class="table-cont">
            <table id="content" lay-filter="switchconfigfilter" lay-data="{id: 'switchconfigdata'}"></table>
        </div>
    </div>
</div>

<#--新增页面-->
<div class="add-dialog-cont" style="display:none;overflow-y:auto;" id="exceptionDataAddDiv">
    <form id="exceptionDataAddForm" class="layui-form">
        <table id="insertTable">


        </table>
    </form>
</div>

<#--新增按钮弹窗-->
<script>
    $(function () {
        addData();
    })

    function addData() {
        alert(121313);
    }
</script>


<#--折线-->
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('maintest2'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '异常数据7天、30天展示图'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['7天数据', '30天数据']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['2020-10-07','2020-10-08','2020-10-09','2020-10-10','2020-10-11','2020-10-12','2020-10-13','2020-10-14','2020-10-15','2020-10-16','2020-10-17','2020-10-18','2020-10-19','2020-10-20','2020-10-21','2020-10-22','2020-10-23','2020-10-24','2020-10-25','2020-10-26','2020-10-27','2020-10-28','2020-10-29','2020-10-30','2020-10-31','2020-11-01','2020-11-02','2020-11-03','2020-11-04','2020-11-05']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: '7天数据',
                type: 'line',
                stack: '总量',
                data: [220, 182, 191,220, 182, 192, 234]
            },
            {
                name: '30天数据',
                type: 'line',
                stack: '总量',
                data: [31, 42, 80, 88, 51, 25, 15, 53, 93, 20, 85, 98, 36, 34, 96, 38, 56, 22, 16, 6, 26, 48, 67, 81, 1, 70, 30, 59, 21, 16]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

<#--单个柱形图-->
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('maintest3'));

        $.ajax({
            url : "${path}/exceptiondatastatistics/queryByData",
            dataType : 'json',
            type : 'post',
            success : function(data) {
                // 指定图表的配置项和数据
                var option = {
                    title: {text: '异常数据截止当前展示图 '},
                    tooltip: {},
                    legend: {data:['异常表总量']},
                    xAxis: {
                        data: data.tableName
                    },
                    yAxis: {},
                    series: [{
                        name: '异常表总量',
                        type: 'bar',
                        data: data.tableValue
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });
    })

</script>

<#--饼图-->
<script>
    <#--饼图-->
    var myChart = echarts.init(document.getElementById('maintest5'));
    var data = genData(50);
    option = {
        title: {
            text: '异常数据截止最新总占比',
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

<#--表格-->
<script type="text/javascript">

    // 用于业务类型转换


    var tableName = $("#tableName select[name='tableName']").val();
    var value = $(".table-data input[name='tax']").val();

    $(function () {
        query();
    })
    function query() {

        // var switchName = $("#switchNameSearch").val();

        // 拼接参数
        var params = {
            tableName : tableName,
            exceptionAllgross : value
        };
        var options = {
            id : 'switchconfigdata',
            elem : '#content',
            url : "${path}/dmSubjectSerGroupInfo/query?random=" + Math.random(),
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
                    width : '25%',
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
                    width : '25%'
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

<#--下拉选获取表中的数据-->
<script type="text/javascript">
    $(function () {
        layui.use(['form', 'upload', 'layer', 'table', 'util'], function () {
            table = layui.table;
            util = layui.util;
            // 检查项目添加到下拉框中

            $.ajax({
                url: "${path}/dmSubjectSerGroupInfo/",
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $.each(data, function (index, item) {
                        $("#exceptionForm select[name='tableName']").append(new Option(item.name, item.code));// 下拉菜单里添加元素
                    });
                    // 重新渲染 固定写法
                    layui.form.render("select");
                }
            });
        });
    });
</script>

<#--新增页面-->
<script>

    $(function () {
        addData();
    });

    function addData() {
        alert("123132131");
    }

    //循环获取默认表名
    $(function () {
        var tableList =["nbdivide_exception_cloudcard_handle", "nbdivide_exception_co_handle"
            , "nbdivide_exception_fi_handle", "nbdivide_exception_other_handle"
            , "nbdivide_exception_receipt_handle", "nbdivide_exception_restore_bill_handle"
            , "nbdivide_exception_restore_dd_handle", "nbdivide_exception_restore_fsfx_handle"
            , "nbdivide_exception_restore_kc_handle", "nbdivide_exception_restore_qz_handle"
            , "nbdivide_exception_restore_shzf_handle", "nbdivide_exception_restore_xykzf_handle"
            , "nbdivide_exception_restore_zf_handle", "nbdivide_exception_tax_handle"];

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
        debugger
        layui.use("layer", function() {
            var layer = layui.layer;
            debugger
            layer.open({
                type : 1,
                skin : 'layui-layer-molv',
                area : [ '480px','850px' ], // 只设置宽度 如果要设置宽度、高度： ['500px','300px']
                content : $("#exceptionDataAddDiv"), // 这里content是一个html
                title : "新增异常",
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
            let tableNmae = $(dom).attr("optTableName");
            let value = $(dom).val();

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
            url : "${path}/exceptiondatastatistics/add",
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
            }
        });
    }

</script>
</body>
</html>
