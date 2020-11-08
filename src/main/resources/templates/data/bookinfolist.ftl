<#assign path="${request.getContextPath()}" >
<#if bookLIst??>
    <#list bookLIst as info>
        <tr>
            <td class="data-num">${info.id}</td>
            <td class="data-title">${info.title}</td>
            <td class="data-price">${info.price}</td>
            <td class="data-author">${info.author}</td>
            <td class="data-option">
                <span onclick="editById(this)" bookInfos="${info.id}" >编辑</span>
                <span onclick="deleteById(this)" bookId="${info.id}">删除</span>
            </td>
        </tr>
    </#list>
<#else>
    暂无数据
</#if>


<script type="application/javascript">

    function editById(data) {
        var bookInfos = $(data).attr("bookInfos");

        //查询所有数据ajax
        $.ajax({
            url: "${path}/book-info/queryInfo-by-id",
            type: "POST",
            data: {
                "bookId": bookInfos
            },
            success: function(msg) {
                $("#bTitle").val(msg.title);
                $("#bPrice").val(msg.price);
                $("#bAuth").val(msg.author);

                $("#saveEdit").attr("bId", msg.id);
            }
        });

    }

    function saveEdit(data) {
        var bookId = $(data).attr("bId");

        $.ajax({
            url: "${path}/book-info/save-id",
            type: "POST",
            data: {
                "bookId": bookId,
                "price" : $("#bTitle").val(),
                "title" : $("#bPrice").val(),
                "author": $("#bAuth").val()


            },
            success: function(msg) {
                window.location.reload();
            }
        });
    }

</script>
<script type="application/javascript">

    //根据title珊瑚
    function deleteById(data) {
        var bookId = $(data).attr("bookId");

        //删除的ajax
        $.ajax({
            url: "${path}/book-info/delete-by-id",
            type: "POST",
            data: {
                "bookId": bookId
            },
            success: function(data) {
                debugger
                alertInfo(data.message, 1);
                window.location.reload();
            }
        });
    }
    function alertInfo(info, icon) {
        layer.alert(info, {
            // 样式类名 自定义样式
            skin : 'layui-layer-molv',
            // 是否有关闭按钮
            closeBtn : 0,
            icon : icon,
            yes : function(index) {
                layer.close(index);
                window.location.reload();
            }
        });
    }



</script>
