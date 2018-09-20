require(['jquery', 'bootstrap', 'utils', 'layer'], function ($) {

    var webRoot = G_WEB_ROOT;
    var urls = {
        queryAllQuestionBank: webRoot + '/admin/ya/queryAllQuestionBank',
        saveYa: webRoot + '/admin/ya/saveYa',
    }
    layer.config({
        path: webRoot + '/layer-v3.0.3/layer/' // layer.js所在的目录，可以是绝对目录，也可以是相对目录
    });
    var SELECTED_QUESTIONBANK_ID = [];

    function initList() {
        var setting = {
            url: urls.queryAllQuestionBank,
            success: function (rs, status) {
                $('#yaZoneId').empty();
                var html = '';
                $.each(rs, function (i, item) {
                    var dtoList = item;
                    var pHtml = '';
                    $.each(dtoList, function (j, item2) {
                        var typeName = item2.type == '1' ? "单选题" : item2.type == '2' ? "多选题" : "是非题";
                        pHtml += '<p>' + typeName + ':' + item2.questionNum + '题</p>';
                    });

                    html +=
                        '<div class="col-lg-2 col-md-2">' +
                        '    <div class="questionBankZone panel panel-primary" data-questionBankId="' + i + '">' +
                        '        <div class="panel-heading">' +
                        '            ' + dtoList[0].questionBankName +
                        '        </div>' +
                        '        <div class="panel-body text-right">' +
                        '           <div class="col-lg-3 col-md-3 selectedZone_' + i + '"></div>' +
                        '           <div class="col-lg-9 col-md-9">' +
                        '               <p>' + pHtml + '</p>' +
                        '           </div>' +
                        '        </div>' +
                        '        <div class="panel-footer ">创建时间：' + dtoList[0].createdDate + '</div>' +
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
        $("#yaZoneId").on("click", ".questionBankZone", function () {
            var questionBankId = this.getAttribute("data-questionBankId");
            var len = $(".selectedZone_" + questionBankId).html().length;
            if (len > 0) {
                $(".selectedZone_" + questionBankId).empty();
                SELECTED_QUESTIONBANK_ID.remove(questionBankId);
            } else {
                SELECTED_QUESTIONBANK_ID.push(questionBankId);
                var html = '<button type="button" class="btn btn-info btn-circle btn-xl"><i class="fa fa-check"></i></button></div>';
                $(".selectedZone_" + questionBankId).append(html);
            }
            console.log(SELECTED_QUESTIONBANK_ID);

            var html = SELECTED_QUESTIONBANK_ID.length + '<i class="fa fa-save"></i> 保存 ';
            $("#saveIdBtn").empty();
            $("#saveIdBtn").append(html);
        });


        $("#saveIdBtn").click(function () {
            var setting = {
                url: urls.saveYa,
                data: {"questionBankIdStr": SELECTED_QUESTIONBANK_ID.join()},
                success: function (rs, status) {
                    if (rs.k) {
                        layer.msg(rs.v, {icon: 1});
                    } else {
                        layer.msg(rs.v, {icon: 6});
                    }
                }
            }
            Tc.ajax(setting);
        });
    }

    function init() {
        initList();
    }

    function main() {
        init();
    }

    main();
});
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
