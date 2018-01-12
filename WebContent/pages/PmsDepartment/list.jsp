
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
			//关闭修改界面时,刷新列表
			$("#PmsDepartmentUpdateDiv").window({
	       		onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#PmsDepartmentTab').datagrid('load');  
	                 $("#update_msg").html("&nbsp;");
	       		}
	 		});
			//关闭添加界面时,刷新列表
			$('#PmsDepartmentSaveDiv').window({
	      		 onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#PmsDepartmentTab').datagrid('load');  
	                 $("#add_msg").html("&nbsp;");
			     }
		    });
	    });
	</script>  
	<BODY>   
		<table id="PmsDepartmentTab" class="easyui-datagrid" data-options="url:'../../pmsdepartment/selectPage.action',pageList:[10,20,22,30,40] , 
		       singleSelect:true,fit:true,fitColumns:true,toolbar:'#tb',pagination:true,idField:'id',pageSize:22" >  
			   <thead>   
			          <tr>   
	                        <th data-options="field:'ck',checkbox:true"></th>
				            <th data-options="field:'oemNumber',sortable:true" width="20">oemNumber</th> 
				            <th data-options="field:'departmentName',sortable:true" width="20">departmentName</th> 
				            <th data-options="field:'id',sortable:true" width="20">id</th> 
				            <th data-options="field:'departmentNum',sortable:true" width="20">departmentNum</th> 
			          </tr>   
			   </thead>   
	     </table>   

	     <!-- 搜索div,功能div -->
	     <div id="tb">
	          <form method="post" id="PmsDepartmentSearchForm">
	               <div  class="formDiv">
	         			<ul >
	         			 	<li>
	      						字段：<INPUT class=textfield size=18  name="" >  
	     		     	 	</li> 
	     				  	<li >
	     		     			字段：<INPUT class=textfield size=18  name="" > 
	     		     	  	</li>
			     		  	<li >
			     			         字段：<INPUT class=textfield size=18  name="" > 
			     		  	</li>
			     			<li>
			     			      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)"  onclick="FormData('PmsDepartmentTab','PmsDepartmentSearchForm')">查询</a>
			     			</li>
		     	    	</ul>
				   </div>
	          </form>
			<div class="operate">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOpen('PmsDepartmentSaveDiv','PmsDepartmentSaveIframe')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateOpen('PmsDepartmentTab','PmsDepartmentUpdateDiv','PmsDepartmentUpdateIframe','id')">修改(详细)</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="delete_info('PmsDepartmentTab','pmsdepartment','id')">删除</a>
			</div>
	   </div>

	    <!-- 添加界面 -->
	    <div id="PmsDepartmentSaveDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-add" title="添加" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="PmsDepartmentSaveIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	    <!-- 修改界面 -->
	    <div id="PmsDepartmentUpdateDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-edit" title="修改" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="PmsDepartmentUpdateIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	</BODY>
</html>

