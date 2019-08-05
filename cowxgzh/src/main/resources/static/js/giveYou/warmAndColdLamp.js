$(function(){
	var deviceInformation=$("#deviceInformation").val();
	if (JSON.parse(deviceInformation).type==4){
        $("#colorTemperatureStatusDiv").hide();
    }
	console.log(deviceInformation)
	var status=JSON.parse(deviceInformation).status;
	var switchStatus=status.slice(0,2);
	var brightnessStatus=parseInt(parseInt(status.slice(2,4),16));
	// if (brightnessStatus<9){
    //     brightnessStatus="0"+brightnessStatus;
    // }
	var colorTemperatureStatus=parseInt(parseInt(status.slice(4,6),16));
	//灯开关状态
	if (switchStatus==80) {
		$("#img").attr("src","img/2.4Gkai.jpg");
	}else {
		$("#img").attr("src","img/2.4Gguan.jpg");
	}
	//储存灯的状态
	$("#switchStatus").val(switchStatus);
	$("#brightnessStatus").val(brightnessStatus);
	$("#colorTemperatureStatus").val(colorTemperatureStatus);
	//灯亮度状态
	$("#sweepWind1").val(brightnessStatus);
	//灯色温状态
	$("#sweepWind2").val(colorTemperatureStatus);
})

//开灯关灯控制
function lampSwitch() {
	var switchStatus=$("#switchStatus").val();
	var brightnessStatus=$("#brightnessStatus").val();
	var colorTemperatureStatus=$("#colorTemperatureStatus").val();
    brightnessStatus="0"+parseInt(brightnessStatus).toString(16)
    if (colorTemperatureStatus<16){
        colorTemperatureStatus="0"+parseInt(colorTemperatureStatus).toString(16);
    }else{
        colorTemperatureStatus=parseInt(colorTemperatureStatus).toString(16);
    }
    console.log(brightnessStatus)
    console.log(colorTemperatureStatus)
	var deviceInformation=$("#deviceInformation").val();
	var sn=JSON.parse(deviceInformation).sn;
	var mac=JSON.parse(deviceInformation).mac;
	var status="";
	var obj={};
	if (switchStatus==80) {
		status="00"+brightnessStatus+colorTemperatureStatus+"07";
		//执行关灯
		obj.sn=sn,
		obj.mac=mac
		obj.isNeedCache = "false";
		obj.status = status;
		obj.instruction = "控制设备";
		obj.brandId ="20";
		obj = JSON.stringify(obj);
	}else {
		//执行开灯
		status="80"+brightnessStatus+colorTemperatureStatus+"07";
		obj.devId="13";
		obj.sn=sn;
		obj.mac=mac;
		obj.isNeedCache = "false";
		obj.status = status;
		obj.resultCode =0;
		obj = JSON.stringify(obj);
	}
	$.ajax({
		type: "post",
		url: "./controlLight",
		data: {
            obj:obj
		},
		dataType: "json",
		success: function(result) {
			alert(result.msg)
            var body=JSON.parse(result.body);
            var status=body[0].status;
            var switchStatus=status.slice(0,2);
            var brightnessStatus=parseInt(parseInt(status.slice(2,4),16));
            // if (brightnessStatus<9){
            //     brightnessStatus="0"+brightnessStatus;
            // }
            var colorTemperatureStatus=parseInt(parseInt(status.slice(4,6),16));
            console.log(colorTemperatureStatus)
            // if (colorTemperatureStatus<9){
            //     colorTemperatureStatus="0"+colorTemperatureStatus;
            // }
            //储存灯的状态
            $("#switchStatus").val(switchStatus);
            $("#brightnessStatus").val(brightnessStatus);
            $("#colorTemperatureStatus").val(colorTemperatureStatus);
            //灯亮度状态
            $("#sweepWind1").val(brightnessStatus);
            //灯色温状态
            $("#sweepWind2").val(colorTemperatureStatus);
            if (switchStatus==80){
                $("#img").attr("src","img/2.4Gkai.jpg");
            }else{
                $("#img").attr("src","img/2.4Gguan.jpg");
            }
		}
	})
}
//亮度控制
function onBrightness(value) {
    var deviceInformation=$("#deviceInformation").val();
    var sn=JSON.parse(deviceInformation).sn;
    var mac=JSON.parse(deviceInformation).mac;
    var switchStatus=$("#switchStatus").val();
    var colorTemperatureStatus=$("#colorTemperatureStatus").val();
    value="0"+parseInt(value).toString(16);
   if (colorTemperatureStatus<16){
       colorTemperatureStatus="0"+parseInt(colorTemperatureStatus).toString(16);
   }else{
       colorTemperatureStatus=parseInt(colorTemperatureStatus).toString(16);
   }
    var status=switchStatus+value+colorTemperatureStatus+"07";
    var obj={};
    obj.sn=sn,
    obj.mac=mac
    obj.isNeedCache = "false";
    obj.status = status;
    obj.instruction = "控制设备";
    obj.brandId ="20";
    obj = JSON.stringify(obj);
    console.log(obj)
    $.ajax({
        type: "post",
        url: "./controlLight",
        data: {
            obj:obj
        },
        dataType: "json",
        success: function(result) {
            var body=JSON.parse(result.body);
            var status=body[0].status;
            var switchStatus=status.slice(0,2);
            var brightnessStatus=parseInt(parseInt(status.slice(2,4),16));
            var ColorTemperatureStatus=parseInt(parseInt(status.slice(4,6),16));
            //储存灯的状态
            $("#switchStatus").val(switchStatus);
            $("#brightnessStatus").val(brightnessStatus);
            $("#ColorTemperatureStatus").val(ColorTemperatureStatus);
            if (switchStatus==80){
                $("#img").attr("src","img/2.4Gkai.jpg");
            }else{
                $("#img").attr("src","img/2.4Gguan.jpg");
            }
        }
    })
}
//色温控制
function onColorTemperature(value) {
    var deviceInformation=$("#deviceInformation").val();
    var sn=JSON.parse(deviceInformation).sn;
    var mac=JSON.parse(deviceInformation).mac;

    var switchStatus=$("#switchStatus").val();
    var brightnessStatus= $("#brightnessStatus").val();
    brightnessStatus="0"+parseInt(brightnessStatus).toString(16);
    if (value<16){
        value="0"+parseInt(value).toString(16);
    }else{
        value=parseInt(value).toString(16);
    }
    var status=switchStatus+brightnessStatus+value+"07";
    var obj={};
    obj.sn=sn,
        obj.mac=mac
    obj.isNeedCache = "false";
    obj.status = status;
    obj.instruction = "控制设备";
    obj.brandId ="20";
    obj = JSON.stringify(obj);
    console.log(obj)
    $.ajax({
        type: "post",
        url: "./controlLight",
        //Lighting adjustment
        data: {
            obj:obj
        },
        dataType: "json",
        success: function(result) {
            var body=JSON.parse(result.body);
            var status=body[0].status;
            var switchStatus=status.slice(0,2);
            var brightnessStatus=parseInt(parseInt(status.slice(2,4),16));
            var ColorTemperatureStatus=parseInt(parseInt(status.slice(4,6),16));
            //储存灯的状态
            $("#switchStatus").val(switchStatus);
            $("#brightnessStatus").val(brightnessStatus);
            $("#ColorTemperatureStatus").val(ColorTemperatureStatus);
            if (switchStatus==80){
                $("#img").attr("src","img/2.4Gkai.jpg");
            }else{
                $("#img").attr("src","img/2.4Gguan.jpg");
            }
        }
    })
}