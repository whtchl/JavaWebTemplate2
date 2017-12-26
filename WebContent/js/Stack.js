//领用人查询
function selAllPersion() {
	var departmentId = $('#usedepartment').combobox('getValue');
	$.ajax( {
		url : '../../pmspersonlect.action?departmentId=' + departmentId,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$('#usepersion').combobox( {
				valueField : data.model,
				textField : data.model,
				panelHeight : 'auto',// 自动高度适合
				editable : false
			// 不可编辑状态
					});
		}
	});

}

// 打开批量入库界面（window界面）
function batchIn(divName, iframeName) {
	var high=$("#"+divName+"").window("options").height;
    var wide=$("#"+divName+"").window("options").width;
    var path="../pages/PmsPosInfo/batchIn.jsp";
    parent.$("#updateDiv").window({fit:false,height:high,width:wide}).window('open').window('center');
    parent.$("#updateIframe")[0].src=path;
//	$("#" + divName + "").dialog('open');
//	$("#" + iframeName + "")[0].src = 'batchIn.jsp';
}

// 打开终端数量查询界面（window界面）
function searchOpen(divName, iframeName) {
	$("#" + divName + "").dialog('open');
	$("#" + iframeName + "")[0].src = 'search.jsp';
}

// 终端数量查询
function searchPos(addFormName, actionName) {
	$("#add_msg").html("保存数据中，请稍后...");
	$("#" + addFormName + "").form('submit', {
		url : "../../" + actionName + "/search.action",
		dataType : 'text',
		data : $("#" + addFormName + "").serialize(),
		success : function(data) {
			var newData = $.parseJSON(data);
			$("#count").val(newData);
			$("#add_msg").html("查询成功，终端数量是" + newData);
		}
	});
}

// 批量入库操作
function add_BatchIn(addFormName, actionName) {
	var manufacturerid = $("#manufacturerid").combobox('getValue');
	var posmodel = $("#posmodel").combobox('getValue');
	var serialno = $("#serialno").val();
	if (manufacturerid == '') {
		$.messager.alert('错误信息!', '厂商名称不能为空!', 'error');
		return false;
	}
	if (posmodel == '') {
		$.messager.alert('错误信息!', '型号不能为空!', 'error');
		return false;
	}
	if (serialno == '') {
		$.messager.alert('错误信息!', '终端序号不能为空!', 'error');
		return false;
	}
	if ((serialno.length < 6)) {
		$.messager.alert('错误信息!', '终端序号至少六位!', 'error');
		return false;
	}
	if ((!$.isNumeric(serialno.substring(serialno.length - 4,serialno.length)))) {
		$.messager.alert('错误信息!', '终端序号最后四位必须为数字!', 'error');
		return false;
	}
	if ($("#posCount").val() == '') {
		$.messager.alert('错误信息!', '数量不能为空!', 'error');
		return false;
	}
	if (!$.isNumeric($("#posCount").val())) {
		$.messager.alert('错误信息!', '数量只能为数字!', 'error');
		return false;
	}
	if ($("#posCount").val().length > 2) {
		$.messager.alert('错误信息!', '数量不能大于两位!', 'error');
		return false;
	}
	$("#add_msg").html("保存数据中，请稍后...");
	$("#" + addFormName + "").form('submit', {
		url : "../../" + actionName + "/add_batchIn.action",
		dataType : 'text',
		data : $("#" + addFormName + "").serialize(),
		success : function(data) {
			var newData = $.parseJSON(data);
			if (newData == 3) {
				$.messager.alert('错误信息!', '该终端已入库，请选择其他终端!', 'error');
			} else if (newData == 1) {
				$("#" + addFormName + " input").val('');
				doReset1();
				$("#add_msg").html("批量入库成功,可以继续入库");
			} else {
				$("#add_msg").html("批量入库失败,请查看入库信息是否正确!!!");
			}
		}
	});
}

