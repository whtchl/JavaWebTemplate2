
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
			$("#TSysRoleUpdateDiv").window({
	       		onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#TSysRoleTab').datagrid('load');  
	                 $("#update_msg").html("&nbsp;");
	       		}
	 		});
			//关闭添加界面时,刷新列表
			$('#TSysRoleSaveDiv').window({
	      		 onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#TSysRoleTab').datagrid('load');  
	                 $("#add_msg").html("&nbsp;");
			     }
		    });
	    });
	</script>  
	<BODY>   
		<table id="TSysRoleTab" class="easyui-datagrid" data-options="url:'../../tsysrole/selectPage.action',pageList:[10,20,22,30,40] , 
		       singleSelect:true,fit:true,fitColumns:true,toolbar:'#tb',pagination:true,idField:'roleId',pageSize:22" >  
			   <thead>   
			          <tr>   
	                        <th data-options="field:'ck',checkbox:true"></th>
				            <th data-options="field:'roleName',sortable:true" width="20">roleName</th> 
				            <th data-options="field:'roleId',sortable:true" width="20">roleId</th> 
				            <th data-options="field:'levels',sortable:true" width="20">levels</th> 
			          </tr>   
			   </thead>   
	     </table>   

	     <!-- 搜索div,功能div -->
	     <div id="tb">
	          <form method="post" id="TSysRoleSearchForm">
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
			     			      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)"  onclick="FormData('TSysRoleTab','TSysRoleSearchForm')">查询</a>
			     			</li>
		     	    	</ul>
				   </div>
	          </form>
			<div class="operate">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOpen('TSysRoleSaveDiv','TSysRoleSaveIframe')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateOpen('TSysRoleTab','TSysRoleUpdateDiv','TSysRoleUpdateIframe','roleId')">修改(详细)</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="delete_info('TSysRoleTab','tsysrole','roleId')">删除</a>
			</div>
	   </div>

	    <!-- 添加界面 -->
	    <div id="TSysRoleSaveDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-add" title="添加" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="TSysRoleSaveIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	    <!-- 修改界面 -->
	    <div id="TSysRoleUpdateDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-edit" title="修改" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="TSysRoleUpdateIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	</BODY>
</html>

