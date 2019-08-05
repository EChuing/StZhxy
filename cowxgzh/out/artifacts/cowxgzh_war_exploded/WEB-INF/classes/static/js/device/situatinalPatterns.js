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
             //   if(spdNumber==12 || spdNumber==13 || spdNumber==14 || spdNumber==15){
                    htmlPattern +='<div style="width:48%;height:100px;line-height:100px;float:left;background:url(\'img/pattern.jpg\');background-size: 100% 100%;margin:5px 1% 0 1%;" id="numBer1" onclick="openScenario('+JSONBody[i].spdId+')">'+
                        '<p style="text-align:center;font-size:22px;">'+JSONBody[i].spdDescribe+'</p>'+
                        '</div>';
                }
       //     }
            $("#patternDiv").html(htmlPattern);
        }
    })
});
function openScenario(jsroPatternId) {
    var jsroHsId=$("#hsId").val();
    $.ajax({
        type: "post",
        url: "./openScenario",
        data: {
            jsroPatternId:jsroPatternId,
            jsroHsId:jsroHsId
        },
        dataType: "json",
        success: function(result) {
            if (result.code==-1){
                alert(result.msg);
            }else{
                toast(result.msg);
            }
        }
    })
}
//启动成功后弹出启动成功
function toast(resultMsg){
    $("#resultMsg").html(resultMsg)
    var $toast = $('#toast');
    console.log($toast)
    if ($toast.css('display') != 'none') return;
    $toast.fadeIn(100);
    setTimeout(function () {
        $toast.fadeOut(100);
    }, 2000);
}
