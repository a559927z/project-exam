require(['jquery', 'bootstrap', 'utils', 'layer'], function ($) {

    var webRoot = G_WEB_ROOT;
    var urls = {
        index: webRoot + '/admin/questionBank/index',
        queryAllQuestionBank: webRoot + '/admin/ya/queryAllQuestionBank',
    }
    layer.config({
        path: webRoot + '/layer-v3.0.3/layer/' // layer.js所在的目录，可以是绝对目录，也可以是相对目录
    });
    var QUESTIONBANK_ID = "";


    function initList() {
        console.log(111);
        var setting = {
            url: urls.queryAllQuestionBank,
            success: function (rs, status) {
                $('#yaZoneId').empty();
                var html = '';
                $.each(rs, function (i, item) {
                    html +=
                        '<div class="col-lg-3 col-md-6">' +
                        '    <div class="panel panel-primary">' +
                        '        <div class="panel-heading">' +
                        '            ' + item.questionBankName +
                        '        </div>' +
                        '        <div class="panel-body">' +
                        '            <p>' + item.questionBankNum + '</p>' +
                        '        </div>' +
                        '        <div class="panel-footer">' +
                        '            Panel Footer' +
                        '        </div>' +
                        '    </div>' +
                        '</div>'
                    ;
                });
                $('#yaZoneId').append(html);
                initEvent();
            }
        }
        Tc.ajax(setting);
    }

    function initEvent() {
    }

    function init() {
        initList();
    }

    function main() {
        init();
    }

    main();
});

