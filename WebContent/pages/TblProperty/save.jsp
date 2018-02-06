
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
			<form id="TblPropertySaveForm" method="post" > 
				<div data-options="region:'center'" style="padding:10px;">
					<p>name:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为�?'" name="name" ></p> 
					<p>entityname:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为�?'" name="entityname" ></p> 
					<p>description:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为�?'" name="description" ></p> 
					<p>entityid:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为�?'" name="entityid" ></p> 
					<p>version:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为�?'" name="version" ></p> 
					<p>value:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为�?'" name="value" ></p> 
					<p>uniqueid:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为�?'" name="uniqueid" ></p> 
					<p ><span id="add_msg" style="margin-left: 60px;color: green;font: 13px;font-weight: bolder;">&nbsp;</span></p>
				</div>
				<div data-options="region:'south',border:false" style="text-align:right;padding:10px;padding-bottom: 5px;" >
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" style="margin-right: 15px " onclick="add_save('TblPropertySaveForm','tblproperty')">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cut'" href="javascript:void(0)" style="margin-right: 15px " onclick="doReset('TblPropertySaveForm')">重置</a>
				</div>
			</form>
		</div>

	</BODY>
</html>

