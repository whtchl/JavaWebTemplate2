
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
			$("#PmsDepartmentUPdateDiv").window({
	       		onBeforeClose: function () {  //当面板关闭之前触发的事件
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
	    	    
	function updateOpens(tableName,updateDivName,iframeName,pkName){
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
		     // $("#"+iframeName+"")[0].src="update1.jsp?pkName="+ row_info(row,pkName)+"";
			}
			}
	</script>  
	<BODY>   
		<table id="PmsDepartmentTab" class="easyui-datagrid" data-options="url:'../../pmsdepartment/selectPage.action?oemNumber=1',pageList:[10,20,22,30,40] , 
		       singleSelect:true,fit:true,fitColumns:true,toolbar:'#tb',pagination:true,idField:'id',singleSelect:false,pageSize:22" >  
			   <thead>   
			          <tr>
	                        <th data-options="field:'ck',checkbox:true"></th>
				            <th data-options="field:'departmentNum',sortable:true" width="20">部门编号</th> 
				            <th data-options="field:'departmentName',sortable:true" width="20">部门名称</th> 
			          </tr>   
			   </thead>   
	     </table>   

	     <!-- 搜索div,功能div -->
	     <div id="tb">
	          <form method="post" id="PmsDepartmentSearchForm">
	               <div  class="formDiv">
	         			<ul >
	         			 	<li>
	      						部门编号：<INPUT class=textfield size=18  name="departmentNum" >  
	     		     	 	</li> 
	     				  	<li >
	     		     		 	部门名称：<INPUT class=textfield size=18  name="departmentName" > 
	     		     	  	</li>
			     			<li>
			     			      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)"  onclick="FormData('PmsDepartmentTab','PmsDepartmentSearchForm')">查询</a>
			     			</li>
		     	    	</ul>
				   </div>
	          </form>
			<div class="operate">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOpen('PmsDepartmentSaveDiv','PmsDepartmentSaveIframe')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateOpens('PmsDepartmentTab','PmsDepartmentUPdateDiv','PmsDepartmentUPdateIframe','id')">修改(详细)</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="delete_infos()">删除</a>
			</div>
	   </div>

	    <!-- 添加界面 -->
	    <div id="PmsDepartmentSaveDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-add" title="添加" style="width:300px;height:250px;text-align: center;">
			<iframe scrolling="auto" id="PmsDepartmentSaveIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>

	    <!-- 修改界面 -->
	    <div id="PmsDepartmentUPdateDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-edit" title="修改" style="width:300px;height:250px;text-align: center;">
			<iframe scrolling="auto" id="PmsDepartmentUPdateIframe" frameborder="0"   style="width:97%;height:97%;margin-top: 1%;"></iframe>
	   </div>
	<script type="text/javascript">
		function delete_infos(){
			var rows = $("#PmsDepartmentTab").datagrid("getSelections");    // 获取所有选中的行
			if(rows.length==0){
				$.messager.alert('信息提示','请选择删除信息!!!','error'); 
			}else{
				if (rows.length>1) {
					$.messager.alert('信息提示','只能删除一行信息!!!','error'); 
				}else{
					$.ajax({
						url:'../../pmsperson/select.action?departmentId='+rows[0].departmentNum,
						type:'post',
						dataType:'json',
						success:function(data){
							if (data.length!=0) {
								$.messager.alert('信息提示','该部门下有人员!!','error');
							}else{
								delete_info('PmsDepartmentTab','pmsdepartment','id');
							}
						}
					});
				}
			}
		}
		
	</script>
	</BODY>
</html>

