//form表单查询的公用方法
function FormData(tableId, searchFormId) {
	$("#" + tableId + "").datagrid('clearSelections');
	var params = {};
	var inputValArray = $("#" + searchFormId + "").serialize();
	inputValArray = inputValArray.replace(/\+/g, " ");
	inputValArray = decodeURIComponent(inputValArray, true);

	inputValArray = inputValArray.split("&");
	for (var i = 0; i < inputValArray.length; i++) {
		var eachInput = inputValArray[i].split("=");
		eachInput[1] = eachInput[1].replace(/\s+/g, "");
		params[eachInput[0]] = eachInput[1];
	}
	RefreshPageNumber(tableId);
	$("#" + tableId + "").datagrid('reload', params);

}

// 重新查询的时候，刷新页码
function RefreshPageNumber(id) {

	// 获取dataGrid的列表对象属性
	var $datagrid = $("#" + id).datagrid("options");
	if ($datagrid != undefined) {
		$datagrid.pageNumber = 1;
	}

	// 获取dataGrid的分页对象
	var $getPager = $("#" + id).datagrid('getPager');
	var $pagination = $($getPager).pagination("options");
	if ($pagination != undefined) {
		$pagination.pageNumber = 1;
	}

}

// 打开tab
function addTab(title,url){
	if($("#centre").tabs("exists",title)){
		$("#centre").tabs("close",title);    
	}
   var content = '<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:99%;height:98%;margin:3px;"></iframe>';   
   $("#centre").tabs("add",{
	   title:title,
	   content:content,// 这里的content里放你想要显示的东西
       closable:true
   });
	    
}

// 在父级页面中打开Tab
function addTabInParent(title,url){
	if(parent.$("#centre").tabs("exists",title)){
		parent.$("#centre").tabs("close",title);
	}
	var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:99.5%;height:98.5%;margin:3px;"></iframe>';   
	    parent.$("#centre").tabs("add",{
	    title:title,
	    content:content,// 这里的content里放你想要显示的东西
	    closable:true
	    });   
}

//关闭打开的dialog窗体
function closeDialog(dialogid){
	$("#"+dialogid+"").dialog('close');
}

//删除数据的公用方法
function delete_info(tableId,actionName,pkName){
	var rows = $("#"+tableId+"").datagrid("getSelections");    // 获取所有选中的行
	if(rows.length==0){
		$.messager.alert('信息提示','请选择删除信息!!!','error'); 
	}else if(rows[0].roleName=="超级管理员"){
		$.messager.alert('信息提示','超级管理员不能删除信息!!!','error');
	}else{
		var idArray="";
		for (var i = 0; i < rows.length; i++) {
		    var id = row_info(rows[i],pkName);
		    idArray+=id+"-";
		}
		idArray=idArray.substr(0,idArray.length-1);
		$.messager.confirm('删除提示', "确定删除选中的信息吗?", function(r){
				if (r){
					$.post("../../"+actionName+"/detele.action?idArray="+idArray,function(data){
						if(data==1){
							$.messager.alert('信息提示','数据删除成功','info'); 
							deleteRow(rows,tableId);
						}
						else if(data==2){
							$.messager.alert('信息提示','数据删除失败，营业部下含有已绑定的POS','info'); 
						}
						else{
							$.messager.alert('信息提示','数据删除失败','error'); 
						}
					},'json')
				}
		 });
	}
}


//根据行id删除数据表中的行
function deleteRow(rows,tableId){
	for (var z = 0; z < rows.length; z++) {
	     var copyRows = [];
	        for ( var j= 0; j < rows.length; j++) {
	            copyRows.push(rows[j]);
	        }
	     for(var i =0;i<copyRows.length;i++){    
	            var index = $("#"+tableId+"").datagrid('getRowIndex',copyRows[i]);
	            $("#"+tableId+"").datagrid('deleteRow',index); 
	        }
	 }
}