// 出库操作
function stackOut(tableName, updateDivName, iframeName, pkName) {
	var rows = $("#" + tableName + "").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('信息提示', '请选择出库信息!!!', 'error');
	} else {

		var idArray = new Array();
		var serialnosArray = "";
		for ( var i = 0; i < rows.length; i++) {
			if (rows[i]['storagestatus'] != '0') {
				$.messager.alert('信息提示', '所选信息存在已出库终端，请重新选择!!', 'error');
				break;
			}
			var id = row_info(rows[i], pkName);
			var serialnos = row_info(rows[i], "serialno");
			idArray += id + ",";
			serialnosArray += serialnos + ",";
		}
		idArray = idArray.substr(0, idArray.length - 1);
		serialnosArray = serialnosArray.substr(0, serialnosArray.length - 1);
		$.messager.confirm('信息提示', "确定出库所选信息吗?", function(r) {
			if (r) {
				var high=$("#"+updateDivName+"").window("options").height;
			    var wide=$("#"+updateDivName+"").window("options").width;
			    var path="../pages/PmsPosInfo/stackOut.jsp?";
			    parent.$("#updateDiv").window({fit:false,height:high,width:wide}).window('open').window('center');
			    parent.$("#updateIframe")[0].src=path+'idArray='
				+ idArray + '&serialnosArray=' + serialnosArray;
//				$("#" + updateDivName + "").dialog('open');
//				$("#" + iframeName + "")[0].src =path+'idArray='
//						+ idArray + '&serialnosArray=' + serialnosArray;
			}
		});

	}
}


// 保存出库的信息
function update_stackOut(updateFormName, actionName) {
	var agentName = $("#agentName").combobox('getValue');
	if (agentName == '') {
		$.messager.alert('错误信息!', '出库总不能为空!', 'error');
		return false;
	}
	$.ajax( {
		url : "../../" + actionName + "/check.action",
		type : 'post',
		dataType : 'json',
		data : $("#" + updateFormName + "").serialize(),
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) {
			if (data.length != 0) {
				$.messager.alert('错误信息!', '该终端已出库，请选择其他终端!', 'error');
			} else {
				$.ajax( {
					type : 'post',
					dataType : 'json',
					url : "../../" + actionName + "/update_stackOut.action",
					data : $("#" + updateFormName + "").serialize(),
					error : function() {// 请求失败处理函数
						alert('请求失败');
					},
					success : function(data) { // 请求成功后处理函数。
						if (data == 1) {
							// 重置所有文本框的值
					$("#update_msg").html("出库成功,可以继续操作");
				} else {
					$("#update_msg").html("出库失败,请查看出库的信息是否正确!!!");
				}
			}
				});
			}
		}
	});
}
// 入库撤销方法
function inBack(tableId, actionName, pkName) {
	var rows = $("#" + tableId + "").datagrid("getSelections"); // 获取所有选中的行
	if (rows.length == 0) {
		$.messager.alert('信息提示', '请选择入库撤销信息!!!', 'error');
	} else {
		var idArray = "";
		for ( var i = 0; i < rows.length; i++) {
			var id = row_info(rows[i], pkName);
			if(row_info(rows[i],"posmold")=="2"){
				$.messager.alert('信息提示', '请不要对他备机进行入库撤销!!!', 'error');
				return ;
			}
			if(row_info(rows[i],"storagestatus")!="0"){
				$.messager.alert('信息提示', '该终端已出库，无法撤销入库', 'error');
				return ;
			}
			idArray += id + "-";
		}
		idArray = idArray.substr(0, idArray.length - 1);
		$.messager.confirm('撤销提示', "确定撤销选中的入库信息吗?", function(r) {
			if (r) {
				$.post("../../" + actionName + "/detele.action?idArray="+ idArray, function(data) {
					if (data == 2) {
						$.messager.alert('信息提示', '该终端已出库，无法撤销入库', 'error');
					} else if (data == 1) {
						$.messager.alert('信息提示', '入库撤销成功', 'info');
						$('#PmsPosInfoTab').datagrid('load');
					}
				}, 'json')
			}
		});
	}
}
// 出库撤销方法
function outBack(tableId, actionName, pkName) {
	var rows = $("#" + tableId + "").datagrid("getSelections"); // 获取所有选中的行
	if (rows.length == 0) {
		$.messager.alert('信息提示', '请选择出库撤销信息!!!', 'error');
	} else {
		var idArray = "";
		for ( var i = 0; i < rows.length; i++) {
			var id = row_info(rows[i], pkName);
			if(row_info(rows[i],"posmold")=="2"){
				$.messager.alert('信息提示', '请不要对他备机进行出库撤销!!!', 'error');
				return ;
			}
			if(row_info(rows[i],"storagestatus")=="0"){
				$.messager.alert('信息提示', '该终端未出库，无法撤销出库库', 'error');
				return ;
			}
			if(row_info(rows[i],"storagestatus")=="2"){
				$.messager.alert('信息提示', '该终端已出库给代理商，请联系总进行操作', 'error');
				return ;
			}
			idArray += id + "-";
		}
		idArray = idArray.substr(0, idArray.length - 1);
		$.messager.confirm('撤销提示', "确定撤销选中的出库信息吗?", function(r) {
			if (r) {
				$.post("../../" + actionName + "/outBack.action?idArray="
						+ idArray, function(data) {
					if (data == 2) {
						$.messager.alert('信息提示', '该终端未出库，无法撤销出库', 'error');
					} else if (data == 1) {
						$.messager.alert('信息提示', '出库撤销成功', 'info');
						$('#PmsPosInfoTab').datagrid('load');
					}
				}, 'json')
			}
		});
	}
}

