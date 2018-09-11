$(function () {

    var webRoot = G_WEB_ROOT;
    var urls = {
        parseXls2Dto: webRoot + '/admin/upload/parseXls2Dto'
    }


    function submit() {
        $("#submitBtnId").click(function () {

            $.ajax({
                type: "POST",
                url: urls.parseXls2Dto,
                cache: false,	//禁用缓存
                data: {
                    "questionBankName": $("#questionBankNameId").val(),
                    "categoryId": $("#categoryVal").val(),
                    "courseId": $("#courseVal").val(),
                    "file": $("#fileId").val(),
                },
                dataType: "json",
                success: function (result) {
                    console.log(result);
                }
            });
        });
    }

    function initSelect() {
        //        $("#categoryVal").empty();
        //        $("#courseVal").empty();
        $("#categorySelect").change(function () {
            $("#courseSelect").empty();
            var categoryVal = $("#categorySelect").val();
            $("#categoryVal").val(categoryVal);
            var option = "";
            $.each(courseEnum, function (i, item) {
                var id = item.k;
                var fk = item.f;
                var val = item.v;
                if (fk == categoryVal) {
                    option += "<option value='" + id + "'>" + val + "</option>";
                }
            });
            $("#courseSelect").append(option);
            var courseVal = $("#courseSelect").val();
            $("#courseVal").val(courseVal);
        });

        $("#courseSelect").change(function () {
            var courseVal = $("#courseSelect").val();
            $("#courseVal").val(courseVal);
        });

    }

    function init() {
        initSelect();
    }

    function main() {
        var successTotal = $("#successTotalId").val();
        if (successTotal != null && successTotal.length > 0) {
            alert("入库条数:" + successTotal);
        }
        init();
    }

    main();

});