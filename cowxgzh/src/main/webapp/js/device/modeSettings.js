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
                if (JSONBody[i].spdNumber!=5){
                    html +='<div class="content">'+
                        '<img src="img/ic_dt_sweepfloor_icon.png"/>'+
                        '<div class="content_info">'+
                        '<text class="info_title">'+JSONBody[i].spdDescribe+'</text>'+
                        '</div>'+
                        '<div class="picker" onclick="modeSetting('+JSONBody[i].spdId+')">'+
                        '<text class="back">设置></text>'+
                        '</div>'+
                        '</div>';
                }
            }
            $('.all').html(html);
        }
    })
})