function selAllPersion() {
	var departmentId = $('#usedepartment').combobox('getValue');
	$.ajax( {
		url : '../../pmsperson/select.action?departmentId=' + departmentId,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$('#usepersion').combobox( {
				valueField : data.model,
				textField : data.model,
				panelHeight : 'auto',// 自动高度适合
				editable : false
			// 不可编辑状态
					});
		}
	});
}

function getDate() {
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1)
			: d.getMonth() + 1;
	var vDay = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
	var sys = vYear + '/' + vMon + '/' + vDay;
	return sys;
}

function add_updateload() {
	$("#Add_update_Div").dialog('open');
	$("#PmsPosOutBackSaveIframe1")[0].src = 'updateload.jsp';
}

function doReset1() {
	$("#add_msg").html('&nbsp;');
	$('#PmsPosInfoBatchInForm input').not('#storagestatus').val('');
	$('#indate').datebox('setValue', getDate());
	$('#manufacturerid').combobox('setValue', '');
	$('#manufacturerid').combobox('setText', '请选择');
	$('#posstatus').combobox('setText', '请选择');
	$('#posstatus').combobox('setValue', '');
	$('#postype').combobox('setText', '请选择');
	$('#postype').combobox('setValue', '');
	$('#posmodel').combobox('setValue', '');
	$('#posmodel').combobox('setText', '请选择');
}

