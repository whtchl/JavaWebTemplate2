/**添加代理商及其对应的分润**/
function insert1(agentform,vrateform) {
	/**
	 * 验证代理商表数据
	 */
	/*
	if($('#transactionSystemNumber').val()==""){
		$.messager.alert("信息提示","请填写支付系统行号","info");
		return;
	}
	if($('#corporationPhone').val()==""){
		$.messager.alert("信息提示","请填写法人手机号码","info");
		return;
	}
	if($('#corporationIdenNumber').val()==""){
		$.messager.alert("信息提示","请填写法人身份证号码","info");
		return;
	} */
	
	if(!$("#"+agentform+"").form('validate')){
		$.messager.alert("信息提示","请补全人员信息","info");
		return;
	}else{
		var tfs=$("input[name='tariffRate']");
		var flag=false;
		for(var i=0;i<tfs.length;i++){
			if($(tfs[i]).attr("value")==""){
				flag=true;
			}
		}
		if(flag){
			$.messager.alert("信息提示","请填写完整费率","info");
			return;
		}
	}
	
	/**
	 * 验证分润表数据
	 */
	if(!$("#"+vrateform).form('validate')){
			$.messager.alert("信息提示","请填写完整费率!","info");
			return;
		}
	$("#add_msg").html("保存数据中，请稍后...");
	/**
	 * 提交ajax请求
	 */
	 
	 $("#"+agentform+"").form('submit', {  
  	   url : "../../pmsagentinfo/save.action",  
   	   dataType : 'text',  
   	   data:$("#"+agentform+"").serialize(),
	        success : function(data) { 
	            var newData = $.parseJSON(data);  
	            if(newData==1){
		             $("#add_msg").html("代理商添加成功，正在添加分润数据，请稍后...");
		        			/**
		    				 * 添加代理商成功后，添加分润
		    				 */
		        			var standardRate="";
					    	var tariffRate="";
					    	var profitRatio="";
					    	var cappenAmount="";
					    	var srs=$("input[name='standardRate']");
					    	var tfs=$("input[name='tariffRate']");
					    	var prs=$("input[name='profitRatio']");
					    	var cas=$("input[name='cappenAmount']");
							var flag=false;
							for(var i=0;i<tfs.length;i++){
								if($(srs[i]).prop("checked")){
									standardRate=standardRate+$(srs[i]).val()+",";
									tariffRate=tariffRate+$(tfs[i]).val()+",";
									profitRatio=profitRatio+$(prs[i]).val()+",";
									cappenAmount=cappenAmount+$(cas[i]).val()+",";
								}
							}
							$.ajax({
									type: "POST", 
					            	url:"../../pmsprofitdistribution/save1.action?standardRate="+standardRate+"&tariffRate="+tariffRate+"&profitRatio="+profitRatio+"&cappenAmount="+cappenAmount,
					            	success:function(data){
					            		var info=$.parseJSON(data);
					            		if(info=="true"){
					            			$.messager.confirm('确认对话框', '添加成功,您想要继续添加吗？', function(r){
					            				if (r){
					            					window.location.href="save.jsp";
					            				}else{
					            					parent.$("#PmsAgentInfoSaveDiv").window("close");
					            				}
					            			});
					            			$("#add_msg").html("操作成功,请继续添加");
					            		}else{
					            			$("#add_msg").html("因网络异常，代理商添加分润失败,请前往分润修改页面修改");
					            		}
					               }
				            });
	            }else{
	            	 $("#add_msg").html("添加失败,请查看添加信息是否正确!!!");
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



//选中标准费率后，取消只读，设置验证
function setread(sr,tr,pr,ca){
	var no;
	if(sr.length>3){
		no=sr.substr((sr.length-2),(sr.length));
	}else{
		no=sr.split("")[2];
	}
	if($("#"+sr+"").prop('checked')){
		$("#"+tr+"").prop('readonly',false);
		$("#"+pr+"").prop('readonly',false);
		if(no>=7){
			$("#"+ca+"").prop('readonly',false);
			$("input[id='"+ca+"']").numberbox({    
			    min:0,    
			    precision:1,
			    required: true,
			    missingMessage:"请输入"
			});
		}
		
		$("input[id='"+tr+"']").numberbox({  
			min:0,    
		    precision:3,
		    required: true,
		    missingMessage:"请输入"
			
		})
		
		$("input[id='"+pr+"']").numberbox({  
			min:0,    
		    precision:2,
		    required: true,
		    missingMessage:"请输入"
			
		});
		
		
		
		/*var numberboxs=$("input[id='"+tr+"'],input[id='"+pr+"']");
		numberboxs.numberbox({    
		    min:0,    
		    precision:2,
		    required: true,
		    missingMessage:"请输入"
		});*/
//取消选中后,清空内容,取消必须选项
	}else{
		var numberboxs=$("input[id='"+tr+"'],input[id='"+pr+"']");
		numberboxs.numberbox('setValue','');
		numberboxs.numberbox({
			required:false
		});
		if(no>=7){
			$("input[id='"+ca+"']").numberbox({
			    required: false
			});
			$("input[id='"+ca+"']").numberbox('setValue','');
		}
		$("#"+ca+"").prop('value','');
		$("#"+tr+"").prop('readonly',true);
		$("#"+pr+"").prop('readonly',true);
		$("#"+ca+"").prop('readonly',true);
		
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
		}else{
			return true;
		}
	}
}
	
  

//手机号位数验证
$.extend($.fn.validatebox.defaults.rules, {   
     minLength: {   
        validator: function(value, param){   
            return value.length >= param[0];   
        },   
        message: '请输入{0}位手机号!'
    }   
});
//手机号验证
$('#agentTelephone').change(function(){
	var num = document.getElementById("agentTelephone");
	var partten = /^1[3,5,4,7,8]\d{9}$/;
	if (!partten.test(num.value)){
	$.messager.alert('错误信息!','请输入正确的手机号!','info');			
				$('#agentTelephone').val('');
				return false;
	}
});

//对电子邮件的验证
$('#agentEmail').blur(function() {
	var email = document.getElementById("agentEmail");
		var Myemail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
		if (!Myemail.test(email.value)) {
			$.messager.alert('错误信息!','请输入正确的E_mail!','info');
			$('#agentEmail').val('');
			return false;
		}
});