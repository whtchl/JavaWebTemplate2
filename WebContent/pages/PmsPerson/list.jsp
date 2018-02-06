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
			$("#PmsPersonUpdateDiv").window({
	       		onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#PmsPersonTab').datagrid('load');  
	                 $("#update_msg").html("&nbsp;");
	       		}
	 		});
			//关闭添加界面时,刷新列表
			$('#PmsPersonSaveDiv').window({
	      		 onBeforeClose: function () { //当面板关闭之前触发的事件
	         		 $('#PmsPersonTab').datagrid('load');  
	                 $("#add_msg").html("&nbsp;");
			     }
		    });
		    $('#departmentId').combobox({
		    	url:'../../pmsdepartment/selectcombox.action',
		    	valueField:'departmentNum',
		    	textField:'departmentName',
		    	panelHeight:110,
		    	onSelect:function(rec){
		    		var url='../../pmsperson/selectcombox.action?departmentId='+rec.departmentNum;
		    		$('#personNum').combobox('reload',url);
		    	}
		    });
	    });
	    
	    function updateOpen(tableName,updateDivName,iframeName,pkName){
	    var rows=$("#"+tableName+"").datagrid("getSelections");
	    if(rows.length<1){
			$.messager.alert('信息提示','请选择修改信息!!!','error'); 
		}else if(rows.length>1){
			$.messager.alert('信息提示','只能选择一条修改信息','error'); 
		}else{
			  var row=rows[0];//获取类表中的选中的列
			  var high=$("#"+updateDivName+"").window("options").height;
		      var wide=$("#"+updateDivName+"").window("options").width;
		      var title=$("#"+updateDivName+"").window("options").title;
		      var path="../pages/"+updateDivName.substr(0,updateDivName.length-9)+"/update1.jsp?pkName=";
		      parent.$("#updateDiv").window({fit:false,height:high,width:wide,title:title}).window('open').window('center');
		      parent.$("#updateIframe")[0].src=path+ row_info(row,pkName)+"";
		      //$("#"+updateDivName+"").dialog('open');
		      //$("#"+iframeName+"")[0].src="update1.jsp?pkName="+ row_info(row,pkName)+"";
		}
 }
	    
	</script>  
	<BODY>   
		<table id="PmsPersonTab" class="easyui-datagrid" data-options="url:'../../pmsperson/selectPage.action?oemNumber=1',pageList:[10,20,22,30,40] , 
		       singleSelect:true,fit:true,fitColumns:true,toolbar:'#tb',pagination:true,idField:'id',singleSelect:false,pageSize:22" >  
			   <thead>   
			          <tr>   
	                        <th data-options="field:'ck',checkbox:true"></th>
	                        <th data-options="field:'departmentName',sortable:true" width="20">部门名称</th> 
	                        <th data-options="field:'personNum',sortable:true" width="10">人员编号</th>
	                        <th data-options="field:'personName',sortable:true" width="20">人员名称</th> 
	                        <th data-options="field:'entrytime',sortable:true" width="20">入职时间</th> 
	                        <th data-options="field:'reigntime',sortable:true" width="20">辞职时间</th> 
	                        <th data-options="field:'telephone',sortable:true" width="20">电话号码</th>
	                        <th data-options="field:'idcard',sortable:true" width="20">身份证号</th>  
				            <th data-options="field:'address',sortable:true" width="30">家庭住址</th> 
				            <th data-options="field:'state',sortable:true,
				            	formatter: function(state){ 
				            		if (state == '1'){
				            			return '正常' 
				            		}else{
				            			return '离职'
				            		}}" width="15">状态</th> 
			          </tr>   
			   </thead>   
	     </table>   

	     <div id="tb">
	          <form method="post" id="PmsPersonSearchForm">
	               <div  class="formDiv">
	         			<ul >
	         			 	<li>
	      						人员编号：<INPUT class=textfield size=18  name="personNum" >  
	     		     	 	</li> 
	     				  	<li>
	     				  		所属部门:<select class="easyui-combobox" editable="false" style="width: 100px" id="departmentId" name="departmentId"></select>
	     				  	</li>
	     				  	<li>
	     				  		人员名称:<select class="easyui-combobox" id="personNum" name="personNum" editable="false" data-options="valueField:'personNum',textField:'personName',panelHeight:100" style="width: 100px"></select>
	     				  	</li>
			     		  	<li >
			     			           人员状态：<select class=easyui-combobox editable="false" data-options="panelHeight:70" style="width: 80px"  name="state" >
			     			           		<option value="1" selected="selected">正常</option>
			     			           		<option value="2">离职</option>
			     			           </select> 
			     		  	</li>
			     			<li>
			     			      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)"  onclick="FormData('PmsPersonTab','PmsPersonSearchForm')">查询</a>
			     			</li>
		     	    	</ul>
				   </div>
	          </form>
			<div class="operate">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOpen('PmsPersonSaveDiv','PmsPersonSaveIframe')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateOpen('PmsPersonTab','PmsPersonUPdateDiv','PmsPersonUPdateIframe','id')">修改(详细)</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="delete_info('PmsPersonTab','pmsperson','id')">删除</a>
			</div>
	   </div>

	    <!-- 添加界面 -->
	    <div id="PmsPersonSaveDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-add" title="添加" style="width:630px;height:500px;text-align: center;">
			<iframe scrolling="auto" id="PmsPersonSaveIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	    <!-- 修改界面 -->
	    <div id="PmsPersonUPdateDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-edit" title="修改" style="width:630px;height:500px;text-align: center;">
			<iframe scrolling="auto" id="PmsPersonUPdateIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>
	<script type="text/javascript">
		
	</script>
	</BODY>
</html>

