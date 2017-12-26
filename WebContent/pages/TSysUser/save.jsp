
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ include file="/WEB-INF/common/commons.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"> 
<HTML xmlns="http://www.w3.org/1999/xhtml"> 
	<HEAD>  
	    <meta charset="UTF-8">  
		<title>后台菜单</title>  
	</HEAD>     
	<BODY>   
		<!-- 添加界面 --> 
		<div class="easyui-layout" data-options="fit:true">
			<form id="TSysUserSaveForm" method="post" > 
				<div data-options="region:'center'" style="padding:10px;">
					<p>userStatus:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="userStatus" ></p> 
					<p>loginName:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="loginName" ></p> 
					<p>trueName:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="trueName" ></p> 
					<p>roleId:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="roleId" ></p> 
					<p>departmentId:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="departmentId" ></p> 
					<p>pwdDate:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="pwdDate" ></p> 
					<p>mobileno:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="mobileno" ></p> 
					<p>loginPwd:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="loginPwd" ></p> 
					<p>email:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="email" ></p> 
					<p ><span id="add_msg" style="margin-left: 60px;color: green;font: 13px;font-weight: bolder;">&nbsp;</span></p>
				</div>
				<div data-options="region:'south',border:false" style="text-align:right;padding:10px;padding-bottom: 5px;" >
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" style="margin-right: 15px " onclick="add_save('TSysUserSaveForm','tsysuser')">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cut'" href="javascript:void(0)" style="margin-right: 15px " onclick="doReset('TSysUserSaveForm')">重置</a>
				</div>
			</form>
		</div>

	</BODY>
</html>

