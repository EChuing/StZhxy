//智能控制跳转
function intelligentControl(){
    console.log("智能控制跳转")
    var hsId=$("#hsId").val();
    if(hsId=="" ||hsId==null || hsId=="undefined"){
       alert("请重新选择房间")
    }else{
         location.href="commonScenarios?hsId="+hsId;
    }
  //  window.location.href='commonScenarios.html';
}
//教室列表跳转
function commonAreaList() {
    console.log("教室列表跳转")
    var bottomNavigation="1";
    var hsId=$("#hsId").val();
    location.href="commonAreaList?bottomNavigation="+bottomNavigation+"&hsId="+hsId;
}
//常用情景跳转
function commonScenarios() {
    console.log("常用情景跳转")
    var hsId=$("#hsId").val();
    console.log(hsId)
    if(hsId=="" ||hsId==null || hsId=="undefined"){
        alert("请重新选择房间")
    }else{
       location.href="deviceIndex?hsId="+hsId;
    }
 //   window.location.href='deviceIndex.html';
}
//模式设置跳转
function modeSettings() {
    console.log("模式设置跳转")
     var hsId=$("#hsId").val();
    if(hsId=="" ||hsId==null || hsId=="undefined"){
        alert("请重新选择房间")
    }else {
        location.href = "modeSettings?hsId=" + hsId;
    }
  //  window.location.href='modeSettings.html';
}
//情景模式跳转
function situatinalPatterns() {
    console.log("情景模式跳转")
    var hsId=$("#hsId").val();
    if(hsId=="" ||hsId==null || hsId=="undefined"){
        alert("请重新选择房间")
    }else {
        location.href = "situatinalPatterns?hsId=" + hsId;
        // window.location.href='situatinalPatterns.html';
    }
}