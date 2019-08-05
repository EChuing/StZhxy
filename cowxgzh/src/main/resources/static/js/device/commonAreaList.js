//查询情景模式名称
$(function(){
    //判断是否显示教室列表
    var  Identification=$("#Identification").val();
    if (Identification==1){
        $("#bottomNavigation").show();
    }else{
        $("#bottomNavigation").hide();
    }
    $.ajax({
        type: "post",
        url: "./selecePublicArea",
        data: {},
        dataType: "json",
        success: function(result) {
            console.log(result);
            var html="";
            var  body=result.body;
            for (var i in body){
                html +='<div class="content" onclick="commonScenariosIndex('+i+')">'+
                        '<div>'+
                        '<span>'+body[i].hsAddCommunity+'</span>'+
                        ' <input id="hsId'+i+'" type="hidden" value="'+body[i].hsId+'">'+
                        '</div>'+
                        '</div>';
            }
            $("#hsAddCommunity").html(html)
        }
    })
    hsNameSubmit();
});
//搜索名称
function hsNameSubmit() {
    var publicName=$("#publicName").val();
    $.ajax({
        type: "post",
        url: "./selecePublicArea",
        data: {
            hsAddCommunity:publicName
        },
        dataType: "json",
        success: function(result) {
            var html="";
            var  body=result.body;
            for (var i in body){
                html +='<div class="content" onclick="commonScenariosIndex('+i+')">'+
                    '<div>'+
                    '<span>'+body[i].hsAddCommunity+'</span>'+
                    ' <input id="hsId'+i+'" type="hidden" value="'+body[i].hsId+'">'+
                    '</div>'+
                    '</div>';
            }
            $("#hsAddCommunity").html(html)
        }
    })
}
//跳转常用情景
function commonScenariosIndex(i) {
  var hsId=$("#hsId"+i).val();
  location.href="deviceIndex?hsId="+hsId;
}