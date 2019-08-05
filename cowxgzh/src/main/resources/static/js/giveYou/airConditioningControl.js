var isOn = false;//开关
var codeType = 0;//功能

$(function(){
	//减低温度
	$('#reduce').click(function(){
		var mode = $('#mode').children('[data-status]:visible').attr('data-control');
		if(mode==2){
			return;
		}
		var temperature = $('#temperature').html();
		if(temperature <= 16){
			return ;
		}else{
			temperature --;
		}
		$('#temperature').html(temperature);
		$('#ADR_TMP').html(temperature);
		codeType = 5;
		controlDevice();
	});
	//增加温度
	$('#add').click(function(){
		var mode = $('#mode').children('[data-status]:visible').attr('data-control');
		if(mode==2){
			return;
		}
		var temperature = $('#temperature').html();
		if(temperature >= 30){
			return ;
		}else{
			temperature ++;
		}
		$('#temperature').html(temperature);
		$('#ADR_TMP').html(temperature);
		codeType = 4;
		controlDevice();
	});
	//开启/关闭
	$('#open,#close').click(function(){
		var status = $(this).attr('data-status');
		$('#state').children('[data-status="'+status+'"]').show().siblings().hide();
		codeType = 0;
		controlDevice();
	});
	//模式
	$('.mode-automatic,.mode-cold,.mode-heat,.mode-dehumidif,.mode-airSupply').click(function(){
		var status = $(this).attr('data-status');
		$('#mode').children('[data-status="'+status+'"]').show().siblings().hide();
		codeType = 1;
		controlDevice();
	});
	//风速
	$('.windSpeed-automatic,.windSpeed-high,.windSpeed-middle,.windSpeed-low').click(function(){
		var status = $(this).attr('data-status');
		$('#windSpeed').children('[data-status="'+status+'"]').show().siblings().hide();
		codeType = 2;
		controlDevice();
	});
	//扫风
	$('.sweepWind-automatic,.sweepWind-Manual').click(function(){
		var status = $(this).attr('data-status');
		$('#sweepWind').children('[data-status="'+status+'"]').show().siblings().hide();
		if (status == '00') {
			codeType = 6;
		} else {
			codeType = 7;
		}
		controlDevice();
	});
	//加载初始状态
	initStatus();
});

//加载初始状态
function initStatus(){
	var deviceInformation = $('#deviceInformation').val();
	var deviceObj = JSON.parse(deviceInformation);
	var status = deviceObj.status;
	/*这里是解析设备状态给页面参数*/
	console.log('status:'+status);
	var ROOM_TMP = status.slice(0,2);//室温
	var GROUP_NUM = status.slice(4,6)+status.slice(2,4);//码组号
	var ADR_MODE = status.slice(6,8);//模式
	var ADR_TMP = status.slice(8,10);//温度
	var ADR_FANLEV = status.slice(10,12);//风量（风速）
	var ADR_FANDIR = status.slice(12,14);//风向（扫风）
	var ADR_AFANDIR = status.slice(14,16);//自动风向
	var ADR_MFANDIR = status.slice(16,18);//手动风向
	var ADR_CKHOUR = status.slice(18,20);//时钟小时
	var ADR_TIMEON = status.slice(20,22);//定开时间（小时）
	var ADR_TIMEOFF = status.slice(22,24);//定关时间（小时）
	var ADR_KEYVAL = status.slice(24,26);//键值
	var ADR_SYSFLAG = status.slice(26,28);//功能标志
	var ADR_TMSTATE7 = status.slice(28,30);//定时状态（7种）
	var ADR_TMSTATE3 = status.slice(30,32);//定时状态（3种）
	var ADR_CKMIN = status.slice(32,34);//时钟分钟
	$('#ROOM_TMP').html(parseInt(ROOM_TMP, 16));
	$('#GROUP_NUM').val(parseInt(GROUP_NUM, 16));
	$('#mode').children('[data-status="'+ADR_MODE+'"]').show().siblings().hide();
	$('#ADR_TMP').html(parseInt(ADR_TMP, 16) + 16);
	$('#temperature').html(parseInt(ADR_TMP, 16) + 16);
	$('#windSpeed').children('[data-status="'+ADR_FANLEV+'"]').show().siblings().hide();
	$('#sweepWind').children('[data-status="'+ADR_FANDIR+'"]').show().siblings().hide();
	$('#state').children('[data-status="01"]').show().siblings().hide();
}
//控制设备
function controlDevice(){
	var deviceInformation = $('#deviceInformation').val();
	var deviceObj = JSON.parse(deviceInformation);
	var zz = {};
	zz.sn = deviceObj.sn;
	zz.mac = deviceObj.mac;
	zz.type = deviceObj.type;
	zz.code = $('#GROUP_NUM').val();//码组号
	zz.isOn = $('#state').children('[data-status]:visible').attr('data-control');//开关
	zz.temp = Number($('#ADR_TMP').html());//温度
	zz.mode = $('#mode').children('[data-status]:visible').attr('data-control');//模式
	zz.speed = $('#windSpeed').children('[data-status]:visible').attr('data-control');//风量（风速）
	zz.codeType = codeType;
	console.log(zz);
	$.post('http://www.fangzhizun.com/device/vanhi/AirControl', zz, function(data) {
		console.log(data)
		if (data.code == 0 && data.body[0].resultCode == 0) {
			//alert(data.body[0].resultMsg);
		} else {
			alert(data.body[0].resultMsg);
		}
	});
}