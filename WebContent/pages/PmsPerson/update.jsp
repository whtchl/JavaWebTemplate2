
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ include file="/WEB-INF/common/commons.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"> 
<HTML xmlns="http://www.w3.org/1999/xhtml"> 
	<HEAD>  
	    <meta charset="UTF-8">  
		<title>后台菜单</title>  
	</HEAD>     
		<script type="text/javascript">
			$(function () {
	     		 $.ajax({
					type : 'post',
					dataType : 'json',
					url:'../../pmsperson/select.action',
					data:{'id':${param.pkName}},
					error : function() {// 请求失败处理函数
						alert('请求失败');
					},
					success : function(data) { // 请求成功后处理函数。
	                	var inputValArray=$('#PmsPersonUpdateForm').serialize().split('&');
						for(var i=0;i<inputValArray.length;i++){
						     var inputName=inputValArray[i].split('=')[0];
						     $("#PmsPersonUpdateForm [name='"+inputName+"']").val(row_info(data[0],inputName));
						}
					}
				});
	     	});
		</script>  
	<BODY>   
		<!-- 修改界面 --> 
		<div class="easyui-layout" data-options="fit:true">
			<form id="PmsPersonUpdateForm" method="post" > 
				<div data-options="region:'center'" style="padding:10px;">
			            <p>address:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="address" ></p> 
			            <p>personNum:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="personNum" ></p> 
			            <p>entrytime:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="entrytime" ></p> 
			            <p>departmentId:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="departmentId" ></p> 
			            <p>personName:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="personName" ></p> 
			            <p>telephone:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="telephone" ></p> 
			            <p>reigntime:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="reigntime" ></p> 
			            <p>abbreviation:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="abbreviation" ></p> 
			            <p>rservePhone:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="rservePhone" ></p> 
			            <p>zipCode:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="zipCode" ></p> 
			            <p>idcard:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="idcard" ></p> 
			            <input type='hidden' name="id" > 
			            <p>state:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="state" ></p> 
			            <p>email:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" name="email" ></p> 
	                    <p ><span id="update_msg" style="margin-left: 60px;color: green;font: 13px;font-weight: bolder;">&nbsp;</span></p>
				</div>
				<div data-options="region:'south',border:false" style="text-align:right;padding:10px;padding-bottom: 5px;" >
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="update_save('PmsPersonUpdateForm','pmsperson')">修改</a>
				</div>
			</form>
		</div>

	</BODY>
</html>

