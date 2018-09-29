require(['jquery', 'bootstrap', 'dataTable', 'datatables.net', 'utils', 'layer'], function ($) {

    var webRoot = G_WEB_ROOT;
    var urls = {
        list: webRoot + '/admin/user/list',
        delete: webRoot + '/admin/user/delete',
        uploadStatus: webRoot + '/admin/user/uploadStatus',
    }
    layer.config({
        path: webRoot + '/layer-v3.0.3/layer/' // layer.js所在的目录，可以是绝对目录，也可以是相对目录
    });
    var style = {
        btnColor: "btn-default",
        iconColor: "glyphiconColor"
    }
    var btnHtml = {
        lock: '<button type="button" class="btn btn-default btn-small btn-lock"> 加锁 <span class="' + style.iconColor + '"><i class="fa fa-lock"></i></span>',
        unlock: '<button type="button" class="btn btn-default btn-small btn-lock"> 激活 <span class="' + style.iconColor + '"><i class="fa fa-unlock"></i></span>',
        del: '<button type="button" class="btn btn-default btn-small btn-del"> 删除 <span class="' + style.iconColor + '"><i class="fa fa-times"></i></span>'
    }
    var dtMgr = {
        currentItem: null,
        fuzzySearch: true,
        getQueryCondition: function (data) {
            var param = {};
            // //组装查询参数
            param.search = data.search.value;
            //组装分页参数
            param.startIndex = data.start;//起始位置
            param.pageSize = data.length;//一页大小
            param.page = (data.start / data.length) + 1;//当前页码
            param.draw = data.draw; // 点了多少次分页
            return param;
        },
        deleteItem: function (selectedItems) {
            var ids = [];
            $.each(selectedItems, function (index, item) {
                ids.push(item.enName);
            });
            btnEvent(urls.delete, ids);
        },
        lockItem: function (selectedItems) {
            var ids = [];
            $.each(selectedItems, function (index, item) {
                ids.push(item.enName);
            });
            btnEvent(urls.uploadStatus, ids);
        }
    };
    var $table = $('#table-user');
    var $wrapper = $('#div-table-container');
    var defaultoption = CONSTANT.DATA_TABLES.DEFAULT_OPTION;
    var _table;

    function showDetails() {
        defaultoption.searching = true;
        var tableOption = $.extend(true, {}, defaultoption, {
            ajax: function (data, callback, settings) {
                var param = dtMgr.getQueryCondition(data);
                $.ajax({
                    type: "POST",
                    url: urls.list,
                    cache: false,	//禁用缓存
                    data: param,	//传入已封装的参数
                    dataType: "json",
                    success: function (result) {
                        //没有数据
                        if (result.data.length == 0) {
                            return;
                        }
                        //异常判断与处理
                        if (result.errorCode) {
                            return;
                        }
                        //封装返回数据，这里仅演示了修改属性名
                        var returnData = {};
                        returnData.draw = data.draw;//点了几次分页,这里直接自行返回了draw计数器,应该由后台返回"
                        returnData.recordsTotal = result.total;//返回数据全部记录"
                        returnData.recordsFiltered = result.total;//;//不加这个“下一页”不好使,后台不实现过滤功能，每次查询均视作全部结果"
                        returnData.data = result.data;//返回的数据列表
                        callback(returnData);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                    }
                });
            },
            columns: [
                CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, {
                    className: "ellipsis",
                    data: "enName",
                    width: "150px"
                }, {
                    className: "ellipsis",
                    data: "cnName",
                    width: "150px"
                }, {
                    className: "ellipsis",
                    data: "salt",
                    width: "150px"
                }, {
                    className: "ellipsis",
                    data: "state",
                    width: "150px"
                }, {
                    className: "td-operation",
                    data: null,
                    defaultContent: "",
                    orderable: false,
                    width: "220px"
                }],
            createdRow: function (row, data, index) {
                //行渲染回调,在这里可以对该行dom元素进行任何操作
                //给当前行加样式
                if (data.role) {
                    $(row).addClass("info");
                }
                //给当前行某列加样式 eq从0开始
                // $('td', row).eq(2).addClass(data.userKey == 'dcli' ? "text-danger" : "text-success");

                //不使用render，改用jquery文档操作呈现单元格
                var $btnLock = "";
                if (data.state == 0) {
                    $btnLock = btnHtml.lock;
                } else {
                    $btnLock = btnHtml.unlock;
                }
                $('td', row).eq(5).append($btnLock).append("    ").append(btnHtml.del);
            },
            drawCallback: function (settings) {
                //渲染完毕后的回调
                //清空全选状态
                $(":checkbox[name='cb-check-all']", $wrapper).prop('checked', false);
                //默认选中第一行
                $("tbody tr", $table).eq(0).click();

                var jsonData = settings.aoData;
                $.each(jsonData, function (index, item) {
                    // var obj = item._aData;
                    // console.log(obj.isLock)
                    // if (obj.isLock == 0) {
                    //     $("tbody tr", $table).eq(index).find("td").eq(4).text("解锁中").append(' <span class="' + style.iconColor + '"><i class="fa fa-unlock"></i></span>');
                    //     $("tbody tr", $table).eq(index).find("td").eq(5).find(".btn-lock").text('加锁').append(' <span class="' + style.iconColor + '"><i class="fa fa-lock"></i></span>');
                    // } else {
                    //     $("tbody tr", $table).eq(index).find("td").eq(4).text("加锁中").append(' <span class="' + style.iconColor + '"><i class="fa fa-lock"></i></span>');
                    //     $("tbody tr", $table).eq(index).addClass("text-danger");
                    //     $("tbody tr", $table).eq(index).find("td").eq(5).find(".btn-lock").text('解锁').append(' <span class="' + style.iconColor + '"><i class="fa fa-unlock"></i></span>');
                    // }

                });
            },
            //http://www.datatables.club/reference/option/dom.html
            dom:
            "<'row'<'col-sm-6 col-md-6'l><'col-sm-6 col-md-6'<'pull-right'f>>>" +
            "<'row'<'col-sm-12  col-md-12'tr>>" +
            "<'row'<'col-sm-5 col-md-5'i><'col-sm-7  col-md-7'<'pull-right'p>>>",
            initComplete: function () {
                $("#bar").append('<button id="batch-btn-del" type="button" class="btn ' + style.btnColor + ' btn-sm">批量删除   <span class="glyphicon glyphicon-remove ' + style.iconColor + '"></span></button>&nbsp');
                $("#searchRs").append('<div style="background-color: orangered; text-align: center; font-weight: bold;" ><span>没有查询结果</span></div>');
                $("#searchRs div").hide();
                // 批量删除
                $("#batch-btn-del").click(function () {
                        var arrItemId = [];
                        $("tbody :checkbox:checked", $table).each(function (i) {
                            var item = _table.row($(this).closest('tr')).data();
                            console.log(item);
                            console.log($(this).closest('tr'));
                            arrItemId.push(item);

                        });
                        dtMgr.deleteItem(arrItemId);
                    }
                );
            }
        });
        if (typeof(_table) == "undefined") {
            _table = $table.dataTable(tableOption);
        } else {
            _table.fnClearTable(); //清空一下table
            _table.fnDestroy(); //还原初始化了的datatable
            _table = $table.dataTable(tableOption);
        }
        //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
        _table = _table.api();
    }

    function tableEvent() {
        $table.on("change", ":checkbox", function () {
            if ($(this).is("[name='cb-check-all']")) {
                //全选
                $(":checkbox", $table).prop("checked", $(this).prop("checked"));
            } else {
                //一般复选
                var checkbox = $("tbody :checkbox", $table);
                $(":checkbox[name='cb-check-all']", $table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
            }
        }).on("click", ".td-checkbox", function (event) {
            //点击单元格即点击复选框
            !$(event.target).is(":checkbox") && $(":checkbox", this).trigger("click");
        }).on("click", ".btn-del", function () {
            //点击删除按钮
            var item = _table.row($(this).closest('tr')).data();
            $(this).closest('tr').addClass("active").siblings().removeClass("active");
            dtMgr.deleteItem([item]);
        }).on("click", ".btn-lock", function () {
            //点击锁定按钮
            var item = _table.row($(this).closest('tr')).data();
            $(this).closest('tr').addClass("active").siblings().removeClass("active");
            dtMgr.lockItem([item]);
        });
    }

    function btnEvent(paramUrl, paramIds) {
        var message;
        if (paramIds && paramIds.length) {
            if (paramIds.length == 1) {
                message = "确定要操作 '" + paramIds[0] + "' 吗?";
            } else {
                message = "确定要操作选中的" + paramIds.length + "项记录吗?";
            }
            layer.confirm(message, {
                btn: ['确认', '取消'] //按钮
            }, function () {
                var setting = {
                    url: paramUrl,
                    data: {"userIds": paramIds},
                    success: function (data, status) {
                        if (data.k) {
                            layer.msg(data.v, {icon: 6});
                            $('#table-user_filter input').val('');
                            _table.draw();
                        } else {
                            layer.msg(data.v, {icon: 5});
                        }
                    }
                };
                Tc.ajax(setting);
            }, function () {
                layer.msg('已取消操作', {
                    time: 20000 //20s后自动关闭
                });
            });
        } else {
            layer.msg("请先选中要操作的行", {icon: 1});
        }
    }

    function initEvent() {
        showDetails();
        tableEvent();
    }

    function init() {
        initEvent();
    }

    function main() {
        init();
    }

    main();

});