//删除公用方法(tree树状图)
function tree_remove(){
	var actionName;
	var divName;
	for(var i = 0; i < arguments.length; i++){  
		if(i==0){
			actionName=arguments[i];
		}else{
			divName = arguments[i];
		}
     }  
	
    var nodes = $("#"+divName+"").tree('getChecked');
	var idArray = '';
	for(var i=0; i<nodes.length; i++){
		if (idArray != '') idArray += '-';
		idArray += nodes[i].id;
	}
	if(idArray==''){
		$.messager.alert('信息提示','请选择删除菜单的信息!!!','error'); 
	}else{
		$.messager.confirm('删除提示', "你确定删除id是:&nbsp;&nbsp;"+idArray+"&nbsp;&nbsp;&nbsp;的数据信息吗?", function(r){
			if (r){
				$.post("../../"+actionName+"/detele.action?idArray="+idArray,function(data){
					if(data==1){
						$.messager.alert('信息提示','数据删除成功','info'); 
				        $("#"+divName+"").tree('reload');
					}else{
						$.messager.alert('信息提示','数据删除失败','error'); 
					}
				},'json')
			}
	 });
	}
}





//获取数据表中每行的信息(key和value的值)
function row_info(row,pkName){
	 for(k in row){
	    	if(pkName==k){
	    		return row[k];
	    	}
	    }
}

//打开添加界面（window界面）
function saveOpen(divName,iframeName){
    $("#"+divName+"").dialog('open');
    $("#"+iframeName+"")[0].src='save.jsp';
 }


//添加的公用方法
function add_save(addFormName,actionName){
	
	 $("#"+addFormName+"").form('submit', {  
    	   url : "../../"+actionName+"/save.action",  
     	   dataType : 'text',  
     	   data:$("#"+addFormName+"").serialize(),
     	  beforeSend:function (XMLHttpRequest ){
     		 $("#add_msg").html("保存数据中，请稍后...");
			},
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
	        success : function(data) {  
	            var newData = $.parseJSON(data);  
	            if(newData==1){
		             $("#"+addFormName+" input").val('');  
		             $("#add_msg").html("添加成功,可以继续添加");
	            } else{
	            	 $("#add_msg").html("添加失败,请查看添加信息是否正确!!!");
	            }
	        }  
   }); 
}

//添加菜单界面的重置函数
function doReset(formName){
	$("#"+formName+" input").val('');  
	$("#add_msg").html("&nbsp;");
}

function updateOpen(tableName,updateDivName,iframeName,pkName){
    var rows=$("#"+tableName+"").datagrid("getSelections");
    if(rows.length<1){
		$.messager.alert('信息提示','请选择修改信息!!!','error'); 
	}else if(rows.length>1){
		$.messager.alert('信息提示','只能选择一条修改信息','error'); 
	}else{
		  var row=rows[0];//获取类表中的选中的列
	      $("#"+updateDivName+"").dialog('open');
	      if(iframeName=="PospProfitInfoUpdateIframe"){
	    	  $("#"+iframeName+"")[0].src="updateProfit.jsp?sellerId="+ row_info(row,"sellerNo")+"";
	      }else if(iframeName=="PmsProfitInfoUPdateIframe"){
	    	  $("#"+iframeName+"")[0].src="updateProfit.jsp?agentNumber="+ row_info(row,"agentNumber")+"";
	      }else{
	    	  $("#"+iframeName+"")[0].src="update.jsp?pkName="+ row_info(row,pkName)+"";
	      }
	}
}

function updateOpenfind(tableName,updateDivName,iframeName,pkName){
    var rows=$("#"+tableName+"").datagrid("getSelections");
    if(rows.length<1){
		$.messager.alert('信息提示','请选择查看信息!!!','error'); 
	}else if(rows.length>1){
		$.messager.alert('信息提示','只能选择一条信息查看','error'); 
	}else{
		  var row=rows[0];//获取类表中的选中的列
	      $("#"+updateDivName+"").dialog('open');
	      $("#"+iframeName+"")[0].src="update.jsp?pkName="+ row_info(row,pkName)+"";
	}
}
 
