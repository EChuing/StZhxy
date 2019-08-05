//查询情景模式名称
$(function(){
    $.ajax({
        type: "post",
        url: "./selecePatternName",
        data: {},
        dataType: "json",
        success: function(result) {
            console.log(result);
            var  body=result.body;
            var JSONBody=JSON.parse(body);
            var htmlPattern="";
            var htmlDoorLock="";
            for (var i in JSONBody){
                var spdNumber=JSONBody[i].spdNumber;
                var spdDescribe=JSONBody[i].spdDescribe;

                if(spdNumber!=5){
                    htmlPattern +='<div style="width:48%;height:250px;line-height:250px;float:left;background:url(\'img/pattern.jpg\');background-size: contain;margin:12px 1% 0 1%;" id="numBer1" onclick="startScenarioModel('+JSONBody[i].spdId+')">'+
                        '<p style="text-align:center;font-size:50px;">'+JSONBody[i].spdDescribe+'</p>'+
                        '</div>';
                }
            }
            $("#patternDiv").html(htmlPattern);
        }
    })
});
