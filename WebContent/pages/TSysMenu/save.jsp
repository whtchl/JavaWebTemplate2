
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
			<form id="TSysMenuSaveForm" method="post" > 
				<div data-options="region:'center'" style="padding:10px;">
					<p>menuHref:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="menuHref" ></p> 
					<p>menuName:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="menuName" ></p> 
					<p>platformId:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="platformId" ></p> 
					<p>menuLevel:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="menuLevel" ></p> 
					<p>sort:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="sort" ></p> 
					<p>parentid:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="parentid" ></p> 
					<p ><span id="add_msg" style="margin-left: 60px;color: green;font: 13px;font-weight: bolder;">&nbsp;</span></p>
				</div>
				<div data-options="region:'south',border:false" style="text-align:right;padding:10px;padding-bottom: 5px;" >
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" style="margin-right: 15px " onclick="add_save('TSysMenuSaveForm','tsysmenu')">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cut'" href="javascript:void(0)" style="margin-right: 15px " onclick="doReset('TSysMenuSaveForm')">重置</a>
				</div>
			</form>
		</div>

	</BODY>
</html>

