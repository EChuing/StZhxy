//查询设备信息
$(function(){
    var html="";
    $.ajax({
        type: "post",
        url: "./selectUserDevice",
        data: {
        },
        dataType: "json",
        success: function(result) {
            var body=result.body;
            var bodyParse=JSON.parse(body);
            console.log(bodyParse)
            if(bodyParse!=""){
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
                    if (devFirstType==1 && devSecondType==1){
                        //开关
                        bodyParse[i].devImg="img/ic_dt_cw_light_icon.png";
                    }
                    if (devFirstType==2 && devSecondType==25){
                        //插座
                        bodyParse[i].devImg="img/ic_dt_plug_icon.png";
                    }
                    if (devFirstType==4 && devSecondType==4){
                        //空调
                        bodyParse[i].devImg="img/ic_dt_aircondition_icon.png";
                    }
                    if (devFirstType==6 && devSecondType==6){
                        //窗帘
                        bodyParse[i].devImg="img/ic_dt_curtain_icon.png";
                    }
                    //brandId ==20 && devFirstType==1  && devSecondType==1 || devFirstType==2  && devSecondType==25
                    //                         || devFirstType==4  && devSecondType==4 || devFirstType==6  && devSecondType==6
                    //                         || devFirstType==17 && devSecondType==17 || devFirstType==22 && devSecondType==28
                    //                         || devFirstType==22 && devSecondType==29 || devFirstType==23 && devSecondType==31
                    if(brandId ==20 && devFirstType==1  && devSecondType==1 || devFirstType==2  && devSecondType==25
                        || devFirstType==4  && devSecondType==4 || devFirstType==6  && devSecondType==6
                        || devFirstType==23 && devSecondType==31){

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
                        if (devFirstType==2 && devSecondType==25){
                        	console.log(devFirstType)
                        	//插座
                        	// stateStatus=deviceStatus.slice(8,10);
                        	// twoState=parseInt(stateStatus, 16).toString(2);
                        	// newTwoState=PreFixInterge(twoState,8);
                        	// if(newTwoState.slice(0,1)==1){
                        	// 	status="开";
                        	// 	statusCheck = "checked";
                        	// }else{
                        	// 	status="关";
                        	// 	statusCheck = "checkbox";
                        	// }
                        }
                        if (devFirstType==1 && devSecondType==1){
                        	//灯
                        	// stateStatus=deviceStatus;
                        	// console.log(stateStatus)
                        	// twoState=parseInt(stateStatus, 16).toString(2);
                        	// newTwoState=PreFixInterge(twoState,16);
                        	// if(newTwoState.slice(8,9)==1){
                        	// 	status="开";
                        	// 	statusCheck = "checked";
                        	// }
                        	// else{
                        	// 	status="关";
                        	// 	statusCheck = "checkbox";
                        	// }
                        }
                        if (devFirstType==6 && devSecondType==6){
                            //窗帘
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
                        if (devFirstType==23 && devSecondType==31){
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
                            '<img onclick="deviceDtails('+id+','+devId+','+devFirstType+','+devSecondType+',\''+devAuthId+'\',\''+devAuthSecret+'\')" src="'+bodyParse[i].devImg+'"/>'+
                            '<div class="content_info" onclick="deviceDtails('+id+','+devId+','+devFirstType+','+devSecondType+',\''+devAuthId+'\',\''+devAuthSecret+'\')">'+
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
    // var deviceState=$("#deviceState"+i).html();
    //开关状态
    var state=$("#state"+i).html();
    //一级设备
    var devFirstType=$("#devFirstType"+i).val();
    //二级设备
    var devSecondType=$("#devSecondType"+i).val();
    //空调开关码
    var status=$("#status"+i).val();
    //设备类型
    var devId=$("#devId"+i).val();
    //设备网关
    var devAuthSecret=$("#devAuthSecret"+i).val();
    //设备SN码
    var devAuthId=$("#devAuthId"+i).val();
    var devSwitchInstruction={};

    if(devFirstType==4 && devSecondType==4){
        //空调
        if(state=="开"){
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:status,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }else{
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:status,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }
    }
    if(devFirstType==1 && devSecondType==1){
        //灯光
        if(state=="关"){
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:8080,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }else{
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:8010,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }
    }
    if(devFirstType==2 && devSecondType==25){
        //插座
        if(state=="开"){
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:80,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }else{
            var status=81;
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:81,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }
    }
    if(devFirstType==6 && devSecondType==6){
        //窗帘
        if(state=="关"){		//暂时是关
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:8080,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }else{
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:4040,
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }
    }
    if(devFirstType==23 && devSecondType==31){
        //2.4G冷暖灯
        if(state=="关"){
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:"80070707",
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }else{
            devSwitchInstruction={
                devId:devId,
                sn:devAuthId,
                status:"00070707",
                mac:devAuthSecret,
                isNeedCache:true,
                resultCode:0
            }
        }
    }
    if(devFirstType==17 && devSecondType==17 || devFirstType==22 && devSecondType==28
        ||devFirstType==22 && devSecondType==29){
        $("#in"+i).attr("type","checked");
        alert("该设备不支持开关");
        return;
    }
    var list=[];
    list.push(devSwitchInstruction)
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
function deviceDtails(id,devId,devFirstType,devSecondType,devAuthId ,devAuthSecret){
    if (devFirstType==4 && devSecondType==4 || devFirstType==6 && devSecondType==6 ||devFirstType==17 && devSecondType==17 || devFirstType==23 && devSecondType==31|| devFirstType==22 && devSecondType==28 || devFirstType==22 && devSecondType==29){
        if(devFirstType==4 && devSecondType==4){
            //空调
            var zz = {};
            zz.sn = devAuthId;
            zz.mac=devAuthSecret;
            zz.type=devId;
            var giveParameter = JSON.stringify(zz);
            // var Url= "giveYouAirConditioning?giveParameter="+giveParameter;
            // window.location.href =encodeURI(Url);
            $.ajax({
                type: "post",
                url: "./giveYouAirConditioning",
                data: {
                    giveParameter:giveParameter
                },
                dataType: "json",
                // success: function(result) {
                //     console.log(result)
                //     //window.location.href=("airConditioning.jsp?result=" + result);
                // }
            })
        }
        if(devFirstType==6 && devSecondType==6){
            //窗帘
            var zz = {};
            zz.sn = devAuthId;
            zz.mac=devAuthSecret;
            zz.type=devId;
            var giveParameter = JSON.stringify(zz);
            var Url = "giveYouCurtains?giveParameter="+giveParameter;
            window.location.href = encodeURI(Url);
        }
        if(devFirstType==23 && devSecondType==31){
            //2.4G冷暖灯调节
            var zz = {};
            zz.sn = devAuthId;
            zz.mac=devAuthSecret;
            zz.type=devId;
            var giveParameter = JSON.stringify(zz);
            var Url= "givAdjustingLamp?giveParameter="+giveParameter;
            window.location.href =encodeURI(Url);
        }
        if(devFirstType==17 && devSecondType==17){
            //控制面板
            window.location.href = 'panelSettings?sroHrId='+sroHrId+'&id='+id;
        }
        if(devFirstType==22 && devSecondType==28){
            //小海
            var zz = {};
            zz.hrId=sroHrId;
            zz.id = id;
            var giveParameter = JSON.stringify(zz);
            var Url= "givLittleSea?giveParameter="+giveParameter;
            window.location.href =encodeURI(Url);
        }
        if(devFirstType==22 && devSecondType==29){
            //伊丽莎白
            var zz = {};
            zz.hrId=sroHrId;
            zz.id = id;
            var giveParameter = JSON.stringify(zz);
            var Url= "givElizabeth?giveParameter="+giveParameter;
            window.location.href =encodeURI(Url);
        }
    } else{
    //    alert("只支持控制面板、空调、窗帘、调节灯")
        alert("只支持空调、窗帘、调节灯")
    }
}