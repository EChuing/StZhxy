$(function () {
    var giveParameter=$("#giveParameter").val();
    var givParameterObj=JSON.parse(giveParameter);
    var hsId=givParameterObj.hsId;
    var jsroPatternId=givParameterObj.spdId;
    $.ajax({
        url:"./selectScene",
        type:"post",
        dateType:"json",
        data:{
            hsId:hsId,
            jsroPatternId:jsroPatternId
        },
        success: function(result){
            var state="关";
            //冷暖灯亮度色温初始化
            var colorTemperature=50;
            var brightness=50;

            if(result.body!="" && result.body!=null){
                var resultJson=JSON.parse(result.body);					//设备信息+情景模式
                console.log(resultJson)
                var resultJson1=resultJson.body1;			            //设备信息
                if(resultJson.body2!=""){
                    //当情景模式表查询出信息
                    var resultJson2=resultJson.body2;		//情景模式
                    var html="";

                    for(var i in resultJson1){
                        var status = "checkbox";
                        var sn=resultJson1[i].devAuthId; //获取设备表的SN
                        resultJson1[i].activation="0";
                        //显示图片
                        if (resultJson1[i].devFirstType==1 || resultJson1[i].devFirstType==23){//灯
                            if (resultJson1[i].devSecondType==1 ||  resultJson1[i].devSecondType==31 ||resultJson1[i].devSecondType==36){
                                resultJson1[i].brightness=50;
                                resultJson1[i].colorTemperature=50;
                                resultJson1[i].devImg="img/ic_dt_cw_light_icon1.png";
                            }
                        }
                        if (resultJson1[i].devFirstType==2){ //插座
                            if (resultJson1[i].devSecondType==25 || resultJson1[i].devSecondType==37){
                                resultJson1[i].devImg="img/ic_dt_plug_icon1.png";
                            }
                        }
                        if (resultJson1[i].devFirstType==4 && resultJson1[i].devSecondType==4){//空调
                            resultJson1[i].devImg="img/ic_dt_aircondition_icon1.png";
                        }
                        if (resultJson1[i].devFirstType==6){//窗帘
                            if (resultJson1[i].devSecondType==6 ||resultJson1[i].devSecondType==38){
                                resultJson1[i].devImg="img/ic_dt_curtain_icon1.png";
                            }
                        }
                        for(var j in resultJson2){
                            if(resultJson2[j].jsroWxgzhState!="" && resultJson2[j].jsroWxgzhState!=null
                                && resultJson2[j].jsroWxgzhState!=undefined){
                                var sroState=JSON.parse(resultJson2[j].jsroWxgzhState); //获取情景指令表的设置设备状态
                            }
                            var jsroInstruction=JSON.parse(resultJson2[j].jsroInstruction);

                            if (sroState!=null){
                                for(var l in sroState){
                                    var sn=sroState[l].sn;
                                    //判断设备信息表设备SN码等于情景模式情景指令的SN码
                                    if(sn==resultJson1[i].devAuthId){
                                        resultJson1[i].activation=1;
                                        if (resultJson1[i].devFirstType==1 || resultJson1[i].devFirstType==23){//灯
                                            if (resultJson1[i].devSecondType==1 ||  resultJson1[i].devSecondType==31 ||resultJson1[i].devSecondType==36){
                                                resultJson1[i].devImg="img/ic_dt_cw_light_icon.png";
                                            }
                                        }
                                        if (resultJson1[i].devFirstType==2){ //插座
                                            if (resultJson1[i].devSecondType==25 || resultJson1[i].devSecondType==37){
                                                resultJson1[i].devImg="img/ic_dt_plug_icon.png";
                                            }
                                        }
                                        if (resultJson1[i].devFirstType==4 && resultJson1[i].devSecondType==4){ //空调
                                            resultJson1[i].devImg="img/ic_dt_aircondition_icon.png";
                                        }
                                        if (resultJson1[i].devFirstType==6){//窗帘
                                            if (resultJson1[i].devSecondType==6 ||resultJson1[i].devSecondType==38){
                                                resultJson1[i].devImg="img/ic_dt_curtain_icon.png";
                                            }
                                        }
                                        var state=sroState[l].state;
                                        if(state=="关"){
                                            status = "checkbox";
                                        }else{
                                            status = "checked";
                                        }
                                        resultJson1[i].brightness=sroState[l].brightness;
                                        resultJson1[i].colorTemperature=sroState[l].colorTemperature;
                                    }
                                }
                            }
                            //获取情景指令表主键
                            $("#sroId").val(resultJson2[j].jsroId);
                        }
                        //情景表设置情景，做匹配
                        if (resultJson1[i].devBrandId==20){//品牌类型
                            if (resultJson1[i].devFirstType==1 || resultJson1[i].devFirstType==2 || resultJson1[i].devFirstType==4 || resultJson1[i].devFirstType==6 || resultJson1[i].devFirstType==23){//一级类型
                                if (resultJson1[i].devSecondType==1 || resultJson1[i].devSecondType==4 || resultJson1[i].devSecondType==6||resultJson1[i].devSecondType==25 ||resultJson1[i].devSecondType==31
                                    ||resultJson1[i].devSecondType==36 || resultJson1[i].devSecondType==37 ||resultJson1[i].devSecondType==38){//二级类型

                                    html +='<div class="content">'+
                                        '<img id="img'+i+'" onclick="closeDevice('+i+')" src="'+resultJson1[i].devImg+'" />'+
                                        '<div class="content_info">'+
                                        //显示设备名称以及开关状态
                                        '<p class="info_title" onclick="lightingAdjustment('+i+')" id="heater">'+resultJson1[i].devNickname+'</p>'+
                                        '<p class="state" onclick="lightingAdjustment('+i+')" id="heaterState'+i+'">'+state+'</p>'+
                                        //隐藏的设备信息
                                        '<input class="state" id="devId'+i+'" type="hidden" value="'+resultJson1[i].devId+'">'+
                                        '<input class="state" id="devBrandId'+i+'" type="hidden"  value="'+resultJson1[i].devBrandId+'">'+
                                        '<input class="state" id="activation'+i+'" type="hidden"  value="'+resultJson1[i].activation+'">'+
                                        '<input class="state" id="devFirstType'+i+'" type="hidden"  value="'+resultJson1[i].devFirstType+'">'+
                                        '<input class="state" id="devSecondType'+i+'" type="hidden"  value="'+resultJson1[i].devSecondType+'">'+
                                        '<input class="state" id="devAuthSecret'+i+'" type="hidden"  value="'+resultJson1[i].devAuthSecret+'">'+
                                        '<input class="state" id="devAuthId'+i+'" type="hidden" value="'+resultJson1[i].devAuthId+'">'+
                                        '<input class="state" id="status'+i+'" type="hidden" value="'+resultJson1[i].devStatus+'">'+
                                        // 冷暖灯设置的亮度
                                        '<input class="state" id="brightness'+i+'" type="hidden"  value="'+resultJson1[i].brightness+'">'+
                                        '<input class="state" id="colorTemperature'+i+'" type="hidden"  value="'+resultJson1[i].colorTemperature+'">'+
                                        '</div>'+
                                        '<div class="btn_box">'+
                                        '<input type="checkbox" '+status+'='+status+' id="in'+i+'" class="key" onclick="cc('+i+')">'+
                                        '<label for="in'+i+'" class="key-bg" onclick="openingDevice('+i+')">'+
                                        '<span class="circle" id="circle'+i+'"></span>'+
                                        '</label>'+
                                        '</div>'+
                                        '</div>';
                                }
                            }
                        }
                    }
                    var htmls='<div class="buttom">'+
                        '<button class=\'btn\' onclick="updateScenario('+i+')">保存</button>'+
                        '</div>';
                    $('#box').html(html+htmls);
                }else{
                    //冷暖灯亮度色温初始化、
                    var colorTemperature=50;
                    var brightness=50;
                    //当情景表没有设置情景模式
                    var html="";
                    var sroPattern=$('#pattern').val();
                    for(var i in resultJson1){
                        console.log(resultJson1)
                        //类型图片
                        if (resultJson1[i].devFirstType==1 || resultJson1[i].devFirstType==23){//灯
                            if (resultJson1[i].devSecondType==1 ||  resultJson1[i].devSecondType==31 ||resultJson1[i].devSecondType==36){
                                resultJson1[i].devImg="img/ic_dt_cw_light_icon1.png";
                            }
                        }
                        if (resultJson1[i].devFirstType==2){ //插座
                            if (resultJson1[i].devSecondType==25 || resultJson1[i].devSecondType==37){
                                resultJson1[i].devImg="img/ic_dt_plug_icon1.png";
                            }
                        }
                        if (resultJson1[i].devFirstType==4 && resultJson1[i].devSecondType==4){//空调
                            resultJson1[i].devImg="img/ic_dt_aircondition_icon1.png";
                        }
                        if (resultJson1[i].devFirstType==6){//窗帘
                            if (resultJson1[i].devSecondType==6 || resultJson1[i].devSecondType==38){
                                resultJson1[i].devImg="img/ic_dt_curtain_icon1.png";
                            }
                        }

                        if (resultJson1[i].devBrandId==20){//品牌类型
                            if (resultJson1[i].devFirstType==1 || resultJson1[i].devFirstType==2 || resultJson1[i].devFirstType==4 || resultJson1[i].devFirstType==6 || resultJson1[i].devFirstType==23){//一级类型
                                if (resultJson1[i].devSecondType==1 || resultJson1[i].devSecondType==4 || resultJson1[i].devSecondType==6||resultJson1[i].devSecondType==25 ||resultJson1[i].devSecondType==31
                                    ||resultJson1[i].devSecondType==36 || resultJson1[i].devSecondType==37 ||resultJson1[i].devSecondType==38){//二级类型

                                    html +='<div class="content">'+
                                        '<img id="img'+i+'" onclick="closeDevice('+i+')" src="'+resultJson1[i].devImg+'" />'+
                                        '<div class="content_info">'+
                                        //显示设备名称以及开关状态
                                        '<p class="info_title" onclick="lightingAdjustment('+i+')" id="heater">'+resultJson1[i].devNickname+'</p>'+
                                        '<p class="state" onclick="lightingAdjustment('+i+')"   id="heaterState'+i+'">'+state+'</p>'+
                                        //隐藏的设备信息
                                        '<input class="state" id="devId'+i+'" type="hidden"  value="'+resultJson1[i].devId+'">'+
                                        '<input class="state" id="devBrandId'+i+'" type="hidden"  value="'+resultJson1[i].devBrandId+'">'+
                                        '<input class="state" id="activation'+i+'" type="hidden"  value="0">'+
                                        '<input class="state" id="devFirstType'+i+'" type="hidden"  value="'+resultJson1[i].devFirstType+'">'+
                                        '<input class="state" id="devSecondType'+i+'" type="hidden"  value="'+resultJson1[i].devSecondType+'">'+
                                        '<input class="state" id="devAuthSecret'+i+'" type="hidden" value="'+resultJson1[i].devAuthSecret+'">'+
                                        '<input class="state" id="devAuthId'+i+'" type="hidden" value="'+resultJson1[i].devAuthId+'">'+
                                        '<input class="state" id="status'+i+'" type="hidden"  value="'+resultJson1[i].devStatus+'">'+
                                        // 冷暖灯设置的亮度
                                        '<input class="state" id="brightness'+i+'" type="hidden"   value="'+brightness+'">'+
                                        '<input class="state" id="colorTemperature'+i+'" type="hidden"   value="'+colorTemperature+'">'+
                                        '</div>'+
                                        '<div class="btn_box">'+
                                        '<input type="checkbox" id="in'+i+'" class="key" onclick="cc('+i+')">'+
                                        '<label for="in'+i+'" class="key-bg" onclick="openingDevice('+i+')">'+
                                        '<span class="circle"></span>'+
                                        '</label>'+
                                        '</div>'+
                                        '</div>';
                                }
                            }
                        }
                    }
                    var htmls='<div class="buttom">'+
                        '<button class=\'btn\' onclick="updateScenario('+i+')">保存</button>'+
                        '</div>';
                    $('#box').html(html+htmls);
                }
            }else{
                var htmls='<div class="aa" style="width:100%;margin-top:100px;font-size:18px;text-align:center;">'+
                    '<p class="info_title" id="heater">该房间没有绑定设备</p>'+
                    '</div>';
                $('#box').html(htmls);
            }
        }
    })
    //冷暖灯亮度设置
    //减低亮度
    $('#brightnessReduce').click(function(){
        var mode = $('#mode').children('[data-status]:visible').attr('data-control');
        if(mode==2){
            return;
        }
        var brightness = $('#brightness').html();
        if(brightness <= 5){
            return ;
        }else{
            brightness=parseInt(brightness)-5;
        }
        $('#brightness').html(brightness);

    });
    //增加亮度
    $('#brightnessAdd').click(function(){
        var mode = $('#mode').children('[data-status]:visible').attr('data-control');
        if(mode==2){
            return;
        }
        var brightness = $('#brightness').html();
        if(brightness >= 100){
            return ;
        }else{
            brightness=parseInt(brightness)+5;
        }
        $('#brightness').html(brightness);
    });
    //冷暖灯色温增加
    //减低亮度
    $('#colorTemperatureReduce').click(function(){
        var mode = $('#mode').children('[data-status]:visible').attr('data-control');
        if(mode==2){
            return;
        }
        var colorTemperature = $('#colorTemperature').html();
        if(colorTemperature <= 5){
            return ;
        }else{
            colorTemperature=parseInt(colorTemperature)-5;
        }
        $('#colorTemperature').html(colorTemperature);

    });
    //增加亮度
    $('#colorTemperatureAdd').click(function(){
        var mode = $('#mode').children('[data-status]:visible').attr('data-control');
        if(mode==2){
            return;
        }
        var colorTemperature = $('#colorTemperature').html();
        if(colorTemperature >= 100){
            return ;
        }else{
            colorTemperature=parseInt(colorTemperature)+5;
        }
        $('#colorTemperature').html(colorTemperature);
    });
})

