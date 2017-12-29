<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>修改密码</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
		<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>  
		<script type="text/javascript" src="../js/config.js" ></script>
		<SCRIPT type=text/javascript>
			$(function(){
			      alert("您目前使用的是初始密码，请立即修改密码");
					$('#form').submit(
				
						function (){						
						 	if($("#LoginPassword").val()==null || $("#LoginPassword").val()==""){
						 		$("#loginmsg").html('新密码为空').css("color","red");
						 		return false;
						 	}else{
						 		$("#loginmsg").html('');
						 	}
						 	if($("#code").val()==null || $("#code").val()==""){
								$("#loginmsg").html('确认密码为空').css("color","red");
								return false;
							}else{
						 		$("#loginmsg").html('');
						 	}
							if($("#LoginPassword").val()!= $("#code").val()){
								$("#LoginPassword").val('');
								$("#code").val('');
								$("#loginmsg").html('两次密码输入不一致').css("color","red");
								return false;
							}else{
						 		$("#loginmsg").html('');
						 	}
							return true;
						}
					);
				}
			);

			function clearmsg(){
				$("#loginmsg").html('');
			}
    	</SCRIPT>
		<META content="MSHTML 6.00.2900.5880" name=GENERATOR>
	<style type="text/css">
        	body{ position: relative; background: url(../images/login.jpg) center top #D6DEE0 ;background-repeat:inherit;}
        	.login{ position: absolute; left: 50%; top: 480px; margin-left: -185px; width: 370px; height: 100px;}
		</style>
	</HEAD>
	<body style="margin: 0px;">
	       
	         
				<form id="form" name=AdminLogin  action="/JavaWebTemplate2/login/updatePwd.action" method="post">
					<div class="login">
						<table >
						  
						    <tr style="line-height:45px;">
						    	<td style="font-weight: bolder;color: #0854B4;width: 140px;">用  户  名:</td>
						    	<td colspan="2">
									<INPUT id=LoginName name=loginName style="width:130px;"onmousedown="clearmsg()" value="${loginName }"  readonly="readonly"/>
								</td>
						    </tr>
						    
						     <tr style="line-height:45px;">
						    	<td style="font-weight: bolder;color: #0854B4;">新  密  码:</td>
						    	<td colspan="2">
									<INPUT id=LoginPassword type=password style="width:130px;"	name=loginPwd onmousedown="clearmsg()"/>
								</td>
						    </tr>
						    
						    <tr style="line-height:45px;">
						    	<td style="font-weight: bolder;color: #0854B4;">确认密码:</td>
						    	<td colspan="2">
									<INPUT id=code type="password" name=code style="width: 130px;" onmousedown="clearmsg()" value="${code}">
								</td>
						    </tr>
						    
						    <tr style="height:70px;">
								<td colspan="3" align="center"><span style="width: 20px;">&nbsp;</span>
									 <input style="width:80px; height:32px;margin-left: 50px;" type="image" src="../images/loginbtn.png" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <input style="width:80px; height:32px;margin-left: 15px;" type="image" src="../images/resetbtn.png" value="取消" onclick="doReset('LoginName','LoginPassword','code');"/>
								</td>
							</tr>
							<tr style="height: 30px;">
								<td colspan="3" align="center">
									<span id="loginmsg" style="color: red;float: left;margin-left: 50px;">${loginmsg}</span>
								</td>
							</tr>
						</table>
					</div>
				</form>
	
	</body>
</HTML>