<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>anhdkjsahdjksa</title>

<#--    <script type="text/javascript" src="../static/res/echarts.min.js"></script>-->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>

    <style type="text/css">
        .crumb {
            height: 50px;
            line-height: 50px;
            background-color: #f0f0f0;
            color: #999;
            font-size: 14px;
            position: relative;
            text-indent: 30px;
            text-align: left;
            border-bottom: 10px solid #fbfbfc;
        }
        .div-content{

        }
        .pie-chart{

        }
        .query-param{

        }
        .bar-chart{

        }
        .div-table{

        }
    </style>
</head>
<body>
    <div class="crumb"><span style="color:#00a7eb;font-weight:600;font-size:16px;">分成异常数据统计</div>

    <div class="div-content">
        <div class="pie-chart" id="main" style="width: 600px;height:400px;">

        </div>
        <div class="query-param">

        </div>
        <div class="bar-chart">

        </div>
        <div class="div-table">

        </div>

    </div>


    <script  type="text/javascript">
        var mycharts = echarts.init(document.getElementById('main'));


      var  option = {
            legend: {},
            tooltip: {},
            dataset: {
                dimensions: ['product', '2015', '2016', '2017'],
                source: [
                    {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                    {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                    {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                    {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                ]
            },
            xAxis: {type: 'category'},
            yAxis: {},
            // Declare several bar series, each will be mapped
            // to a column of dataset.source by default.
            series: [
                {type: 'bar'},
                {type: 'bar'},
                {type: 'bar'}
            ]
        };
        mycharts.setOption(option);


    </script>
</body>
</html>


