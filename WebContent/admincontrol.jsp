<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>主界面</title>
	</head>
	<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/themes/default/easyui.css">  
	<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/themes/icon.css">  
	<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/themes/demo.css">  
	<link rel="stylesheet" type="text/css" href="../css/main.css">  
	<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>  
	<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="../js/config.js" ></script>   
	<script type="text/javascript">
	 $(function(){
	 	window.setInterval("checkuser()", 30*60*1000+1000);
		/* $("#LoginUPdateDiv").dialog({
			onBeforeClose: function () { //当面板关闭之前触发的事件
				window.location.href="/lf_pms/login/login.action";
			}
		}); */
	 
	         setInterval("getCurrentTime()", "1000");//获得系统时间
		/* 	 $('#menu ul').tree({
			    onClick: function(node){
			          temp=node;
			          if($('#menu ul').tree('isLeaf',temp.target)){
			             //最内层的菜单点击事件时,在主页面中加载tab
			              $.post("/lf_pms/tsysmenu/select.action?idArray="+node.id,function(data){
			                   addTab(data[0].menuName,data[0].menuHref);
			              },'json')
	                  } 
			    }
			}); */
			 <%--
			$("#updateDiv").window({
				onBeforeClose :function(){
					// 调用 'refresh' 方法更新选项卡面板的内容
					//alert($("#PmsMerchantInfoTab").datagrid("load"));
					var tab = $('#centre').tabs('getSelected');  // 获取选择的面板
					var a = tab.panel()[0].children[0];
					if (a)
					a.onload = function (){
						var ss = a.contentDocument.getElementById("PmsMerchantInfoTab");
						alert($(a.contentDocument).children(".datagrid-view").length);
						$(a.contentDocument).children(".datagrid-view").datagrid("reload");
						alert('');
					}
					//tab.panel('refresh');
				}
			});
			$("#detailDiv").window({
				onBeforeClose :function(){
					alert($("#PmsMerchantInfoTab").datagrid("load"));
					// 调用 'refresh' 方法更新选项卡面板的内容
					//var tab = $('#centre').tabs('getSelected');  // 获取选择的面板
					//tab.panel('refresh');
				}
			});
			--%>
			//addTabFr();
// 			loadnotice();
	    })  
	    
   		  //获得系统时间
     function getCurrentTime() {
		 $("#timeInfo").html(new Date().toLocaleString()+'  星期'+'日一二三四五六'.charAt(new Date().getDay()));
      }
   	
		function exit() {
			window.location.href = "/JavaWebTemplate2/login/login.action";
		}
		
	  	function openDiv(){
	   		$('#LoginUPdateDiv').dialog({
	   			closed:false
	   		});
	   	}
	</script>
	
	
	<body id="maindiv" style="margin: 0px;padding: 0px;" class="easyui-layout" >
		<div data-options="region:'north',border:false" style="height:70px;background:#E6EEF8;padding:0px">
		
			<div style="float: left;font-size: 35px;font-weight: bold;margin-top: 20px;margin-left: 10px;color: #0F61DC"><img src='../images/logo_yufubao.png'   width="330"/>  </div>
			<div style="float: right;margin-top: 35px;margin-right: 10px;" id="timeInfo"></div>
			<div style="float: right;margin-top: 35px;margin-right: 20px;" >欢迎你：&nbsp;&nbsp; 
			     <span id="userName">${user.loginName}</span>&nbsp;<span>[${RoleName }]</span>
			</div>
			<div style="float: right;margin-top: 35px;margin-right: 20px;">
				<a href="javascript:void(0)" onclick="exit()" id="exit" >退出</a>
			</div>
			<div style="float: right;margin-top: 30px;margin-right: 15px;">
				<a href="/JavaWebTemplate2/file/进件标准.rar" class="easyui-linkbutton" plain="true"  id="changepwd">进件模板下载</a>
			</div>
			<!-- 修改密码  -->
			<div style="float: right;margin-top: 30px;margin-right: 15px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="openDiv()" id="changepwd">修改密码</a>
			</div>
		</div>
		<div data-options="region:'west',split:true" title="系统功能列表" style="width:250px;">
				<div class="easyui-accordion" data-options="fit:true,border:false" id="menu">
					<c:forEach items="${oneMenuList}" var="menu">
				              <div style="padding-top: 10px;" title="${menu.menuName}">
				                  <ul id="tree${menu.menuId}" class="easyui-tree" data-options='animate:true,data:${menu.children}'>
	                              </ul>
				            </div>
	                </c:forEach>
				</div>
	    </div>
	    
	    
	      <!-- 修改界面 -->
	    <div id="LoginUPdateDiv" class="easyui-dialog" closed="true" modal="true" iconCls="icon-edit" title="修改密码" style="width:600px;height:370px;text-align: center;" >
			<iframe scrolling="auto" id="LoginUPdateIframe" frameborder="0" src="../update.jsp" style="width:97%;height:97%; margin-top: 1%;"></iframe>
	   </div>
	    
		<div data-options="region:'south',border:false" style="height:50px;background:#E6EEF8;padding:10px;text-align: center;color: #0F61DC;">
			<p style="margin-top: -2px;"> 后  台  管  理  系  统  </p>
		</div>
		
		<div data-options="region:'center',iconCls:'icon-ok'" style=" background-image: url('jquery-easyui-1.3.2/themes/default/images/top.jpg');"  id="mainCenter">
				<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="centre">
					<!--<div title="主界面"  style="padding:10px" data-options="closable:true">
						 <a href="javascript:void(0)" style="float: right" data-options="iconAlign:'right',iconCls:'icon-reload'" class="easyui-linkbutton" onclick="loadnotice()">刷新公告</a>
						<div id="table"></div> 
					</div>-->
				</div>
		</div>
		
		<!-- 详情界面 -->
	    <div id="detailDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-edit" title="详情" >
			<iframe scrolling="auto" id="detailIframe" frameborder="0"   style="width:100%;height:95%;margin-top: 1%;"></iframe>
	   </div>
	    <!-- 修改界面 -->
	    <div id="updateDiv" class="easyui-window" closed="true" modal="true" iconCls="icon-edit" title="修改" >
			<iframe scrolling="auto" id="updateIframe" frameborder="0" style="width:100%;height:95%; margin-top: 1%;"></iframe>
	   </div>
	</body>
</html>