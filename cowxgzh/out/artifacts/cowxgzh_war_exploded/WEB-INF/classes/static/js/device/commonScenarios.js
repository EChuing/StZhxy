//查询设备信息
$(function(){
    var hsId=$("#hsId").val();
    var html="";
    $.ajax({
        type: "post",
        url: "./selectUserDevice",
        data: {
            hsId:hsId
        },
        dataType: "json",
        success: function(result) {
            var body=result.body;
            var bodyParse=JSON.parse(body);
            // console.log(bodyParse)
            if(bodyParse!="" && bodyParse!=null){
                for(var i in bodyParse){
                    //获取设备信息
                    var id=bodyParse[i].id;
                    var devId=bodyParse[i].devId;
                    var brandId=bodyParse[i].devBrandId;
                    var devFirstType=bodyParse[i].devFirstType;
                    var devSecondType=bodyParse[i].devSecondType;
                    var devAuthSecret=bodyParse[i].devAuthSecret;
                    var devNickname=bodyParse[i].devNickname;
                    var devAuthId=bodyParse[i].devAuthId;
                    var deviceStatus=bodyParse[i].devStatus;

                    //显示设备对应图片
                    bodyParse[i].devImg="img/ic_dt_safecenter_wifi_icon.png";
                    if (devFirstType==1 && devSecondType==1){//开关
                        bodyParse[i].devImg="img/ic_dt_cw_light_icon.png";
                    }
                    if (devFirstType==2){//插座
                        if(devSecondType==25 || devSecondType==37){
                            bodyParse[i].devImg="img/ic_dt_plug_icon.png";
                        }
                    }
                    if (devFirstType==4 && devSecondType==4){//空调
                        bodyParse[i].devImg="img/ic_dt_aircondition_icon.png";
                    }
                    if (devFirstType==6){//窗帘
                        if (devSecondType==6 || devSecondType==38){
                            bodyParse[i].devImg="img/ic_dt_curtain_icon.png";
                        }
                    }
                    if(brandId ==20){
                        if(devFirstType ==1 || devFirstType==2 || devFirstType==4 || devFirstType==6 || devFirstType==17 || devFirstType==23){
                            if(devSecondType ==1 || devSecondType==25 || devSecondType==4 || devSecondType==6 || devSecondType==17
                                || devSecondType==31 || devSecondType==36 || devSecondType==37 || devSecondType==38){
                                //判断设备是否在线状态
                                if(deviceStatus==""){
                                    var deviceState="不在线-未知状态";
                                }else{
                                    deviceState="在线"
                                }
                                //显示设备开关状态
                                var status="关";
                                var statusCheck = "checkbox";
                                var  stateStatus="";
                                var twoState="";
                                var  newTwoState="";
                                if (devFirstType==2){
                                    if(devSecondType==25){//插座
                                        twoState=parseInt(deviceStatus.slice(8,12), 16).toString(2);
                                        if (twoState.length<16){
                                            twoState="00"+twoState;
                                        }
                                        if(twoState.slice(0,1)==1){
                                            status="开";
                                            statusCheck = "checked";
                                        }else{
                                            status="关";
                                            statusCheck = "checkbox";
                                        }
                                    }
                                    if (devSecondType==37){
                                        if(deviceStatus=="8080"){
                                            status="开";
                                            statusCheck = "checked";
                                        }else{
                                            status="关";
                                            statusCheck = "checkbox";
                                        }
                                    }
                                }
                                if (devFirstType==1 && devSecondType==1){//灯
                                    stateStatus=deviceStatus;
                                    console.log(stateStatus)
                                    twoState=parseInt(stateStatus, 16).toString(2);
                                    newTwoState=PreFixInterge(twoState,16);
                                    if(newTwoState.slice(8,9)==1){
                                        status="开";
                                        statusCheck = "checked";
                                    }
                                    else{
                                        status="关";
                                        statusCheck = "checkbox";
                                    }
                                }
                                if (devFirstType==4 && devSecondType==4){//空调
                                    console.log(deviceStatus)
                                    stateStatus=deviceStatus.slice(26,28);
                                    console.log(stateStatus)
                                    if(stateStatus=="21"){
                                        status="开";
                                        statusCheck = "checked";
                                    }
                                    else{
                                        status="关";
                                        statusCheck = "checkbox";
                                    }
                                }

                                if (devFirstType==6){
                                    if (devSecondType==6){ //窗帘
                                        stateStatus=deviceStatus.slice(2,4);
                                        if (stateStatus==80){
                                            status="开";
                                            statusCheck = "checked";
                                        }
                                        else{
                                            status="关";
                                            statusCheck = "checkbox";
                                        }
                                    }
                                    if (devSecondType==38){
                                        stateStatus=deviceStatus.slice(8,12); // 00020001E02064000000
                                        console.log(stateStatus)
                                        if (stateStatus=="E040"){
                                            status="关";
                                            statusCheck = "checkbox";
                                        }
                                        else {
                                            status="开";
                                            statusCheck = "checked";
                                        }
                                    }
                                }
                                if (devFirstType==23 && devSecondType==31 || devFirstType==23 && devSecondType==36){
                                    console.log(stateStatus)
                                    //2.4冷暖灯
                                    stateStatus=deviceStatus.slice(0,2);
                                    if (stateStatus==80){
                                        status="开";
                                        statusCheck = "checked";
                                    }
                                    else{
                                        status="关";
                                        statusCheck = "checkbox";
                                    }
                                }
                                html +='<div class="content">'+
                                    '<img onclick="deviceDtails('+id+','+hsId+','+devId+','+devFirstType+','+devSecondType+',\''+devAuthId+'\',\''+devAuthSecret+'\')" src="'+bodyParse[i].devImg+'"/>'+
                                    '<div class="content_info" onclick="deviceDtails('+id+','+hsId+','+devId+','+devFirstType+','+devSecondType+',\''+devAuthId+'\',\''+devAuthSecret+'\')">'+
                                    '<text class="info_title" id="deviceName'+i+'">'+devNickname+'</text>'+
                                    '<text class="state" id="deviceState'+i+'">'+deviceState+'</text>'+
                                    '<text class="state" id="state'+i+'">'+status+'</text>'+
                                    '<input class="state" id="devFirstType'+i+'" type="hidden"  value="'+bodyParse[i].devFirstType+'">'+
                                    '<input class="state" id="devSecondType'+i+'" type="hidden"  value="'+bodyParse[i].devSecondType+'">'+
                                    '<input class="state" id="status'+i+'" type="hidden"  value="'+bodyParse[i].devStatus+'">'+
                                    '<input class="state" id="devId'+i+'" type="hidden"  value="'+bodyParse[i].devId+'">'+
                                    '<input class="state" id="devAuthSecret'+i+'" type="hidden" value="'+bodyParse[i].devAuthSecret+'">'+
                                    '<input class="state" id="devAuthId'+i+'" type="hidden" value="'+bodyParse[i].devAuthId+'">'+
                                    '</div>'+
                                    '<div class="btn_box">'+
                                    '<input type="checkbox" '+statusCheck+'='+statusCheck+' id="in'+i+'" class="key" onclick="cc('+i+')">'+
                                    '<label for="in'+i+'" class="key-bg" onclick="openingDevice('+i+')">'+
                                    '<span class="circle"></span>'+
                                    '</label>'+
                                    '</div>'+
                                    '</div>';
                            }
                        }
                    }
                }
            }else{
                html='<div class="aa" style="width:100%;margin-top:100px;font-size:18px;text-align:center;">'+
                    '<p class="info_title" id="heater">该房间没有绑定设备</p>'+
                    '</div>';
            }
            $('#devicesDisplaying').html(html);
        }
    })
});
function PreFixInterge(num,n){
    //num代表传入的数字，n代表要保留的字符的长度
    return (Array(n).join(0)+num).slice(-n);
}
//更改状态
function cc(i){
    var devId=$("#devId"+i).val();
    if($('#in'+i).prop("checked")){
        $("#state"+i).html("开");
    }else{
        $("#state"+i).html("关");
    }
}
//单控设备
function openingDevice(i){
    var state=$("#state"+i).html(); //开关状态
    var devFirstType=$("#devFirstType"+i).val(); //一级设备
    var devSecondType=$("#devSecondType"+i).val(); //二级设备
    var status=$("#status"+i).val(); //空调开关码
    var devId=$("#devId"+i).val(); //设备类型
    var devAuthSecret=$("#devAuthSecret"+i).val();//设备网关
    var devAuthId=$("#devAuthId"+i).val();  //设备SN码
    var devSwitchInstruction={//控制指令
        devId:devId,
        sn:devAuthId,
        status:"",
        mac:devAuthSecret,
        isNeedCache:false,
        resultCode:0
    }
    if(devFirstType==4){ //空调
        if(state=="开"){
            if (devSecondType==4){
                devSwitchInstruction.status=status;
            }
        }else{
            if (devSecondType==4){
                devSwitchInstruction.status=status;
            }
        }
    }
    if(devFirstType==1){//灯光
        if(state=="关"){
            if (devSecondType==1){
                devSwitchInstruction.status="8080";
            }
        }else{
            if (devSecondType==1){
                devSwitchInstruction.status="8010";
            }
        }
    }
    if(devFirstType==2){//插座
        if(state=="开"){
            if (devSecondType==25){
                devSwitchInstruction.status="80";
            }
            if(devSecondType==37){
                devSwitchInstruction.status="8000";
            }
        }else{
            if (devSecondType==25){
                devSwitchInstruction.status="81";
            }
            if(devSecondType==37){
                devSwitchInstruction.status="8080";
            }
        }
    }
    if(devFirstType==6){//窗帘
        if(state=="关"){
            if (devSecondType==6){
                devSwitchInstruction.status="8080";
            }if (devSecondType==38) {
                devSwitchInstruction.status="000200018080";
            }
        }else{
            if (devSecondType==6){
                devSwitchInstruction.status="4040";
            }if (devSecondType==38) {
                devSwitchInstruction.status="000200014040";
            }
        }
    }
    if(devFirstType==23){ //2.4G冷暖灯
        if(state=="关"){
            if (devSecondType==31 || devSecondType==36){
                devSwitchInstruction.status="80070707";
            }
        }else{
            if (devSecondType==31 || devSecondType==36){
                devSwitchInstruction.status="00070707";
            }
        }
    }
    if(devFirstType==17|| devFirstType==22){
        if (devSecondType==17  ||  devSecondType==28 || devSecondType==29){
            $("#in"+i).attr("type","checked");
            alert("该设备不支持开关");
            return;
        }
    }
    var list=[];
    list.push(devSwitchInstruction);
    $.ajax({
        type: "post",
        url: "./controlSwitch",
        data: {
            devSwitchInstruction:JSON.stringify(list),
            devState:state
        },
        dataType: "json",
        success: function(result) {
            console.log(result)
                toast(result.msg);
        }
    })
}
//启动成功后弹出启动成功
function toast(msg){
    $("#msg").html(msg)
    var $toast = $('#toast');
    if ($toast.css('display') != 'none') return;
    $toast.fadeIn(100);
    setTimeout(function () {
        $toast.fadeOut(100);
    }, 2000);
}