function updateOpen1(tableName,updateDivName,iframeName,pkName){
    var rows=$("#"+tableName+"").datagrid("getSelections");
    if(rows.length<1){
		$.messager.alert('信息提示','请选择查询信息!!!','error'); 
	}else if(rows.length>1){
		$.messager.alert('信息提示','只能选择一条查询信息','error'); 
	}else{
		  var row=rows[0];//获取类表中的选中的列
	      $("#"+updateDivName+"").dialog('open');
	      $("#"+iframeName+"")[0].src="select.jsp?pkName="+ row_info(row,pkName)+"";
	}
}
 
//编辑用户菜单权限
function editOpen(tableName,editDivName,iframeName,pkName){
	
    var rows=$("#"+tableName+"").datagrid("getSelections");
    var row=rows[0].roleId;
    if(rows.length<1){
		$.messager.alert('信息提示','请选择编辑信息!!!','error'); 
	}else if(rows.length>1){
		$.messager.alert('信息提示','只能选择一条编辑信息!!!','error'); 
	}else if(row==1){
		$.messager.alert('信息提示','管理员不能编辑权限!!!','error'); 
	}else{
		  var row=rows[0];//获取类表中的选中的列
	      $("#"+editDivName+"").dialog('open');
	      $("#"+iframeName+"")[0].src="edit.jsp?pkName="+ row_info(row,pkName)+"";
	} 
}
//编辑角色菜单权限
function editOpen1(tableName,editDivName,iframeName,pkName){
	
    var rows=$("#"+tableName+"").datagrid("getSelections");
    var row=rows[0].roleId;
    if(rows.length<1){
		$.messager.alert('信息提示','请选择编辑信息!!!','error'); 
	}else if(rows.length>1){
		$.messager.alert('信息提示','只能选择一条编辑信息!!!','error'); 
	}else if(row==1){
		$.messager.alert('信息提示','管理员不能编辑权限!!!','error'); 
	}else if(row==21){
		$.messager.alert('信息提示','普通员工不能编辑权限!!!','error');
	}else{
		  var row=rows[0];//获取类表中的选中的列
	      $("#"+editDivName+"").dialog('open');
	      $("#"+iframeName+"")[0].src="edit.jsp?pkName="+ row_info(row,pkName)+"";
	} 
}
//保存密码修改的信息
function updatePwd_save(updateFormName,actionName){
	$.ajax({
		type : 'post',
		dataType : 'json',
		url:""+actionName+"/updatePwd2.action",
		data:$("#"+updateFormName+"").serialize(),
		error : function() {// 请求失败处理函数
			alert('请求失败...');
		},
		success : function(data) { // 请求成功后处理函数。
			if (data ==1) {
				//重置所有文本框的值
				$("#update_msg").html("修改成功,可以继续操作");
			}else{
				$("#update_msg").html("修改失败,请查看修改的信息是否正确!!!");
			}
		}
	});
}

//保存修改的信息
function update_save(updateFormName,actionName){
	$.ajax({
		type : 'post',
		dataType : 'json',
		url:"../../"+actionName+"/update.action",
		data:$("#"+updateFormName+"").serialize(),
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		beforeSend:function (XMLHttpRequest ){
    		 $("#update_msg").html("处理中，请稍后...");
			},
		success : function(data) { // 请求成功后处理函数。
			if (data ==1) {
				//重置所有文本框的值
				$("#update_msg").html("修改成功,可以继续操作");
				$.messager.alert('信息提示','修改成功','info');
			}else{
				$("#update_msg").html("修改失败,请查看修改的信息是否正确!!!");
			}
		}
	});
}
function getDate(){
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	var sys=vYear+"/"+(vMon<10 ? "0" + vMon : vMon)+"/"+(vDay<10 ? "0"+ vDay : vDay);	
	return sys;
}

function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}

