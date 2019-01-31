var method = '',
    edit = null,
    del = null,
    log = console.log;
$(function () {
    // var edit = null,
    //     del = null,
    //     log = console.log;
    //
    // edit = function (uuid) {
    //     log(uuid);
    // };
    //
    // del = function (uuid) {
    //     log(uuid);
    // };

    $('#grid').datagrid({
        url: 'dep_listPage.action',
        columns: [[
            {field: 'uuid', title: 'uuid', width: 100},
            {field: 'name', title: 'name', width: 100},
            {field: 'tele', title: 'tele', width: 100, align: 'right'},
            {
                field: '-', title: '操作', formatter: function (value, row, index) {
                    var oper = "<a href=\"javascript:void(0)\" onclick=\"edit(" + row.uuid + ')">修改</a>';
                    oper += ' <a href="javascript:void(0)" onclick="del(' + row.uuid + ')">删除</a>';
                    return oper;
                }
            }
        ]],
        striped: true,
        singleSelect: true,
        pagination: true,
        toolbar: [
            {
                iconCls: 'icon-add',
                text: 'add',
                handler: function () {
                    $('#editDlg').dialog('open');
                    //清空表单内容
                    $('#editForm').form('clear');
                    // default content
                    $('#editForm').form('load', {
                        't.name': 'depName',
                        't.tele': 'depTele'
                    });

                    method = 'add';
                }
            }
        ]
    });

    $("#btnSearch").bind("click", function () {
        var formData = $("#searchForm").serializeJSON();
        $("#grid").datagrid("load", formData);
        // console.log($('#grid').datagrid('getData'));
    });

    $('#grid').datagrid({
        onLoadSuccess: function (data) {
            console.log(data);
        }
    });

    $('#editDlg').dialog({
        title: '部门编辑',
        width: 300,
        height: 200,
        closed: true,//窗口是是否为关闭状态, true：表示关闭
        modal: true//模式窗口
    });

    $('#btnSave').bind('click', function () {
        var formData = $('#editForm').serializeJSON();
        $.ajax({
            url: 'dep_' + method,
            data: formData,
            dataType: 'json',
            type: 'post',
            success: function (rtn) {
                $.messager.alert("提示", rtn.message, 'info', function () {
                    //成功的话，我们要关闭窗口
                    $('#editDlg').dialog('close');
                    //刷新表格数据
                    $('#grid').datagrid('reload');
                });
            }
        });
    });
});


edit = function (uuid) {
    log(uuid);
    //弹出窗口
    $('#editDlg').dialog('open');

    //清空表单内容
    $('#editForm').form('clear');
    method = "edit";
    //加载数据
    $('#editForm').form('load', 'dep_get?id=' + uuid);
};

/**
 * 1. delete
 * 2. reload
 * @param uuid
 */
del = function (uuid) {
    $.messager.confirm('confirm', 'are you sure?', function (yes) {
        if (yes) {
            $.ajax('dep_delete?id=' + uuid, {
                dataType: 'json',
                type: 'post',
                success: function (rtn) {
                    $.messager.alert('prompt', rtn.message, 'info', function () {
                        $('#grid').datagrid('reload');
                    });
                }
            });
        }
    });

};
