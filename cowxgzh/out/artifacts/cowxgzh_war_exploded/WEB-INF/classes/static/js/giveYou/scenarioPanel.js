function setting(type) {
    var deviceInformation=$("#deviceInformation").val();
    var hsId=JSON.parse(deviceInformation).hsId;
    var id =JSON.parse(deviceInformation).id;
    var zz = {};
    zz.id = id;
    zz.hsId=hsId;
    zz.type=type;
    var giveParameter = JSON.stringify(zz);
   window.location.href = 'givSettingPanelMode?giveParameter='+giveParameter;
}