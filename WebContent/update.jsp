<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"> 
<HTML xmlns="http://www.w3.org/1999/xhtml"> 
	<HEAD>  
	    <meta charset="UTF-8">  
		<title>后台菜单</title>  
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.2/themes/default/easyui.css">  
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.2/themes/icon.css">  
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.2/themes/demo.css">  
		<link rel="stylesheet" type="text/css" href="css/main.css">   
		<script type="text/javascript" src="jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>  
		<script type="text/javascript" src="jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="js/config.js" ></script>     
	</HEAD>     
	<BODY>   
		<!-- 修改界面 --> 
		<div class="easyui-layout" data-options="fit:true" >
			<form id="LoginUpdateForm" method="post"> 
				<div data-options="region:'center'" style="padding:10px; text-align: center;">
			            <input type='hidden' name="id" > 
			            <br/>
			            <p>&nbsp;&nbsp;用户名称:&nbsp;&nbsp;<input class="easyui-validatebox" readonly="readonly" name="loginName" value="${user.loginName}" /><span id="required">*</span></p> 
						
						<p>&nbsp;&nbsp;&nbsp;新&nbsp;密&nbsp;码:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="newPwd" id="newPwd" type="password"/><span id="required">*</span></p>
						<p>&nbsp;确认密码:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="newPwd2" onblur="checkPwd()" onfocus="clearPwd()" id="newPwd2" type="password"/><span id="required" class="required">*</span></p>
						<p ><span id="update_msg" style="margin-left: 60px;color: green;font: 13px;font-weight: bolder;"></span></p>
						<div align="right" style="padding-right: 80px; margin-bottom: 10px;"><em style="color: red;">*</em>为必填项</div>
					<div data-options="region:'south',border:false" style="text-align:center;padding:25px;" >
						<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="updatePwd_save('LoginUpdateForm','login')">修改</a>
					</div>
				</div>
			</form>
		</div>
	<script type="text/javascript">
		function checkPwd(){
		             $("#update_msg").val("");
	     			 var pwd=$('#newPwd').val();
	     			 var pwd2=$('#newPwd2').val();
	     			 
	     			 if (pwd!=pwd2) {
						$("#update_msg").text("两次密码输入不一致");
					 }
	     	}
		function clearPwd(){
		             $("#update_msg").text("");
	     	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	</script>
	</BODY>
</html>

