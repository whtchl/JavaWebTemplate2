
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"> 
<HTML xmlns="http://www.w3.org/1999/xhtml"> 
	<HEAD>  
	    <meta charset="UTF-8">  
		<title>后台菜单</title>  
		<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.3.2/themes/default/easyui.css">  
		<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.3.2/themes/icon.css">  
		<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.3.2/themes/demo.css">  
		<link rel="stylesheet" type="text/css" href="../../css/main.css">   
		<script type="text/javascript" src="../../jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>  
		<script type="text/javascript" src="../../jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="../../js/config.js" ></script>     
	</HEAD>     
		<script type="text/javascript">
			$(function () {
	     		 $.ajax({
					type : 'post',
					dataType : 'json',
					url:'../../pmsdepartment/select.action',
					data:{'id':${param.pkName}},
					error : function() {// 请求失败处理函数
						alert('请求失败');
					},
					success : function(data) { // 请求成功后处理函数。
	                	var inputValArray=$('#PmsDepartmentUpdateForm').serialize().split('&');
						for(var i=0;i<inputValArray.length;i++){
						     var inputName=inputValArray[i].split('=')[0];
						     $("#PmsDepartmentUpdateForm [name='"+inputName+"']").val(row_info(data[0],inputName));
						}
					}
				});
	     	});
		</script>  
	<BODY>   
		<!-- 修改界面 -->
		<center> 
			<form id="PmsDepartmentUpdateForm" method="post"> 
			            <input type='hidden' name="id" > 
			            <p>部门编号:&nbsp;&nbsp;<input class="easyui-validatebox" readonly="readonly" data-options="required:true,missingMessage:'字段不可以为空'" name="departmentNum" ><span id="required">*</span></p> 
						<p>部门名称:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="departmentName" ><span id="required">*</span></p> 
						<p ><span id="update_msg" style="margin-left: 60px;color: green;font: 13px;font-weight: bolder;">&nbsp;</span></p>
						<div align="right"><em style="color: red;font-size: 14">*</em>为必填项</div>
				<div style="text-align:right;padding:10px;padding-bottom: 5px;" >
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="update_save('PmsDepartmentUpdateForm','pmsdepartment')">修改</a>
				</div>
			</form>
		</center>
	</BODY>
</html>

