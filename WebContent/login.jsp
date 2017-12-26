<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>管理员登陆</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
		<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>  
		<script type="text/javascript" src="../js/config.js" ></script>
		<SCRIPT type=text/javascript>
		 $(function(){
		 changeWindow();
		    var zt=$("#zt").val();
		    if(zt=='1'){
		       alert('密码修改成功'); 
		    }
		    if (zt=='0'){
		       alert('密码修改失败！！！');  
		    }
		})
		
			//点击变幻验证码图片函数 
		 	function changeImg(){
			    var imgSrc = $("#imgObj");  
			    var src = imgSrc.attr("src"); 
			    imgSrc.attr("src",chgUrl(src));
			}

		 	//时间戳     
		 	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
		 	function chgUrl(url){     
		 	    var timestamp = (new Date()).valueOf();     
				url = url + "?timestamp=" + timestamp;   
		 	    return url;     
		 	}
			
			$(function(){
					$('#form').submit(
						function (){						
							if($("#LoginName").val()==null || $("#LoginName").val()==""){
						 		$("#loginmsg").html('用户名为空').css("color","red");
						 		return false;
						 	}else{
						 		$("#loginmsg").html('');
						 	}
						 	if($("#LoginPassword").val()==null || $("#LoginPassword").val()==""){
						 		$("#loginmsg").html('密码为空').css("color","red");
						 		return false;
						 	}else{
						 		$("#loginmsg").html('');
						 	}
						 	if($("#code").val()==null || $("#code").val()==""){
								$("#loginmsg").html('验证码为空').css("color","red");
								return false;
							}
							return true;
						}
					);
				}
			);

			function clearmsg(){
				$("#loginmsg").html('');
			}
			function changeWindow(){
			$("#maindiv").width(1920).height(946).resize();
			
		}
    	</SCRIPT>
		<META content="MSHTML 6.00.2900.5880" name=GENERATOR>
		<style type="text/css">
        	.login{ position:absolute;left:45%;top:50%;width: 370px; height: 100px;}
		</style>
	</HEAD>
	<body >
	<img src="../images/login.jpg" width="100%" height="100%" style="z-index:-100;position:absolute;left:0;top:0">
		<form id="form" name=AdminLogin
			action="/lf_pms/login/main.action" method="post">
			<div class="login">
				<table style="line-height:45px;">
					<tr>
						<td style="font-weight: bolder;color:#0089C7">用户名:&nbsp;&nbsp;</td>
						<td>
						    <input type="hidden" value="${zt}" id="zt"/>
							<INPUT id=LoginName name=loginName style="width:130px;"
								onmousedown="clearmsg()" value="${loginName }" />
						</td>
					</tr>
					<tr>
						<td  style="font-weight: bolder;color:#0089C7">密&nbsp;&nbsp;码:&nbsp;&nbsp;</td>
						<td>
							<INPUT id=LoginPassword type=password style="width:130px;"
								name=loginPwd onmousedown="clearmsg()"/>
						</td>
					</tr>
					<tr>
						<td style="font-weight: bolder;color:#0089C7">验证码:&nbsp;&nbsp;</td>
						<td>
							<INPUT id=code maxLength=5 name=code
								style="width: 90px;" onmousedown="clearmsg()" value="${code}">&nbsp;&nbsp;
						</td>
						<td>
							<div style="width: 50px; float: right;">
								<IMG id="imgObj" onclick="changeImg()" alt=点击刷新
									src="/lf_pms/verifycode/verifyCode.action"
									border=0 />
							</div>
							
						</td>
					</tr>
					<tr style="height: 80px;">
						<td colspan="3" >
							 <input style="width:80px; height:32px;margin-left: 20px;" type="image" src="../images/loginbtn.png" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 <input style="width:80px; height:32px;margin-left: 45px;" type="image" src="../images/resetbtn.png" value="重置" onclick="doReset('LoginName','LoginPassword','code');"/>
						</td>
					</tr>
					
					<tr>
						<td colspan="3" align="center">
							<span id="loginmsg" style="color: red;">${loginmsg}</span>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</HTML>