
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
	<BODY>   
		<!-- 添加界面 -->  
		<div class="easyui-layout" data-options="fit:true">
			<form id="PmsDepartmentSaveForm" method="post" > 
				<div data-options="region:'center'" style="padding:10px;">
					<p>部门编号:&nbsp;&nbsp;<input class="easyui-validatebox" readonly="readonly" id="departmentNum" name="departmentNum" ><span id="required">*</span></p> 
					<p>部门名称:&nbsp;&nbsp;<input class="easyui-validatebox" data-options="required:true,missingMessage:'字段不可以为空'" id="departmentName" name="departmentName" ><span id="required">*</span></p> 
					<p ><span id="add_msg" style="margin-left: 60px;color: green;font: 13px;font-weight: bolder;">&nbsp;</span></p>
					<div align="right"><em style="color: red;font-size: 14">*</em>为必填项</div>
				</div>
				<div data-options="region:'south',border:false" style="text-align:right;padding:10px;padding-bottom: 5px;" >
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" style="margin-right: 15px " onclick="add_save()">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cut'" href="javascript:void(0)" style="margin-right: 15px " onclick="doReset()">重置</a>
				</div>
			</form>
		</div>

	</BODY>
	<script type="text/javascript">
		$(document).ready(function(){
				$.ajax({
					url:'../../pmsdepartment/select.action',
					type:'post',
					dataType:'json',
					success:function(data){
						if (data!=null) {
							var sends=0;
							for ( var i = 0; i < data.length; i++) {
								var send=parseInt(data[i].departmentNum);
								if (sends<send) {
									sends=send;
								}
							}
							$('#departmentNum').val(sends+1);
						}else{
							$('#departmentNum').val('1001');
						}
					}
				});
		});
		function doReset(){
			$('#departmentName').val('');
		}
		function add_save(){
			 $("#PmsDepartmentSaveForm").form('submit', {  
		    	   url : "../../pmsdepartment/save.action",  
		     	   dataType : 'text',  
		     	   data:$("#PmsDepartmentSaveForm").serialize(),
			        success : function(data) {
				 		var newData = $.parseJSON(data);
			            if(newData==1){
				             $('#departmentName').val('');
				             $("#add_msg").html("添加成功,可以继续添加");
				             			$.ajax({
									url:'../../pmsdepartment/select.action',
									type:'post',
									dataType:'json',
									success:function(data){
										if (data!="[]" || data.length!=0 || data==null) {
											var sends=0;
											for ( var i = 0; i < data.length; i++) {
												var send=parseInt(data[i].departmentNum);
												if (sends<send) {
													sends=send;
												}
											}
											$('#departmentNum').val(sends+1);
										}
									}
								});
			            } else{
			            	 $("#add_msg").html("添加失败,请查看添加信息是否正确!!!");
			            }
			        }  
		    });   
		}
	</script>
</html>

