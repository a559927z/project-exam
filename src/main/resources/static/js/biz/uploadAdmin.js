$(function () {

    function init() {
        $("#categoryVal").empty();
        $("#courseVal").empty();
        $("#categorySelect").change(function () {
            // 先清空第二个
            $("#courseSelect").empty();
            //获取Select选择的Value
            var categoryVal = $("#categorySelect").val();
            $("#categoryVal").val(categoryVal);
            var option = "";
            $.each(courseEnum, function (i, item) {
                var id = item.k;
                var val = item.v;
                if (id == categoryVal) {
                    option += "<option value='" + id + "'>" + val + "</option>";
                }
            });
            // 实际的应用中，这里的option一般都是用循环生成多个了
            $("#courseSelect").append(option);
            var courseVal = $("#courseSelect").val();
            $("#courseVal").val(courseVal);
        });

        $("#courseSelect").change(function () {
            var courseVal = $("#courseSelect").val();
            $("#courseVal").val(courseVal);
        });
    }

    function main() {
        console.log(ctx);
        init();
    }

    main();

});