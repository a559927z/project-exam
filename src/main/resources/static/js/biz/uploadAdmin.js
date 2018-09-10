$(function () {

    function initSelect(){
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
        console.log(ctx);
        var successTotal =  $("#successTotalId").val();
        if(successTotal != null && successTotal.length >0){
            alert("入库条数:"+successTotal);
        }
        init();
    }

    main();

});