
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
		<script type="text/javascript" src="../../jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>  
		<script type="text/javascript" src="../../js/config.js" ></script>     
	</HEAD>     
	<script type="text/javascript">
		$(function () {
			//关闭修改界面时,刷新列表
			$("#TSysUserUpdateDiv").window({
	       		onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#TSysUserTab').datagrid('load');  
	                 $("#update_msg").html("&nbsp;");
	       		}
	 		});
			//关闭添加界面时,刷新列表
			$('#TSysUserSaveDiv').window({
	      		 onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#TSysUserTab').datagrid('load');  
	                 $("#add_msg").html("&nbsp;");
			     }
		    });
	    });
	</script>  
	<BODY>   
		<table id="TSysUserTab" class="easyui-datagrid" data-options="url:'../../tsysuser/selectPage.action',pageList:[10,20,22,30,40] , 
		       singleSelect:true,fit:true,fitColumns:true,toolbar:'#tb',pagination:true,idField:'id',pageSize:22" >  
			   <thead>   
			          <tr>   
	                        <th data-options="field:'ck',checkbox:true"></th>
				            <th data-options="field:'id',sortable:true" width="20">编号</th> 
				             <th data-options="field:'loginName',sortable:true" width="20">登录名</th> 
				             <th data-options="field:'trueName',sortable:true" width="20">真实姓名</th> 
				            <th data-options="field:'roleName',sortable:true" width="20">角色名称</th>
				            <th data-options="field:'departmentName',sortable:true" width="20">部门名称</th>  
				            <th data-options="field:'email',sortable:true" width="20">电子邮件</th> 				           				     
				            <th data-options="field:'userStatus',sortable:true,formatter:function(userStatus){if(userStatus=='0'){return '开通';}if(userStatus=='1'){return '无效';}}"  width="20">用户状态</th> 				            
				            <th data-options="field:'mobileno',sortable:true" width="20">手机号</th> 
			          </tr>   
			   </thead>   
	     </table>   

	     <!-- 搜索div,功能div -->
	     <div id="tb">
	          <form method="post" id="TSysUserSearchForm">
	               <div  class="formDiv">
	         			<ul >
	         			 	<li>
	      						编号：<INPUT class=textfield size=18  name="id" >  
	     		     	 	</li> 
	     				  	<li >
	     		     			姓名：<INPUT class=textfield size=18  name="trueName" > 
	     		     	  	</li>
			     		  	<li >
			     			         登录名：<INPUT class=textfield size=18  name="loginName" > 
			     		  	</li>
			     			<li>
			     			      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)"  onclick="FormData('TSysUserTab','TSysUserSearchForm')">查询</a>
			     			</li>
		     	    	</ul>
				   </div>
	          </form>
			<div class="operate">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOpen('TSysUserSaveDiv','TSysUserSaveIframe')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateOpen('TSysUserTab','TSysUserUpdateDiv','TSysUserUpdateIframe','id')">修改(详细)</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="delete_info('TSysUserTab','tsysuser','id')">删除</a>
			</div>
	   </div>

	    <!-- 添加界面 -->
	    <div id="TSysUserSaveDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-add" title="添加" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="TSysUserSaveIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	    <!-- 修改界面 -->
	    <div id="TSysUserUpdateDiv" class="easyui-window" closed="true" fit="true" closed="true" modal="true" iconCls="icon-edit" title="修改" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="TSysUserUpdateIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	</BODY>
</html>

