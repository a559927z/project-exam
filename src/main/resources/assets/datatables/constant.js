/*常量*/
var CONSTANT = {
    DATA_TABLES: {
        DEFAULT_OPTION: { //DataTables初始化选项
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "每页 _MENU_ 项",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
                "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页",
                    "sJump": "跳转"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            bAutoWidth: false,	//禁用自动调整列宽
            stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
            order: [],			//取消默认排序查询,否则复选框一列会出现小箭头
            processing: false,	//隐藏加载提示,自行处理
            serverSide: true,	//启用服务器端分页
            searching: false,	//禁用原生搜索
            bFilter: false,     //是否启动过滤、搜索功能
            bSort: false,       //是否启动各个字段的排序功能
            // bInfo: false,       //是否显示页脚信息，DataTables插件左下角显示记录数
            bJQueryUI: false,    //是否应用jquery ui themeroller的风格default false
            // sScrollX: "disabled",  //""disabled"" or ""200px""?类似的字符串
            // sScrollY: "disabled",  //""disabled"" or ""200px""?类似的字符串
            aLengthMenu: [5, 10, 25, 50, 100],
            sPaginationType: "full_numbers",    //""full_numbers"" or ""two_button""， default ""two_button""
            destroy: true       //解决Cannot reinitialise DataTable
        },
        COLUMN: {
            CHECKBOX: {	//复选框单元格
                className: "td-checkbox",
                orderable: false,
                width: "30px",
                data: null,
                render: function (data, type, row, meta) {
                    return '<input type="checkbox" class="iCheck">';
                }
            }
        },
        RENDER: {	//常用render可以抽取出来，如日期时间、头像等
            ELLIPSIS: function (data, type, row, meta) {
                data = data || "";
                return '<span title="' + data + '">' + data + '</span>';
            }
        }
    }
};