//激活设备
function openingDevice(i) {
    var activation=$("#activation"+i).val();
    if (activation==0){
        _I=i;
        var $iosDialog1 = $('#iosDialog1');
        if ($iosDialog1.css('display') != 'none') return;
        $iosDialog1.fadeIn(100);
        setTimeout(function () {
            $iosDialog1.fadeOut(100);
        }, 200000);
        $("#containerBd").html("是否激活设备？");
    }
}

//关闭设备激活状态
function closeDevice(i) {
    var activation=$("#activation"+i).val();
    if (activation==1){
        _I=i;
        var $iosDialog1 = $('#iosDialog1');
        if ($iosDialog1.css('display') != 'none') return;
        $iosDialog1.fadeIn(100);
        setTimeout(function () {
            $iosDialog1.fadeOut(100);
        }, 200000);
        $("#containerBd").html("是否关闭激活状态？");
    }
}

function confirmBth() {
    var i=_I;
    var activation=$("#activation"+i).val();
    var devFirstType=$("#devFirstType"+i).val();
    var devSecondType=$("#devSecondType"+i).val();

    //激活设备
    if (activation==0){
        console.log("激活设备");
        $("#in"+i).attr("type","checkbox");
        $("#in"+i).attr("checked","checkbox");
        //类型图片
        if (devFirstType==1 || devFirstType==23){//灯
            if (devSecondType==1 ||  devSecondType==31 || devSecondType==36){
                $("#img"+i).attr("src","img/ic_dt_cw_light_icon.png");
            }
        }
        if (devFirstType==2){//插座
            if(devSecondType==25|| devSecondType==37){
                $("#img"+i).attr("src","img/ic_dt_plug_icon.png");
            }
        }
        if (devFirstType==4 && devSecondType==4){//空调
            $("#img"+i).attr("src","img/ic_dt_aircondition_icon.png");
        }
        if (devFirstType==6){//窗帘
            if(devSecondType==6|| devSecondType==38){
                $("#img"+i).attr("src","img/ic_dt_curtain_icon.png");
            }
        }
        $("#activation"+i).val("1");
        cc(i);
    }
    //关闭设备
    if (activation==1){
        console.log("关闭设备");
        $("#in"+i).attr("type","checked");
        //类型图片
        if (devFirstType==1 || devFirstType==23){//灯
            if (devSecondType==1 ||  devSecondType==31 || devSecondType==36){
                $("#img"+i).attr("src","img/ic_dt_cw_light_icon1.png");
            }
        }
        if (devFirstType==2){//插座
            if(devSecondType==25|| devSecondType==37){
                $("#img"+i).attr("src","img/ic_dt_plug_icon1.png");
            }
        }
        if (devFirstType==4 && devSecondType==4){//空调
            $("#img"+i).attr("src","img/ic_dt_aircondition_icon1.png");
        }
        if (devFirstType==6){//窗帘
            if(devSecondType==6|| devSecondType==38){
                $("#img"+i).attr("src","img/ic_dt_curtain_icon1.png");
            }
        }
        $("#activation"+i).val("0");
        $("#heaterState"+i).html("关");
    }
    $('#iosDialog1').attr("style","display: none");
}

