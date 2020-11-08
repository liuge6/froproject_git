<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>第一个 ECharts 实例</title>
    <!-- 引入 echarts.js -->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../res/layui/css/layui.css">
    <link rel="stylesheet" href="../layui/js/layui.js">

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<hr>
<div id="pieDiv" style="width: 600px; height: 400px;"></div>

<#--柱状图-->
<script type="text/javascript">

    $(function () {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.ajax({
            url : "../exceptionData/queryBarByData",
            type : 'post',
            dataType : 'json',

            success : function (data) {
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '异常数据展示图'
                    },
                    tooltip: {},
                    legend: {
                        data: ['异常数据']
                    },
                    xAxis: {
                        data: data.tableName
                    },
                    yAxis: {},
                    series: [{
                        name: '异常数据',
                        type: 'bar',
                        data: data.tableDataValue
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

    $(function () {
        var myChart = echarts.init(document.getElementById('pieDiv'));

        $.ajax({
            url : "../exceptionData/queryPieByData1"
            type : 'post',
            success : function () {
                var data = genData(50);
                option = {
                    title: {
                        text: '最新数据展示占比图',
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


            }
        });
    })




</script>
</body>
</html>