function formatChannelno(val,row){
	val = trimStr(val);
	if(val=='0000'||val=='00'){return '成功';}
	var original=val;
	if(val=="00"){return "<span style='color: red;'>("+original+")成功</span>"}
	if(val=="01"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="02"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="03"){return "<span style='color: red;'>("+original+")商户未登记</span>"}
	if(val=="04"){return "<span style='color: red;'>("+original+")没收卡,请联系收单行</span>"}
	if(val=="05"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="06"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="07"){return "<span style='color: red;'>("+original+")没收卡,请联系收单行</span>"}
	if(val=="09"){return "<span style='color: red;'>("+original+")终端未登记</span>"}
	if(val=="09"){return "<span style='color: red;'>("+original+")终端未登记,请联系收单行或银联</span>"}
	if(val=="12"){return "<span style='color: red;'>("+original+")交易失败,请重试</span>"}
	if(val=="13"){return "<span style='color: red;'>("+original+")交易金额超限,请重试</span>"}
	if(val=="14"){return "<span style='color: red;'>("+original+")无效卡号,请联系发卡行</span>"}
	if(val=="15"){return "<span style='color: red;'>("+original+")此卡不能受理</span>"}
	if(val=="19"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="20"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="21"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="22"){return "<span style='color: red;'>("+original+")操作有误,请重试</span>"}
	if(val=="23"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="25"){return "<span style='color: red;'>("+original+")无源交易，请联系发卡行</span>"}
	if(val=="31"){return "<span style='color: red;'>("+original+")此卡不能受理</span>"}
	if(val=="33"){return "<span style='color: red;'>("+original+")过期卡,请联系发卡行</span>"}
	if(val=="34"){return "<span style='color: red;'>("+original+")没收卡,请联系收单行</span>"}
	if(val=="35"){return "<span style='color: red;'>("+original+")没收卡,请联系收单行</span>"}
	if(val=="36"){return "<span style='color: red;'>("+original+")此卡有误,请换卡重试</span>"}
	if(val=="37"){return "<span style='color: red;'>("+original+")没收卡,请联系收单行</span>"}
	if(val=="38"){return "<span style='color: red;'>("+original+")密码错误次数超限</span>"}
	if(val=="39"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="40"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="41"){return "<span style='color: red;'>("+original+")没收卡,请联系收单行</span>"}
	if(val=="42"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡方</span>"}
	if(val=="43"){return "<span style='color: red;'>("+original+")没收卡,请联系收单行</span>"}
	if(val=="44"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="50"){return "<span style='color: red;'>("+original+")校验错,请重新签到</span>"}
	if(val=="51"){return "<span style='color: red;'>("+original+")余额不足,请查询</span>"}
	if(val=="52"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="53"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="54"){return "<span style='color: red;'>("+original+")过期卡,请联系发卡行</span>"}
	if(val=="55"){return "<span style='color: red;'>("+original+")密码错,请重试</span>"}
	if(val=="56"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="57"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="58"){return "<span style='color: red;'>("+original+")终端无效,请联系收单行或银联</span>"}
	if(val=="59"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="60"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="61"){return "<span style='color: red;'>("+original+")金额太大</span>"}
	if(val=="62"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="63"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="64"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="65"){return "<span style='color: red;'>("+original+")超出取款次数限制</span>"}
	if(val=="66"){return "<span style='color: red;'>("+original+")交易失败,请联系收单行或银联</span>"}
	if(val=="67"){return "<span style='color: red;'>("+original+")没收卡</span>"}
	if(val=="68"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="69"){return "<span style='color: red;'>("+original+")请向网络中心签到</span>"}
	if(val=="70"){return "<span style='color: red;'>("+original+")交易失败,请联系收单方</span>"}
	if(val=="71"){return "<span style='color: red;'>("+original+")交易失败,请联系收单方</span>"}
	if(val=="72"){return "<span style='color: red;'>("+original+")校验错,请重新签到</span>"}
	if(val=="73"){return "<span style='color: red;'>("+original+")校验错,请重新签到</span>"}
	if(val=="74"){return "<span style='color: red;'>("+original+")校验错,请重新签到</span>"}
	if(val=="75"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="76"){return "<span style='color: red;'>("+original+")交易失败,请联系收单方</span>"}
	if(val=="77"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="78"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="79"){return "<span style='color: red;'>("+original+")POS终端重传脱机数据</span>"}
	if(val=="79"){return "<span style='color: red;'>("+original+")POS终端重传脱机数据</span>"}
	if(val=="81"){return "<span style='color: red;'>("+original+")请向网络中心签到</span>"}
	if(val=="82"){return "<span style='color: red;'>("+original+")终端未登记,请联系收单行或银联</span>"}
	if(val=="83"){return "<span style='color: red;'>("+original+")交易失败,请稍后重试</span>"}
	if(val=="84"){return "<span style='color: red;'>("+original+")交易失败,请联系发卡行</span>"}
	if(val=="85"){return "<span style='color: red;'>("+original+")与原交易的金额不一致</span>"}
	if(val=="86"){return "<span style='color: red;'>("+original+")当批次金额已超限,请结算后再试</span>"}
	if(val=="87"){return "<span style='color: red;'>("+original+")交易失败,请稍后重试</span>"}
	if(val=="88"){return "<span style='color: red;'>("+original+")交易失败,请联系收单方</span>"}
	if(val=="89"){return "<span style='color: red;'>("+original+")请向网络中心签到</span>"}
	if(val=="90"){return "<span style='color: red;'>("+original+")程序需更新</span>"}
	if(val=="91"){return "<span style='color: red;'>("+original+")参数需更新</span>"}
	if(val=="93"){return "<span style='color: red;'>("+original+")请向网络中心签到</span>"}
	if(val=="94"){return "<span style='color: red;'>("+original+")交易失败,请联系收单方</span>"}
	if(val=="95"){return "<span style='color: red;'>("+original+")交易失败</span>"}
	if(val=="96"){return "<span style='color: red;'>("+original+")交易失败,请联系收单方</span>"}
	if(val=="97"){return "<span style='color: red;'>("+original+")交易失败,请联系收单行或银联</span>"}
	if(val=="98"){return "<span style='color: red;'>("+original+")交易超时,请联系收单方</span>"}
	if(val=="99"){return "<span style='color: red;'>("+original+")交易失败,请联系收单方</span>"}
	if(val=="A0"){return "<span style='color: red;'>("+original+")MAC校验错误</span>"}
	if(val=="EE"){return "<span style='color: red;'>("+original+")未支付，订单取消</span>"}
	else
		return "<span style='color: red;'>("+original+")其他错误</span>";
}


//生成Excel表格
function outputExcel(tableId,searchFormId,actionName, appendInfo){
	var params="?";
	if(appendInfo!=undefined)
	{
		params=  params + appendInfo+'&';
	}
	   var inputValArray=$("#"+searchFormId+"").serialize();
	    inputValArray=inputValArray.replace(/\+/g," ");   
	    inputValArray=decodeURIComponent(inputValArray,true);
	    inputValArray=inputValArray.split("&");
		for(var i=0;i<inputValArray.length;i++){
		     var eachInput=inputValArray[i].split("=");
		     eachInput[1] = eachInput[1].replace(/\s+/g,"");
		     params=params+eachInput[0]+"="+eachInput[1]+"&"
		}
		var str=":";
		$("th[data-options^='field:']").each(function(index,domEle){
			var d=$(domEle).attr("data-options");
			str=str+","+d.substr(7,d.indexOf(',')-8)+":"+$(domEle).html()+"";
 		});
 		params=params+"namelist="+str;
	  	window.location="../../"+actionName+"/excelDoc.action"+params;
}
//根据表格id和列名称 获取被选中的值 返回数组
function getSelectItems(tableId, filedName)
{
	//所有选中的行
	var rows = $("#"+tableId).datagrid("getSelections");
	var result = new Array();
	
	for(var i=0;i<rows.length;i++)
	{
		result.push( row_info(rows[i],filedName).trim());
	}
	return result;
	
}
/**
 * 验证传入的字符串s格式是否为正整数或0或小数(小数位数小于等于2)
 * @param s
 * @returns {Boolean}
 */
function isTrueMoney(s)
{
	var re = /^[0-9]*(\.\d{0,2})?$/;
     if (!re.test(s))
    {
        return false;
     }
     return true;
} 

//金额必须为正整数或小数 小数位数最多两位
function checkIsTrueMoney(bal){
	if(!isTrueMoney(bal))
	{
		$.messager.alert('错误提示','金额:'+bal+'格式错误 只能为整数或两位以内小数');
		return false;
	}
	return true;
}




/*******************************************************************************************************/

//								自定义表单校验

/*******************************************************************************************************/
/**
 * 两位小内的金额
 */
$(document).ready(function(){  
    //自定义validatebox的验证方法  
       $.extend($.fn.validatebox.defaults.rules, {    
        money: {    
            validator: function(value){    
                   //alert('校验:'+value);
                  return isTrueMoney(value);  
            },  
           message: '请输入正确金额，最多保留两位小数'    
        }
    });    
         
});   
/**
 * 正整数
 */
$(document).ready(function(){  
    //自定义validatebox的验证方法  
       $.extend($.fn.validatebox.defaults.rules, {    
    	  positiveInteger: {    
            validator: function(value){    
                   //alert('校验:'+value);
                  return checkPositiveInteger(value);  
            },  
           message: '请输入正整数'    
        }
    });    
         
});  
/**
 * 验证是否为正整数
 */
function checkPositiveInteger(s)
{
	var re = /^[1-9]*[0-9]?$/;
    if (!re.test(s))
   {
       return false;
    }
    return true;
	
}


//在链接中获取参数值
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }


//浮点数加法运算  
function FloatAdd(arg1,arg2){  
  var r1,r2,m;  
  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
  m=Math.pow(10,Math.max(r1,r2))  
  return (arg1*m+arg2*m)/m  
 }  
 
//浮点数减法运算  
function FloatSub(arg1,arg2){  
	var r1,r2,m,n;  
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
	m=Math.pow(10,Math.max(r1,r2));  
	//动态控制精度长度  
	n=(r1>=r2)?r1:r2;  
	return ((arg1*m-arg2*m)/m).toFixed(n);  
}  
  
//浮点数乘法运算  
function FloatMul(arg1,arg2)   
{   
 var m=0,s1=arg1.toString(),s2=arg2.toString();   
 try{m+=s1.split(".")[1].length}catch(e){}   
 try{m+=s2.split(".")[1].length}catch(e){}   
 return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
 }   
 
 
//浮点数除法运算  
function FloatDiv(arg1,arg2){   
	var t1=0,t2=0,r1,r2;   
	try{t1=arg1.toString().split(".")[1].length}catch(e){}   
	try{t2=arg2.toString().split(".")[1].length}catch(e){}   
	with(Math){   
	r1=Number(arg1.toString().replace(".",""))   
	r2=Number(arg2.toString().replace(".",""))   
	return (r1/r2)*pow(10,t2-t1);   
}   
}