//设备详情页面
function deviceDtails(id,hsId,devId,devFirstType,devSecondType,devAuthId ,devAuthSecret){
    if (devFirstType==4|| devFirstType==6 ||devFirstType==17 || devFirstType==23 || devFirstType==22 ){
        if(devSecondType==4 || devSecondType==6 || devSecondType==17 || devSecondType==31 ||devSecondType==36 || devSecondType==28 || devSecondType==29 || devSecondType==38){

            if(devFirstType==4 && devSecondType==4){//空调
                location.href="giveYouAirConditioning?sn="+devAuthId+"&mac="+devAuthSecret+"&type="+devId;
            }
            if(devFirstType==6){//窗帘
                if(devSecondType==6 || devSecondType==38){
                    location.href="giveYouCurtains?sn="+devAuthId+"&mac="+devAuthSecret+"&type="+devId;
                }
            }
            if(devFirstType==23){
                if(devSecondType==31){//2.4G冷暖灯
                    location.href="givWarmAndColdLamp?sn="+devAuthId+"&mac="+devAuthSecret+"&type="+devId;
                }
                if(devSecondType==36){//调节灯
                    location.href="givAdjustingLamp?sn="+devAuthId+"&mac="+devAuthSecret+"&type="+devId;
                }
            }
            if(devFirstType==17 && devSecondType==17){//控制面板
               window.location.href = "givScenarioPanel?hsId="+hsId+"&id="+id+"&type="+devId;
            }
        }else{
            alert("只支持空调、窗帘、调节灯")
         }
    } else{
        alert("只支持空调、窗帘、调节灯")
    }
}