$(function(){
    var deviceInformation=$("#deviceInformation").val();
    var id=JSON.parse(deviceInformation).id;
    var hsId=JSON.parse(deviceInformation).hsId;
    var type=JSON.parse(deviceInformation).type;
    $.ajax({
        type: "post",
        url: "./selectSituationalPatterns",
        data: {
            id:id,
            hsId:hsId,
            type:type
        },
        dataType: "json",
        success: function(result) {
            var  result1=result.body;
            var result2=JSON.parse(result1);
            var  body1=result2.body1;
            var  body2=result2.body2;

            var JSONBody1=JSON.parse(JSON.stringify(body1));
            var JSONBody2=JSON.parse(JSON.stringify(body2));
            if(JSONBody2.length!=0){
                var cpScenarioId=JSONBody2[0].cpScenarioId;
            }
            var  html="";
            for (var i in JSONBody1){
                var spdDescribe=JSONBody1[i].spdDescribe;
                var jsroId=JSONBody1[i].jsroId;
                var status = "checkbox";
                var state="关";
                //判断分控面板是否等于情景Id
                if(jsroId==cpScenarioId){
                    status = "checked";
                    state="开";
                }
                html +='<div class="content">'+
                    '<img src="img/ic_dt_rebot_icon.png" />'+
                    '<div class="content_info">'+
                    '<p class="info_title" id="spdDescribe'+i+'">'+spdDescribe+'</p>'+
                    '<p class="info_title1" id="heaterState'+i+'">'+state+'</p>'+
                    '<input class="state" id="jsroId'+i+'" type="hidden" value="'+jsroId+'">'+
                    '</div>'+
                    '<div class="btn_box">'+
                    '<input type="radio" name="state" '+status+'='+status+' id="in'+i+'" class="key" onclick="cc('+i+')">'+
                    '<label for="in'+i+'" class="key-bg">'+
                    '<span class="circle"></span>'+
                    '</label>'+
                    '</div>'+
                    '</div>';
            }
            var htmls='<div class="buttom">'+
                '<button class=\'btn\' onclick="updatePaneSava('+i+')">保存</button>'+
                '</div>';
            $('#box').html(html+htmls);
        }
    })
})
//更该状态
function cc(i){
    $(".info_title1").html("关")
    if($('#in'+i).prop("checked")){
        $("#heaterState"+i).html("开");
    }else{
        $("#heaterState"+i).html("关");
    }
}
//保存信息
function updatePaneSava(i){
    var deviceInformation=$("#deviceInformation").val();
    //设备Id
    var id=JSON.parse(deviceInformation).id;
    //按键值
    var type=JSON.parse(deviceInformation).type;
    for(var j=0;j<=i;j++){
        //状态
        var heaterState=$("#heaterState"+j).html();
        if(heaterState=="开"){
            //情景表Id
            var jsroId=$("#jsroId"+j).val();
        }
    }
    $.ajax({
        type: "post",
        url: "./updatePanelMode",
        data: {
            cpScenarioId:jsroId,
            cpKeyValue:type,
            cpDeviceId:id
        },
        dataType: "json",
        success: function(result) {
            toast(result.msg);
        }
    })
}
//保存成功后弹出已完成
function toast(msg){
    console.log(msg)
    $("#resultMsg").html(msg)
    var $toast = $('#toast');
    if ($toast.css('display') != 'none') return;
    $toast.fadeIn(100);
    setTimeout(function () {
        $toast.fadeOut(100);
    }, 2000);
}