function cancelBth() {
    var i=_I;
    var activation=$("#activation"+i).val();
    var devFirstType=$("#devFirstType"+i).val();
    var devSecondType=$("#devSecondType"+i).val();

    //取消激活设备
    if (activation==0){
        console.log("取消激活设备");
        $("#in"+i).attr("type","checked");
        //类型图片
        if (devFirstType==1 || devFirstType==23){//灯
            if (devSecondType==1 ||  devSecondType==31 || devSecondType==36) {
                $("#img" + i).attr("src", "img/ic_dt_cw_light_icon1.png");
            }
        }
        if (devFirstType==2){//插座
            if(devSecondType==25|| devSecondType==37){
                $("#img"+i).attr("src","img/ic_dt_plug_icon1.png");
            }
        }
        if (devFirstType==4 && devSecondType==4){//空调
            $("#img"+i).attr("src","img/ic_dt_aircondition_icon1.png");
        }
        if (devFirstType==6){//窗帘
            if(devSecondType==6|| devSecondType==38){
                $("#img"+i).attr("src","img/ic_dt_curtain_icon1.png");
            }
        }
        $("#activation"+i).val("0");
        $("#heaterState"+i).html("关");
    }
    //取消关闭激活设备
    if (activation==1){
        console.log("取消关闭激活设备");
        $("#in"+i).attr("type","checkbox");
        //类型图片
        if (devFirstType==1 || devFirstType==23){//灯
            if (devSecondType==1 ||  devSecondType==31 || devSecondType==36) {
                $("#img" + i).attr("src", "img/ic_dt_cw_light_icon.png");
            }
        }
        if (devFirstType==2){//插座
            if (devSecondType==25 || devSecondType==37){
                $("#img"+i).attr("src","img/ic_dt_plug_icon.png");
            }
        }
        if (devFirstType==4 && devSecondType==4){//空调
            $("#img"+i).attr("src","img/ic_dt_aircondition_icon.png");
        }
        if (devFirstType==6){//窗帘
            if(devSecondType==6|| devSecondType==38){
                $("#img"+i).attr("src","img/ic_dt_curtain_icon.png");
            }
        }
        $("#activation"+i).val("1");
    }
    $('#iosDialog1').attr("style","display: none");
}

