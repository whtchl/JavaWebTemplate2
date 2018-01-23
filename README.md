# JavaWebTemplate2
 
不需要 ： 角色管理，总部代理部门信息，总部代理人员信息
需要 ：商户报件，交易流水，公告管理，发卡统计


=============================
点击一个叶子，打开的界面是TSYSMenu的menuHref 从数据库中获得的。不是根据@Result中的路径。
admincontrol.jsp  下面这段代码根据数据库中的配置跳转到相应的界面。

		$('#menu ul').tree(
					{
						onClick : function(node) {
							temp = node;
							if ($('#menu ul').tree('isLeaf', temp.target)) {
								//最内层的菜单点击事件时,在主页面中加载tab
								 $.post("/JavaWebTemplate2/tsysmenu/select.action?idArray="
												+ node.id, function(data) {
											addTab(data[0].menuName,
													data[0].menuHref);
										}, 'json') 
								//window.location.href = "/JavaWebTemplate2/login/login.action";
							}
						}
					});
