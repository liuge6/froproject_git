<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script type="text/javascript" src="js/layui.all.js" ></script>
    <script>
        $(document).ready(function () {
            $("#addUser").click(function () {
                $("#formAdd").toggle();
            })
            $("#queryUser").click(function () {
                $("#formAdd2").toggle();
            })

        })
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <button id="addUser" type="button" class="btn btn-primary">新增用户</button>
            <button id="queryUser" type="button" class="btn btn-primary">搜索用户</button>
            <div id="formAdd" style="display: none">
                <form role="form" method="post" action="/superadmin/save">
                    <div class="form-group">
                        <label>用户名</label>
                        <input name="userName" type="text" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input name="passWord" type="number" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>

            <!--搜索用户-->
            <div id="formAdd2" style="display: none">
                <form role="form" method="post" action="/superadmin/query">
                    <div class="form-group">
                        <label>根据用户名搜索</label>
                        <input name="userName" type="text" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
            <p>用户表</p>
            <table class="table table-bordered table-condensed table-striped table-hover">
                <thead>
                <tr>
                    <th>用户id</th>
                    <th>用户姓名</th>
                    <th>用户密码</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list list as li>
                    <tr>
                        <td>${li.userId}</td>
                        <td>${li.userName}</td>
                        <td>${li.passWord}</td>
                        <td><a href="/superadmin/update?userId=${li.userId}">修改</a></td>
                        <td><a href="/superadmin/delete?userId=${li.userId}">删除</a></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