//更改状态
function cc(i){
    if($('#in'+i).prop("checked")){
        $("#heaterState"+i).html("开");
    }else{
        $("#heaterState"+i).html("关");
    }
}
//设置冷暖灯光
function lightingAdjustment(i){
    _I=i;
    $('#brightness').html($("#brightness"+i).val());
    $('#colorTemperature').html($("#colorTemperature"+i).val());
    var devFirstType=$('#devFirstType'+i).val();
    var devSecondType=$('#devSecondType'+i).val();
    if (devFirstType==23 && devSecondType==31 || devFirstType==23 && devSecondType==36){
        if (devFirstType==23 && devSecondType==36){
            $("#colorTemperatureDiv").hide();
        }
        var $iosDialog0 = $('#iosDialog0');
        if ($iosDialog0.css('display') != 'none') return;
        $iosDialog0.fadeIn(100);
        setTimeout(function () {
            $iosDialog0.fadeOut(100);
        }, 200000);
    }
}
//取消设置灯光亮度色温
function lightingCancellation() {
    $('#iosDialog0').attr("style","display: none");
}
//确定设置灯光亮度色温
function lightingDetermination() {
    var i=_I;
    var brightness=$('#brightness').html();
    var colorTemperature= $('#colorTemperature').html();
    $("#brightness"+i).val(brightness);
    $("#colorTemperature"+i).val(colorTemperature);
    console.log(brightness)
    console.log(colorTemperature)
    $('#iosDialog0').attr("style","display: none");
}

