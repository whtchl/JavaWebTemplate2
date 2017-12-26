
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
			$("#TSysMenuUpdateDiv").window({
	       		onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#TSysMenuTab').datagrid('load');  
	                 $("#update_msg").html("&nbsp;");
	       		}
	 		});
			//关闭添加界面时,刷新列表
			$('#TSysMenuSaveDiv').window({
	      		 onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#TSysMenuTab').datagrid('load');  
	                 $("#add_msg").html("&nbsp;");
			     }
		    });
	    });
	</script>  
	<BODY>   
		<table id="TSysMenuTab" class="easyui-datagrid" data-options="url:'../../tsysmenu/selectPage.action',pageList:[10,20,22,30,40] , 
		       singleSelect:true,fit:true,fitColumns:true,toolbar:'#tb',pagination:true,idField:'menuId',pageSize:22" >  
			   <thead>   
			          <tr>   
	                        <th data-options="field:'ck',checkbox:true"></th>
				            <th data-options="field:'menuHref',sortable:true" width="20">menuHref</th> 
				            <th data-options="field:'menuName',sortable:true" width="20">menuName</th> 
				            <th data-options="field:'platformId',sortable:true" width="20">platformId</th> 
				            <th data-options="field:'menuLevel',sortable:true" width="20">menuLevel</th> 
				            <th data-options="field:'sort',sortable:true" width="20">sort</th> 
				            <th data-options="field:'menuId',sortable:true" width="20">menuId</th> 
				            <th data-options="field:'parentid',sortable:true" width="20">parentid</th> 
			          </tr>   
			   </thead>   
	     </table>   

	     <!-- 搜索div,功能div -->
	     <div id="tb">
	          <form method="post" id="TSysMenuSearchForm">
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
			     			      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)"  onclick="FormData('TSysMenuTab','TSysMenuSearchForm')">查询</a>
			     			</li>
		     	    	</ul>
				   </div>
	          </form>
			<div class="operate">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOpen('TSysMenuSaveDiv','TSysMenuSaveIframe')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateOpen('TSysMenuTab','TSysMenuUpdateDiv','TSysMenuUpdateIframe','menuId')">修改(详细)</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="delete_info('TSysMenuTab','tsysmenu','menuId')">删除</a>
			</div>
	   </div>

	    <!-- 添加界面 -->
	    <div id="TSysMenuSaveDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-add" title="添加" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="TSysMenuSaveIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	    <!-- 修改界面 -->
	    <div id="TSysMenuUpdateDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-edit" title="修改" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="TSysMenuUpdateIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	</BODY>
</html>

