/**
 * 初始化页面
 */
$(function () {
	 $.ajax({
		type : 'post',
		dataType : 'json',
		url:'../../pmsprofitdistribution/select1.action',
		data:{'agentNumber':$("#agentNumber").val(),'par':"B,A,W"},
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
        	
        	var no=data.length;
        	if(no==0){
        		no=3
        	}
		     for(var i=0;i<no;i++){
		     	srid="sr"+(i+1);
		     	trid="tr"+(i+1);
		     	prid="pr"+(i+1);
		     	/*caid="ca"+(i+1);*/
		     	feetype="feetype"+(i+1);
		     	$("#table").append("<tr>" +
					     			"<td width='200px' ><input type='text' readonly='readonly' id='"+srid+"' name='standardRate' onblur=checkSr('"+srid+"')></td>" +
					     			"<td width='100px'><input type='text' name='tariffRate' id='"+trid+"' onblur=checkTr('"+srid+"','"+trid+"')></td>" +
					     			"<td width='100px'><input type='text' name='profitRatio' id='"+prid+"' onblur=checkPr('"+prid+"')></td>" +
					     			/*"<td width='100px'><input type='text' id='"+caid+"' onblur=checkCa('"+srid+"','"+caid+"') name='cappenAmount'></td>" +*/
					     			"<td><input type='text' id='"+feetype+"' name='feeType'></td>" +
					     			
		     					"</tr>");
		     }
		    var inputValArray=$('#rateForm').serialize().split('&');
			for(var i=0;i<inputValArray.length;i++){
			     var inputName=inputValArray[i].split('=')[0];
			     var idname="";
			     if(i%4==0){//第一个
			    	 	idname="sr"+((i-i%4)/4+1);
			    	 }else if(i%4==1){
			    		 idname="tr"+((i-i%4)/4+1);
			         }else if(i%4==2){
			        	 idname="pr"+((i-i%4)/4+1);
			         }else if(i%4==3){
			        	 idname="feetype"+((i-i%4)/4+1);
			         }
			     var num=(i-i%4)/4;
			     var content=row_info(data[num],inputName);
			     //标准费率
			     if(idname.substr(0,2)=="sr"){
			    	 var nb=$("#"+idname).numberbox({    
						    min:0,    
						    precision:3,
						    required: true,
						    missingMessage:"请输入",
						});
			    	 nb.numberbox("setValue",content);
			    	 
				     //$("#"+idname).val(content);
				     
				     $("#"+idname).prop("readonly",false);
				     
			     }else if(idname.substr(0,2)=="tr"){//协定费率
			    	 var nb=$("#"+idname).numberbox({    
						    min:0,    
						    precision:3,
						    required: true,
						    missingMessage:"请输入",
						});
			    	 nb.numberbox("setValue",content);
			     }else if(idname.substr(0,7)=="feetype"){
			    	 if(data.length ==0){
			    		 if(idname=="feetype1"){
			    			 $("#"+idname).val("W");
			    			 $("#"+idname).attr("value","W");
			    			 $("#"+idname).prop("readonly",true);
			    		 }else if(idname =="feetype2"){
			    			 $("#"+idname).val("A");
			    			 $("#"+idname).prop("readonly",true);
			    		 }else if(idname =="feetype3"){
			    			 $("#"+idname).val("B");
			    			 $("#"+idname).prop("readonly",true);
			    		 }
			    	 }else{
			    		 $("#"+idname).val(content);
				    	 $("#"+idname).prop("readonly",true);
			    	 }
			    	 
			     }
			     
			     else{
			    	 var nb=$("#"+idname).numberbox({    
						    min:0,    
						    precision:2,
						    required: true,
						    missingMessage:"请输入",
						});
			    	 nb.numberbox("setValue",content);
			     }
			}
		}
	});
});
/**修改分润**/

function insertage(vrateform,userType) {
	/*if(!$("#"+vrateform).form('validate')){
			$.messager.alert("信息提示","请填写完整费率!","info");
			return;
		}*/
	/**
	 * 提交ajax请求
	 */
	var standardRate="";
	var tariffRate="";
	var profitRatio="";
	//var cappenAmount="";//扫码没有封顶费率
	var feeType="";
	
	var agentNumber=$("#agentNumber").val();
	var srs=$("input[name='standardRate']");
	var tfs=$("input[name='tariffRate']");
	var prs=$("input[name='profitRatio']");
	//var cas=$("input[name='cappenAmount']");
	
	var feeT=$("input[name='feeType']");
	for(var i=0;i<tfs.length;i++){
		//验证分润比例
		/*if(prs.eq(i).val() > 1 || prs.eq(i).val()!=""){
			$.messager.alert('信息提示','请输入分润比例，分润比例不能大于1，请重新输入','info');
			return false;
		}*/
		//协定费率不能大于标准费率
		if(tfs.eq(i).val()>srs.eq(i).val()){
			$.messager.alert('信息提示','您输入的协定扣率大于标准扣率，请重新输入','info');
			return false;
		}
		
		standardRate=standardRate+srs.eq(i).val()+",";
		tariffRate=tariffRate+tfs.eq(i).val()+",";
		profitRatio=profitRatio+prs.eq(i).val()+",";
		//cappenAmount=cappenAmount+cas.eq(i).val()+",";
		feeType=feeType+feeT.eq(i).val()+",";
}
	$.messager.progress({title:'提示',msg:'正在处理(处理时间可能有点长)，请稍候.......'});
	
	$.ajax({
			type: "POST", 
        	url:"../../pmsprofitdistribution/updateScanFee.action?agentNumber="+agentNumber+"&standardRate="+standardRate+"&tariffRate="+tariffRate+"&profitRatio="+profitRatio+"&feeType="+feeType+"&par="+userType,
        	success:function(message){
        		$.messager.progress('close');
        		var info=$.parseJSON(message);
        		if(info=="true"){
        			$("#add_msg").html("修改成功,请关闭本页面");
        		}else{
        			$("#add_msg").html("因网络异常，修改失败");
        		}
        	}
    });
 }

//验证协定
function checkTr(sr,tr){
	if($("#"+tr+"").attr("value")*1000>$("#"+sr+"").attr("value").split("-")[0]*1000){
		
		$("#"+tr+"").attr("value","");
		$.messager.alert('信息提示','您输入的协定扣率大于标准扣率，请重新输入','info'); 
	}
}
//验证分润
function checkPr(pr){
	if($("#"+pr+"").attr("value")>1){
		$("#"+pr+"").attr("value","");
		$.messager.alert('信息提示','您输入的分润比例大于1，请重新输入','info');
	}
}

//标准费率
function checkSr(sr){
	var standardRate=$("#"+sr+"").attr("value");
	var num4=/^[0-9.]+$/;
	if(!num4.test(standardRate)){
		$.messager.alert('信息提示','标准费率只能输入数字或点!','error');
		$("#"+sr+"").attr("value","");
		return false;
	}
}


//验证封顶金额
function checkCa(sr,ca){
	var sr=$("#"+sr).val();
	var srs=sr.split("-");
	if(srs[1]!=null||srs[1]!=""){
		if($("#"+ca).val()*10>srs[1]*10){
			$("#"+ca+"").prop('value','');
			$.messager.alert("信息提示","您输入的封顶金额大于额定金额","info");
		}
	}else{
		return true;
	}
	
}