//情景设置保存信息
function updateScenario(i){
    var giveParameter=$("#giveParameter").val();
    var givParameterObj=JSON.parse(giveParameter);
    var hsId=givParameterObj.hsId;
    var jsroPatternId=givParameterObj.spdId;
    var jsroWxgzhState=[];					//情景状态数组
    var jsroInstruction=[];					//情景指令数组
    for(var j=0;j<=i;j++){
        var devId=$('#devId'+j).val();
        var devBrandId=$('#devBrandId'+j).val();
        var devFirstType=$('#devFirstType'+j).val();
        var devSecondType=$('#devSecondType'+j).val();
        var state=$("#heaterState"+j).html();
        var statu=$("#status"+j).val();
        var sn=$('#devAuthId'+j).val();
        var devAuthSecret=$('#devAuthSecret'+j).val();
        var activation=$("#activation"+j).val();
        // var brightness=$("#brightness"+j).val();
        // var colorTemperature=$("#colorTemperature"+j).val();

        if(devBrandId==undefined || activation==0){
            continue;
        }
        var	states={				//情景状态
            sn:sn,
            state:state,
            brightness:$("#brightness"+j).val(),
            colorTemperature:$("#colorTemperature"+j).val()
        };
        var instruction={//情景指令
            devId:devId,
            sn:sn,
            status:"",
            mac:devAuthSecret,
            isNeedCache:false,
            resultCode:0,
            resultMsg:null,
            devBrandId:devBrandId,
            devFirstType:devFirstType,
            devSecondType:devSecondType
        };

        if(devBrandId==20){ //灯光
            if (devFirstType==1){
                if (devSecondType==1){
                    if(state=="开"){
                        instruction.status="8080";
                    }else{
                        instruction.status="8010";
                    }
                }
            }
        }


        if(devBrandId==20){ //插座
            if (devFirstType==2){
                if (devSecondType==25) {
                    if (state == "开") {
                        instruction.status="81";
                    } else {
                        instruction.status="80";
                    }
                }
                if (devSecondType==37) {
                    if (state == "开") {
                        instruction.status="8080";
                    } else {
                        instruction.status="8000";
                    }
                }
            }
        }

        if(devBrandId==20){//空调
            if (devFirstType==4){
                if (devSecondType==4) {
                    if(state=="开"){
                        instruction.status=statu;
                    }else{
                        instruction.status=statu;
                    }
                }
            }
        }

        if(devBrandId==20){ //窗帘
            if (devFirstType==6){
                if (devSecondType==6) {
                    if(state=="开"){
                        instruction.status="8080";
                    }else{
                        instruction.status="4040";
                    }
                }
                if (devSecondType==38) {
                    if(state=="开"){
                        instruction.status="000200018080";
                    }else{
                        instruction.status="000200014040";
                    }
                }
            }
        }
        if(devBrandId==20){ //2.4G冷暖灯
            if (devFirstType==23){
                if (devSecondType==31) {
                    var brightness=$("#brightness"+j).val();
                    var colorTemperature=$("#colorTemperature"+j).val();
                    brightness="0"+parseInt(brightness/6.6).toString(16);
                    if (parseInt(colorTemperature*1.27).toString(16)<16){
                        colorTemperature="0"+parseInt(colorTemperature*1.27).toString(16);
                    }else{
                        colorTemperature=parseInt(colorTemperature*1.27).toString(16);
                    }
                    if(state=="开"){
                        instruction.status="80"+brightness+colorTemperature+"07";
                    }else{
                        instruction.status="00"+brightness+colorTemperature+"07";
                    }
                }
                if (devSecondType==36) {
                    var brightness=$("#brightness"+j).val();
                    var colorTemperature=$("#colorTemperature"+j).val();
                    brightness="0"+parseInt(brightness/6.6).toString(16);
                    if(state=="开"){
                        instruction.status="80"+brightness+"0707";
                    }else{
                        instruction.status="00"+brightness+"0707";
                    }
                }
            }
        }
        //情景状态
        jsroWxgzhState.push(states);
        if(instruction!=""){
            //情景指令
            jsroInstruction.push(instruction);
        }
    }
    if (JSON.stringify(jsroInstruction)=="[]"&&JSON.stringify(jsroWxgzhState)=="[]"){
        alert("选择设备，再保存");
    }else {
        $.ajax({
            type: "post",
            url: "./updateScenario",
            data: {
                hsId:hsId,
                jsroInstruction:JSON.stringify(jsroInstruction),
                jsroWxgzhState:JSON.stringify(jsroWxgzhState),
                jsroPatternId:jsroPatternId
            },
            dataType: "json",
            success: function(result) {
                toast(result.msg);
            }
        })
    }
}
//保存成功后弹出已完成
function toast(msg){
    $("#msg").html(msg)
    var $toast = $('#toast');
    if ($toast.css('display') != 'none') return;
    $toast.fadeIn(100);
    setTimeout(function () {
        $toast.fadeOut(100);
    }, 2000);
}