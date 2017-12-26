function formatMn(value,row,index){
	var url='<a href="#" onclick=selOpen("PmsMerchantInfoTab","PmsDepartmentInfoSelDiv","PmsDepartmentInfoselIframe",'+row.mercid+')>'+value+'</a>'
	return url;
}
function formatXp(value,row,index){
	var url='<a href="#" onclick=xuOpen("PmsMerchantInfoTab","PospRouteInfoUpdateDiv","PospRouteInfoUpdateIframe",'+row.id+')>'+value+'</a>'
	return url;
}
function formatPn(value,row,index){
	var url='<a href="#" onclick=savePosOpen("PmsMerchantInfoTab","PmsPosDiv","PmsposIframe",'+row.id+')>'+value+'</a>';
	return url;
}
function formatBn(value,row,index){
	var url='<a href="#" onclick=businessOpen("PmsMerchantInfoTab","PmsBusinessInfoDiv","PmsBusinessInfoIframe",'+row.id+')>'+value+'</a>';
	return url;
}
function formatOm(value,row,index){
	var url='<a href="#" onclick=businessOpen("PmsMerchantOptTab","PmsMerchantInfoUPdateDiv","PmsMerchantInfoUPdateIframe",'+row.merId+')>'+value+'</a>';
	return url;
}


//function findXp(value,row,index){
//	var ownerId = row_info(row,'id');
//	$.post("../../posprouteinfo/selectXuPos.action?ownerId="+ownerId, null, function(data){
//		return data;
//	},'json');
//}