function add_BatchIn1(addFormName, actionName) {
	var lineText = $("#lineText").val();
	var manufacturerid = $("#manufacturerid").combobox('getValue');
	var posmodel = $("#posmodel").combobox('getValue');
	if (manufacturerid == '') {
		$.messager.alert('错误信息!', '厂商名称不能为空!', 'error');
		return false;
	}
	if (posmodel == '') {
		$.messager.alert('错误信息!', '型号不能为空!', 'error');
		return false;
	}
	if (lineText == '') {
		$.messager.alert('错误信息!', '上传文件不能为空!', 'error');
		return false;
	}
	$("#add_msg").html("保存数据中，请稍后...");
	$("#" + addFormName + "").form('submit', {
		url : "../../" + actionName + "/add_batchIn1.action",
		dataType : 'text',
		data : $("#" + addFormName + "").serialize(),
		success : function(data) {
			var newData = $.parseJSON(data);
			if (newData == 3) {
				$.messager.alert('错误信息!', '该终端已入库，请选择其他终端!', 'error');
			} else if (newData == 1) {
				$("#" + addFormName + " input").val('');
				doReset1();
				$("#add_msg").html("批量入库成功,可以继续入库");
			} else {
				$("#add_msg").html("批量入库失败,请查看入库信息是否正确!!!");
			}
		}
	});
}
// 打开审核页面
function reviewOpen(tableName, updateDivName, iframeName, pkName) {
	var rows = $("#" + tableName + "").datagrid("getSelections");
	if (rows.length < 1) {
		$.messager.alert('信息提示', '请选择审核信息!!!', 'error');
	} else if (rows.length > 1) {
		$.messager.alert('信息提示', '只能选择一条审核信息', 'error');
	} else {
		var row = rows[0];// 获取类表中的选中的列
		$("#" + updateDivName + "").dialog('open');
		$("#" + iframeName + "")[0].src = "review.jsp?pkName="
				+ row_info(row, pkName) + "";

	}
}
// 保存审核的信息
function update_review(updateFormName, actionName, flag) {
	$.ajax( {
		type : 'post',
		dataType : 'json',
		url : "../../" + actionName + "/updateStatus.action?status=" + flag,
		data : $("#" + updateFormName + "").serialize(),
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
			if (data == 1) {
				// 重置所有文本框的值
		$("#update_msg").html("审核完毕,可以继续操作");
	} else {
		$("#update_msg").html("审核失败,请查看修改的信息是否正确!!!");
	}
}
	});
}
// 添加的公用方法
function add_saveApplica(addFormName, actionName) {
	$("#add_msg").html("保存数据中，请稍后...");
	$("#" + addFormName + "").form('submit', {
		url : "../../" + actionName + "/save.action",
		dataType : 'text',
		data : $("#" + addFormName + "").serialize(),
		success : function(data) {
			var newData = $.parseJSON(data);
			if (newData == 1) {
				$("#" + addFormName + " input").val('');
				$("#add_msg").html("添加成功,可以继续添加");
			} else {
				$("#add_msg").html("添加失败,请查看添加信息是否正确!!!");
			}
		}
	});
}
// 添加的公用方法
function add_saveapp(addFormName, actionName) {
	var manufacturerid = $("#manufacturerid").combobox('getValue');
	var modelid = $("#modelid").combobox('getValue');
	var appid = $("#appid").combobox('getValue');
	if (manufacturerid == '') {
		$.messager.alert('错误信息!', '厂商不能为空!', 'error');
		return false;
	}
	if (modelid == '') {
		$.messager.alert('错误信息!', '型号不能为空!', 'error');
		return false;
	}
	if (appid == '') {
		$.messager.alert('错误信息!', '版本不能为空!', 'error');
		return false;
	}
	$("#add_msg").html("保存数据中，请稍后...");
	$("#" + addFormName + "").form('submit', {
		url : "../../" + actionName + "/save.action",
		dataType : 'text',
		data : $("#" + addFormName + "").serialize(),
		success : function(data) {
			var newData = $.parseJSON(data);
			if (newData == 1) {
				$("#" + addFormName + " input").val('');
				$("#add_msg").html("添加成功,可以继续添加");
			} else if (newData == 2) {
				$.messager.alert('信息提示', '该版本已存在，无法添加！！', 'error');
			} else {
				$("#add_msg").html("添加失败,请查看添加信息是否正确!!!");
			}
		}
	});
}
// 打开添加界面（window界面）
function saveOpenAPP(divName, iframeName) {
	$("#" + divName + "").dialog('open');
	$("#" + iframeName + "")[0].src = 'save.jsp';
}

// 打开审核页面
function appUpdateOpen(tableName, updateDivName, iframeName, pkName) {
	var rows = $("#" + tableName + "").datagrid("getSelections");
	if (rows.length < 1) {
		$.messager.alert('信息提示', '请选择审核信息!!!', 'error');
	} else if (rows.length > 1) {
		$.messager.alert('信息提示', '只能选择一条审核信息', 'error');
	} else {
		var row = rows[0];// 获取类表中的选中的列
		$("#" + updateDivName + "").dialog('open');
		$("#" + iframeName + "")[0].src = "review.jsp?pkName="
				+ row_info(row, pkName) + "";

	}
}
