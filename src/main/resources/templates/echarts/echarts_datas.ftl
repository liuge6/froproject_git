<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>anhdkjsahdjksa</title>

<#--    <script type="text/javascript" src="../static/res/echarts.min.js"></script>-->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>

</head>
<body class="">
<div id="main" style="height: 500px;">

</div>
</body>
</html>


<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var mycharts = echarts.init(document.getElementById('main'));
    $(function () {
        $.ajax({
            url : "../user-curd/getEchartsDatas",
            type: 'post',
            success: function (data) {
                var _obj = data.data.result;
                option = {
                    title: {
                        text: '堆叠区域图'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        }
                    },
                    legend: {
                        data: _obj.tableName
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: _obj.tableDate
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: _obj.tableDataList

                };
                //试用钢指定的配置项和数据显示图表
                mycharts.setOption(option);
            }
        });
    })

</script>
