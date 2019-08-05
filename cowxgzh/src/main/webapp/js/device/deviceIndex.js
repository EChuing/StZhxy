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

                if(spdNumber==12 ||spdNumber==13 ||spdNumber==14 || spdNumber==15){
                    htmlPattern +='<div style="width:48%;height:250px;line-height:250px;float:left;background:url(\'img/pattern.jpg\');background-size: contain;margin:12px 1% 0 1%;" id="numBer1" onclick="startScenarioModel('+JSONBody[i].spdId+')">'+
                        '<p style="text-align:center;font-size:50px;">'+JSONBody[i].spdDescribe+'</p>'+
                        '</div>';
                }
                if(spdNumber==12){
                    console.log(1111111111)
                    htmlDoorLock +='<div style="width:100%;height:250px;text-align:center;margin:30px 0 0 0;background:url(\'http://pic-gongkai.fangzhizun.com/FliSd57POj1XjmWyGGThxRQizWun7163.jpg\') no-repeat center center/85%;" onclick="intelligentControl('+JSONBody[i].spdId+')">'+
                        '<p style="text-align:center;font-size:50px; line-height:250px;">智能设备控制</p>'+
                        '</div>';
                }
            }
            $("#patternDiv").html(htmlPattern);
            $("#doorLockDiv").html(htmlDoorLock);
        }
    })
});
