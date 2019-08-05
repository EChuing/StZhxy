$(function(){
    $.ajax({
        type: "post",
        url: "./selecePatternName",
        data: {},
        dataType: "json",
        success: function(result) {
            var  body=result.body;
            var JSONBody=JSON.parse(body);
            var  html="";
            for(var i in JSONBody){
                    html +='<div class="content">'+
                        '<img src="img/ic_dt_sweepfloor_icon.png"/>'+
                        '<div class="content_info">'+
                        '<text class="info_title">'+JSONBody[i].spdDescribe+'</text>'+
                        '</div>'+
                        '<div class="picker" onclick="scenarioSetting('+JSONBody[i].spdId+')">'+
                        '<text class="back">设置></text>'+
                        '</div>'+
                        '</div>';
                }
            $('.all').html(html);
        }
    })
})
//跳转情景指令列表
function scenarioSetting(spdId) {
    var hsId=$("#hsId").val();
    location.href="scenarioSetting?hsId="+hsId+"&spdId="+spdId;
}