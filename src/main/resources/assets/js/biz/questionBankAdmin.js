require(['jquery', 'bootstrap', 'spinJs', 'dataTable', 'datatables.net', 'utils', 'layer'], function ($) {

    var webRoot = G_WEB_ROOT;
    var urls = {
        list: webRoot + '/admin/questionBank/list',
        queryTotal: webRoot + '/admin/questionBank/queryTotal',
        deleteQuestionBank: webRoot + '/admin/questionBank/deleteQuestionBank',
        index: webRoot + '/admin/questionBank/index',
    }
    layer.config({
        path: webRoot + '/layer-v3.0.3/layer/' // layer.js所在的目录，可以是绝对目录，也可以是相对目录
    });
    var style = {
        btnColor: "btn-default",
        iconColor: "glyphiconColor"
    }
    var dtMgr = {
        currentItem: null,
        fuzzySearch: true,
        getQueryCondition: function (data, questionBankId) {
            var param = {};
            //组装排序参数
            if (data.order && data.order.length && data.order[0]) {
                switch (data.order[0].column) {
                    case 1:
                        param.orderColumn = "questionBankName";
                        break;
                    case 2:
                        param.orderColumn = "categoryId";
                        break;
                    case 3:
                        param.orderColumn = "courseId";
                        break;
                }
                param.orderDir = data.order[0].dir;
            }
            // //组装查询参数
            param.search = data.search.value;
            //组装分页参数
            param.startIndex = data.start;
            param.pageSize = data.length;
            param.draw = data.draw;
            param.questionBankId = questionBankId;
            return param;
        },
        showItemDetail: function (item) {
        },
        addItemInit: function () {
        },
        editItemInit: function (item) {
        },
        addItemSubmit: function () {
            layer.msg("保存当前添加用户", {icon: 1});
        },
        editItemSubmit: function () {
            layer.msg("保存当前编辑用户", {icon: 1});
        },
        deleteItem: function (selectedItems) {
        },
        deleteQuestionBank: function (questionBankId) {
            console.log(questionBankId)
            var setting = {
                url: urls.deleteQuestionBank,
                data: {"questionBankId": questionBankId},
                success: function (rs, status) {
                    if (rs.success) {
                        layer.msg("成功删除", {icon: 1});
                    }
                }
            }
            Tc.ajax(setting);
        },
        lockItem: function (selectedItems) {
        }
    };
    var $table = $('#table-user');
    var $wrapper = $('#div-table-container');
    var defaultoption = CONSTANT.DATA_TABLES.DEFAULT_OPTION;
    // 原生搜索
    defaultoption.searching = true;
    var _table;

    function showDetails(questionBankId) {
        if (typeof(_table) == "undefined") {
            _table = $table.dataTable($.extend(true, {}, defaultoption, {
                //ajax配置为function,手动调用异步查询
                ajax: function (data, callback, settings) {
                    //封装请求参数
                    var param = dtMgr.getQueryCondition(data, questionBankId);
                    $.ajax({
                        type: "POST",
                        url: urls.list,
                        cache: false,	//禁用缓存
                        data: param,	//传入已封装的参数
                        dataType: "json",
                        success: function (result) {
                            //setTimeout仅为测试遮罩效果
                            setTimeout(function () {
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
                                // returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                                returnData.recordsTotal = result.total;
                                returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                                returnData.data = result.data;
                                callback(returnData);
                            }, 200);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                        }
                    });
                },
                columns: [
                    CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, {
                        className: "ellipsis",
                        data: "questionBankName",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "categoryId",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "courseId",
                        width: "280px"
                    }, {
                        className: "ellipsis",
                        data: "title",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "answer",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "trueAnswer",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "jieXi",
                        width: "220px"
                    }, {
                        className: "ellipsis",
                        data: "note",
                        width: "200px"
                    }/*, {
                data: "isLock",
                "createdCell": function (td, cellData, rowData, row, col) {
                }
            }*//*, {
                className: "td-operation",
                data: null,
                defaultContent: "",
                orderable: false,
                width: "220px"
            }*/],
                createdRow: function (row, data, index) {
                    //行渲染回调,在这里可以对该行dom元素进行任何操作
                    //给当前行加样式
                    if (data.role) {
                        $(row).addClass("info");
                    }
                    //给当前行某列加样式 eq从0开始
                    // $('td', row).eq(2).addClass(data.userKey == 'dcli' ? "text-danger" : "text-success");

                    //不使用render，改用jquery文档操作呈现单元格
                    // var textLock = "";
                    // if (data.isLock == 0) {
                    //     textLock = '加锁 <span class="' + style.iconColor + '"><i class="fa fa-lock"></i></span>';
                    // } else {
                    //     textLock = '解锁 <span class="' + style.iconColor + '"><i class="fa fa-lock"></i></span>';
                    // }
                    // var $btnLock = $('<button type="button" class="btn btn-default btn-small btn-lock">' + textLock);
                    // $('td', row).eq(5).append($btnLock);
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
                dom: "<'row'<'col-xs-2'l><'#bar.col-xs-3'><'#searchRs.col-xs-2'><'col-xs-5'f>r>" + "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
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
                //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
            }));
        } else {
            _table.fnClearTable(); //清空一下table
            _table.fnDestroy(); //还原初始化了的datatable
            _table = $table.dataTable($.extend(true, {}, defaultoption, {
                //ajax配置为function,手动调用异步查询
                ajax: function (data, callback, settings) {
                    //封装请求参数
                    var param = dtMgr.getQueryCondition(data, questionBankId);
                    $.ajax({
                        type: "POST",
                        url: urls.list,
                        cache: false,	//禁用缓存
                        data: param,	//传入已封装的参数
                        dataType: "json",
                        success: function (result) {
                            //setTimeout仅为测试遮罩效果
                            setTimeout(function () {
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
                                // returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                                returnData.recordsTotal = result.total;
                                returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                                returnData.data = result.data;
                                callback(returnData);
                            }, 200);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                        }
                    });
                },
                columns: [
                    CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, {
                        className: "ellipsis",
                        data: "questionBankName",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "categoryId",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "courseId",
                        width: "280px"
                    }, {
                        className: "ellipsis",
                        data: "title",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "answer",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "trueAnswer",
                        width: "200px"
                    }, {
                        className: "ellipsis",
                        data: "jieXi",
                        width: "220px"
                    }, {
                        className: "ellipsis",
                        data: "note",
                        width: "200px"
                    }/*, {
                data: "isLock",
                "createdCell": function (td, cellData, rowData, row, col) {
                }
            }*//*, {
                className: "td-operation",
                data: null,
                defaultContent: "",
                orderable: false,
                width: "220px"
            }*/],
                createdRow: function (row, data, index) {
                    //行渲染回调,在这里可以对该行dom元素进行任何操作
                    //给当前行加样式
                    if (data.role) {
                        $(row).addClass("info");
                    }
                    //给当前行某列加样式 eq从0开始
                    // $('td', row).eq(2).addClass(data.userKey == 'dcli' ? "text-danger" : "text-success");

                    //不使用render，改用jquery文档操作呈现单元格
                    // var textLock = "";
                    // if (data.isLock == 0) {
                    //     textLock = '加锁 <span class="' + style.iconColor + '"><i class="fa fa-lock"></i></span>';
                    // } else {
                    //     textLock = '解锁 <span class="' + style.iconColor + '"><i class="fa fa-lock"></i></span>';
                    // }
                    // var $btnLock = $('<button type="button" class="btn btn-default btn-small btn-lock">' + textLock);
                    // $('td', row).eq(5).append($btnLock);
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
                dom: "<'row'<'col-xs-2'l><'#bar.col-xs-3'><'#searchRs.col-xs-2'><'col-xs-5'f>r>" + "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
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
                //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
            }));
        }
        _table.api();
    }


    function deleteQuestionBank(questionBankId) {
        var setting = {
            url: urls.deleteQuestionBank,
            data: {"questionBankId": questionBankId},
            success: function (rs, status) {
                if (rs.success) {
                    layer.msg("成功删除", {icon: 1});
                    window.location.href = urls.index;
                }
            }
        }
        Tc.ajax(setting);
    }

    function initTotal() {
        var setting = {
            url: urls.queryTotal,
            success: function (rs, status) {
                $('#totalZoneId').empty();
                var html = '';
                $.each(rs.data, function (i, item) {
                    html +=
                        '<div class="col-lg-3 col-md-6">' +
                        '    <div class="panel panel-green">' +
                        '        <div class="panel-heading">' +
                        '            <div class="row">' +
                        '                <div class="col-xs-3">' +
                        '                    <i class="fa fa-tasks fa-5x"></i>' +
                        '                </div>' +
                        '                <div class="col-xs-9 text-right">' +
                        '                    <div class="huge">题目数：' + item.total + '</div>' +
                        '                    <div>题库名称：' + item.questionBankName + '</div>' +
                        '                </div>' +
                        '            </div>' +
                        '        </div>' +
                        '        <a href="javascript:void(0)" class="showDetails-btn" data-questionBankId="' + item.questionBankId + '">' +
                        '            <div class="panel-footer">' +
                        '                <span class="pull-left">查看明细</span>' +
                        '                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>' +
                        '                <div class="clearfix"></div>' +
                        '            </div>' +
                        '        </a>' +
                        '        <a href="javascript:void(0)" class="del-btn" data-questionBankId="' + item.questionBankId + '">' +
                        '            <div class="panel-footer">' +
                        '                <span class="pull-left">删除</span>' +
                        '                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>' +
                        '                <div class="clearfix"></div>' +
                        '            </div>' +
                        '        </a>' +
                        '    </div>' +
                        '</div>'
                    ;
                });
                $('#totalZoneId').append(html);
                initEvent();
            }
        }
        Tc.ajax(setting);
    }

    function initEvent() {
        $("#totalZoneId").on("click", ".showDetails-btn", function () {
            var questionBankId = this.getAttribute("data-questionBankId");
            showDetails(questionBankId);
        });
        $("#totalZoneId").on("click", ".del-btn", function () {
            var questionBankId = this.getAttribute("data-questionBankId");
            deleteQuestionBank(questionBankId);
        });
    }

    function init() {
        initTotal();
    }

    function main() {
        init();
    }

    main();

});

