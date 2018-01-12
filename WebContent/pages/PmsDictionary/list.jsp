
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
			$("#PmsDictionaryUpdateDiv").window({
	       		onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#PmsDictionaryTab').datagrid('load');  
	                 $("#update_msg").html("&nbsp;");
	       		}
	 		});
			//关闭添加界面时,刷新列表
			$('#PmsDictionarySaveDiv').window({
	      		 onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#PmsDictionaryTab').datagrid('load');  
	                 $("#add_msg").html("&nbsp;");
			     }
		    });
	    });
	</script>  
	<BODY>   
		<table id="PmsDictionaryTab" class="easyui-datagrid" data-options="url:'../../pmsdictionary/selectPage.action',pageList:[10,20,22,30,40] , 
		       singleSelect:true,fit:true,fitColumns:true,toolbar:'#tb',pagination:true,idField:'id',singleSelect:false,pageSize:22" >  
			   <thead>   
			          <tr>   
	                        <th data-options="field:'ck',checkbox:true"></th>
				            <th data-options="field:'id',sortable:true" width="20">ID</th> 
				            <th data-options="field:'type',sortable:true" width="20">类型</th>
				            <th data-options="field:'key',sortable:true" width="20">key</th>  
				            <th data-options="field:'value',sortable:true" width="20">值</th> 
				            <th data-options="field:'disValue',sortable:true" width="20">显示值</th> 
				            <th data-options="field:'description',sortable:true" width="20">详细描述</th> 
				            
			          </tr>   
			   </thead>   
	     </table>   

	     <!-- 搜索div,功能div -->
	     <div id="tb">
	          <form method="post" id="PmsDictionarySearchForm">
	               <div  class="formDiv">
	         			<ul >
	         			 	<li>
	      						类型：<INPUT class=textfield size=18  name="type" >  
	     		     	 	</li> 
	     				  	<li >
	     		     			key：<INPUT class=textfield size=18  name="key" > 
	     		     	  	</li>
			     		  	<li >
			     			         描述：<INPUT class=textfield size=18  name="description" > 
			     		  	</li>
			     			<li>
			     			      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)"  onclick="FormData('PmsDictionaryTab','PmsDictionarySearchForm')">查询</a>
			     			</li>
		     	    	</ul>
				   </div>
	          </form>
			<div class="operate">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOpen('PmsDictionarySaveDiv','PmsDictionarySaveIframe')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateOpen('PmsDictionaryTab','PmsDictionaryUPdateDiv','PmsDictionaryUPdateIframe','id')">修改(详细)</a>
			</div>
	   </div>

	    <!-- 添加界面 -->
	    <div id="PmsDictionarySaveDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-add" title="添加" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="PmsDictionarySaveIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	    <!-- 修改界面 -->
	    <div id="PmsDictionaryUPdateDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-edit" title="修改" style="width:530px;height:350px;text-align: center;">
			<iframe scrolling="auto" id="PmsDictionaryUPdateIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	</BODY>
</html>

