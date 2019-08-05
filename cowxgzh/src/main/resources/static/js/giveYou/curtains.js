$(function() {
	//全开
	$('#open,#close,#pause').click(function() {
		$(this).addClass('selection').removeClass('operation');
		if($(this).parent().siblings().children().hasClass('selection')) {
			$(this).parent().siblings().children().addClass('operation').removeClass('selection');
		}
		var id = $(this).attr('id');
		controlDevice(id);
	});
});
//控制设备
function controlDevice(id){
	var deviceInformation = $('#deviceInformation').val();
	var deviceObj = JSON.parse(deviceInformation);
	var zz = {};
	zz.sn = deviceObj.sn;
	zz.mac = deviceObj.mac;
	zz.isNeedCache = "false";
	if (id=="open"){
		if (deviceObj.type==16){
			zz.status = "8080";
		}
		if (deviceObj.type==30){
			zz.status = "000200018080";
		}
	}
	if (id=="pause"){
		if (deviceObj.type==16){
			zz.status = "2020";
		}
		if (deviceObj.type==30){
			zz.status = "000200012020";
		}
	}
	if (id=="close"){
		if (deviceObj.type==16){
			zz.status = "4040";
		}
		if (deviceObj.type==30){
			zz.status = "000200014040";
		}
	}
	console.log(zz)
	$.post('http://www.fangzhizun.com/device/vanhi/YHDeviceControlServlet', zz, function(data) {
		console.log(data)
		if (data.code == 0 && data.body[0].resultCode == 0) {
			//alert(data.body[0].resultMsg);
		} else {
			alert(data.body[0].resultMsg);
		}
	});
}
