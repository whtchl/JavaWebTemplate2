/**
 * 初始化页面
 */
$(function () {
	 $.ajax({
		type : 'post',
		dataType : 'json',
		url:'../../pmsprofitdistribution/select1.action',
		data:{'agentNumber':$("#agentNumber").val(),'feeType':'P'},
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
        	
        	var no=data.length;
		     for(var i=0;i<no;i++){
		     	srid="sr"+(i+1);
		     	trid="tr"+(i+1);
		     	prid="pr"+(i+1);
		     	caid="ca"+(i+1);
		     	$("#table").append("<tr>" +
					     			"<td width='200px' ><input type='text' id='"+srid+"' readonly='readonly' name='standardRate' ></td>" +
					     			"<td width='100px'><input type='text' name='tariffRate' id='"+trid+"' onblur=checkTr('"+srid+"','"+trid+"')></td>" +
					     			"<td width='100px'><input type='text' name='profitRatio' id='"+prid+"' onblur=checkPr('"+prid+"')></td>" +
					     			"<td width='100px'><input type='text' id='"+caid+"' onblur=checkCa('"+srid+"','"+caid+"') name='cappenAmount'></td>" +
		     					"</tr>");
		     }
		    var inputValArray=$('#rateForm').serialize().split('&');
			for(var i=0;i<inputValArray.length;i++){
			     var inputName=inputValArray[i].split('=')[0];
			     var idname="";
			     if(i%4==0){idname="sr"+((i-i%4)/4+1);}else if(i%4==1){idname="tr"+((i-i%4)/4+1);}else if(i%4==2){idname="pr"+((i-i%4)/4+1);}else if(i%4==3){idname="ca"+((i-i%4)/4+1);}
			     var num=(i-i%4)/4;
			     var content=row_info(data[num],inputName);
			     //封顶金额
			     if(idname.substr(0,2)=="ca"){
			    	 var nb=$("#"+idname).numberbox({    
						    min:0,    
						    precision:1,
						    required: true,
						    missingMessage:"请输入",
						});
			    	 nb.numberbox("setValue",content);
			     }else if(idname.substr(0,2)=="sr"){//标准费率
				     $("#"+idname).val(content);
				     if(content.indexOf("-")!=-1){
				    	 idname="ca"+(idname.substr(2,idname.length));
				    	 $("#"+idname).prop("readonly",false);
			    	 }else{
			    		 idname="ca"+(idname.substr(2,idname.length));
				    	 $("#"+idname).prop("readonly",true);
			    	 }
			     }else if(idname.substr(0,2)=="tr"){//协定费率
			    	 var nb=$("#"+idname).numberbox({    
						    min:0,    
						    precision:3,
						    required: true,
						    missingMessage:"请输入",
						});
			    	 nb.numberbox("setValue",content);
			     }else{
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
	if(!$("#"+vrateform).form('validate')){
			$.messager.alert("信息提示","请填写完整费率!","info");
			return;
		}
	$.messager.progress({title:'提示',msg:'正在处理(处理时间可能有点长)，请稍候.......'});
	/**
	 * 提交ajax请求
	 */
	var standardRate="";
	var tariffRate="";
	var profitRatio="";
	var cappenAmount="";
	var agentNumber=$("#agentNumber").val();
	var srs=$("input[name='standardRate']");
	var tfs=$("input[name='tariffRate']");
	var prs=$("input[name='profitRatio']");
	var cas=$("input[name='cappenAmount']");
	for(var i=0;i<tfs.length;i++){
		standardRate=standardRate+srs.eq(i).val()+",";
		tariffRate=tariffRate+tfs.eq(i).val()+",";
		profitRatio=profitRatio+prs.eq(i).val()+",";
		cappenAmount=cappenAmount+cas.eq(i).val()+",";
}
	$.ajax({
			type: "POST", 
        	url:"../../pmsprofitdistribution/update1.action?par="+userType+"&agentNumber="+agentNumber+"&standardRate="+standardRate+"&tariffRate="+tariffRate+"&profitRatio="+profitRatio+"&cappenAmount="+cappenAmount,
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