$(function(){
	
})


function powerCheck()
{
	$.ajax({
		type : 'post',
		dataType : 'json',
		url:"../../tsysmenu/powerCheck.action",
		data:{},
		error : function() {// 请求失败处理函数
			//alert('请求失败...');
		},
		success : function(data) { // 请求成功后处理函数。
			if(data.result==1)
			{
				alert("请登陆");
				parent.window.location = "../../login/login.action";
			}	
			else if(data.result!=2)
			{
				alert("无权限");
				parent.window.location = "../../login/login.action";
			}
		}
	});
	
	
	
	//清结算状态
	function formatSettStatus(settlestatus,nettingStatus){
		settlestatus = settlestatus.trim();
		if(settlestatus=='0'){return '未清'}
		else if(settlestatus=='1'){return '已入账'}
		else if(settlestatus=='2'){return '已清'}
		else if(settlestatus=='3'){return '<font color="blue">已结</font>'}
		else if(settlestatus=='4'){return '<font color="blue">他清</font>'}
		else if(settlestatus=='5'){return '<font color="blue">T+0已结</font>'}
		else {return 		settlestatus}
		
	}
	
	
}
//为date 添加format方法
Date.prototype.format = function (fmt) { 
  var o = {
      "M+": this.getMonth() + 1, //月份 
      "d+": this.getDate(), //日 
      "h+": this.getHours(), //小时 
      "m+": this.getMinutes(), //分 
      "s+": this.getSeconds(), //秒 
      "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
      "S": this.getMilliseconds() //毫秒 
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

//格式化日期格式
function formatsenddate(val, row){
	if(val != null){
		var date = new Date(parseInt(val.time));
		return date.format("yyyy-MM-dd hh:mm:ss");
	}
	return "";
}

function standardDate (val,row,index){
	if(val !=""){
		if(val.length ==21){
			return val.substring(0,19);
		}else{
			return val;
		}
	}
	return val;
}


function queryComboTree(q, comboid) {
    var datalist = [];//用平面的combobox来展示过滤的结果
    var childrenlist = [];
    var combotreeid = "#" + comboid;
    var roots = $(combotreeid).combotree('tree').tree('getRoots');//得到根节点数组
    var children;
    var entertext = $(combotreeid).combotree('getText');
    
    $(combotreeid).combotree('setText', q);
    if (entertext == null || entertext == "") {//如果文本框的值为空，或者将文本框的值删除了，重新reload数据
        $(combotreeid).combotree('reload');
        $(combotreeid).combotree('clear');
        $(combotreeid).combotree('setText', q);
        return;
    }
    //循环数组，找到与输入值相似的，加到前面定义的数组中，
    for (i = 0; i < roots.length; i++) {
        if (q == roots[i].text) {
        	
	        $(combotreeid).combotree('tree')
	            .tree('select', roots[i].target);
	            return;
        } else {
       		 if (roots[i].text.indexOf(q) >= 0 && roots[i].text != "") {
	             var org = {
	                "id" : roots[i].id,
	            	"text" : roots[i].text
	             };
            	 datalist.push(org);
       		 }
        }
         //找子节点（递归）
        childrensTree(combotreeid, roots[i].target, datalist, q);
    }
    //如果找到相似的结果，则加载过滤的结果
    if (datalist.length > 0) {
        $(combotreeid).combotree('loadData', datalist);
        $(combotreeid).combotree('setText', q);
        datalist = [];//这里重新赋值是避免再次过滤时，会有重复的记录
        return;
    } else {
            $(combotreeid).combotree('reload');
            $(combotreeid).combotree('clear');
            $(combotreeid).combotree('setText', q);
        return;
    }
}

    function childrensTree(combotreeid, rootstarget, datalist, q) {
        children = $(combotreeid).combotree('tree').tree('getChildren', rootstarget);
        
        console.log(children);
        for (j = 0; j < children.length; j++) {
            if (q == children[j].text) {
                $(combotreeid).combotree('tree').tree('select',
                        children[j].target);
                return;
            } else {
                if (children[j].text.indexOf(q) >= 0 && children[j].text != "") {
                    var org = {
                        "id" : children[j].id,
                        "text" : children[j].text
                    };
                    datalist.push(org);
                }
            }
            //childrensTree(combotreeid,children[j].target,datalist,q);
        }
    }
    
    
    function strAmt(realamount){
		if(realamount=='0'||realamount==''){return '<h1 style=color:red;>￥0</h1>';}
		if(realamount.substring(0,1)=='.'){return '<h1 style=color:red;>￥0'+realamount+'</h1>';	}
		else{return '<h1 style=color:red;>￥'+realamount+'</h1>';}
	}
    
    function Trim(str)
    { 
        return str.replace(/(^\s*)|(\s*$)/g, ""); 
	}
    function strRed(str){
		return '<h1 style=color:red;>'+str+'</h1>';
	}
    function strBlue(str){
		return '<h1 style=color:blue;>'+str+'</h1>';
	}
    
//    判断是否是数字，字母
    function checknum(value) {
        var Regx = /^[A-Za-z0-9]*$/;
        if (Regx.test(value)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    function myformatter(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
	}
	function myparser(s) {
		if (!s)
			return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
