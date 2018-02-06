package ky.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelOutput {
	public boolean exportExcel(HttpServletResponse response,List dataList, List<Map<String, String>> map) {
		try {
			response.setCharacterEncoding("utf-8");
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition","attachment; filename=fine.xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
			String tmptitle = "道诺信息表"; // 标题
			WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称
			// 设置excel标题
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcfFC = new WritableCellFormat(wfont);
			wcfFC.setBackground(Colour.AQUA);
			wsheet.addCell(new Label(1, 0, tmptitle, wcfFC));
			wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			wcfFC = new WritableCellFormat(wfont);
			// 开始生成主体内容
			for(int i=0;i<map.size();i++){
				Map<String, String> m=map.get(i);
				Iterator<String> it = m.keySet().iterator();
				while(it.hasNext()){
					String paramName = it.next();
					//跳过的列
	                if(paramName.equals("hiddenTrans")){
	                	continue;
	                }
					wsheet.addCell(new Label(i, 2,m.get(paramName)));
				}
			}
			for (int j = 0; j < dataList.size(); j++) {
				Object obj = dataList.get(j);
				for(int i=0;i<map.size();i++){
					Map<String, String> m=map.get(i);
					Iterator<String> it = m.keySet().iterator();
					while(it.hasNext()){
						String paramName = it.next();
						//跳过的列
		                if(paramName.equals("hiddenTrans")){
		                	continue;
		                }
//		                if(paramName.equals("bz")){
//		                	continue;
//		                }
						Field f = obj.getClass().getDeclaredField(paramName);
						f.setAccessible(true);
		                Object o = f.get(obj);
		                if(o==null){
		                	o="";
		                }
		                //需要格式化的列
		                if(paramName.equals("transamt")){
							wsheet.addCell(new jxl.write.Number(i, j+3,Double.parseDouble(o.toString())));
							continue;
						}
		                if(paramName.equals("sumtransamt")){
							wsheet.addCell(new jxl.write.Number(i, j+3,Double.parseDouble(o.toString())));
							continue;
						}
		                if(paramName.equals("maxmoney")){
							wsheet.addCell(new jxl.write.Number(i, j+3,Double.parseDouble(o.toString())));
							continue;
						}
		                if(paramName.equals("minmoney")){
							wsheet.addCell(new jxl.write.Number(i, j+3,Double.parseDouble(o.toString())));
							continue;
						}
		                if(paramName.equals("cantransamt")){
							wsheet.addCell(new jxl.write.Number(i, j+3,Double.parseDouble(o.toString())));
							continue;
						}
		                
		                /*if(paramName.equals("channelno")){
		                	String original=o.toString();
		                	String val;
		                	if(original.length()==2)
		                		val = o.toString();
		                	else
		                		val=o.toString().substring(2, 4);
		            		if(val.equals("00")){val="成功";}
		            		else if(val.equals("01")){val="("+original+")查发卡行";}
		            		else if(val.equals("02")){val="("+original+")查发卡行的特殊条件";}
		            		else if(val.equals("03")){val="("+original+")无效商户";}
		            		else if(val.equals("04")){val="("+original+")没收卡";}
		            		else if(val.equals("05")){val="("+original+")不予承兑";}
		            		else if(val.equals("06")){val="("+original+")出错";}
		            		else if(val.equals("07")){val="("+original+")特殊条件下没收卡";}
		            		else if(val.equals("09")){val="("+original+")POS终端号找不到";}
		            		else if(val.equals("09")){val="("+original+")无效POS终端";}
		            		else if(val.equals("12")){val="("+original+")无效交易";}
		            		else if(val.equals("13")){val="("+original+")无效金额";}
		            		else if(val.equals("14")){val="("+original+")无效卡号";}
		            		else if(val.equals("15")){val="("+original+")无此发卡行";}
		            		else if(val.equals("19")){val="("+original+")重新送入交易";}
		            		else if(val.equals("20")){val="("+original+")无效应答";}
		            		else if(val.equals("21")){val="("+original+")不做任何处理";}
		            		else if(val.equals("22")){val="("+original+")怀疑操作有误";}
		            		else if(val.equals("23")){val="("+original+")不可接受的交易费";}
		            		else if(val.equals("25")){val="("+original+")无源交易，请联系发卡行";}
		            		else if(val.equals("31")){val="("+original+")银联不支持的银行";}
		            		else if(val.equals("33")){val="("+original+")过期的卡";}
		            		else if(val.equals("34")){val="("+original+")有作弊嫌疑";}
		            		else if(val.equals("35")){val="("+original+")受卡方与安全保密部门联系";}
		            		else if(val.equals("36")){val="("+original+")受限制的卡";}
		            		else if(val.equals("37")){val="("+original+")受卡方呼受理方安全保密部门(没收卡)";}
		            		else if(val.equals("38")){val="("+original+")超过允许的PIN试输入";}
		            		else if(val.equals("39")){val="("+original+")无此信用卡帐户";}
		            		else if(val.equals("40")){val="("+original+")请求的功能尚不支持";}
		            		else if(val.equals("41")){val="("+original+")丢失卡";}
		            		else if(val.equals("42")){val="("+original+")无此帐户";}
		            		else if(val.equals("43")){val="("+original+")被窃卡";}
		            		else if(val.equals("44")){val="("+original+")无此投资帐户";}
		            		else if(val.equals("50")){val="("+original+")PIN格式错";}
		            		else if(val.equals("51")){val="("+original+")无足够的存款";}
		            		else if(val.equals("52")){val="("+original+")无此支票账户";}
		            		else if(val.equals("53")){val="("+original+")无此储蓄卡账户";}
		            		else if(val.equals("54")){val="("+original+")过期的卡";}
		            		else if(val.equals("55")){val="("+original+")不正确的PIN";}
		            		else if(val.equals("56")){val="("+original+")无此卡记录";}
		            		else if(val.equals("57")){val="("+original+")不允许持卡人进行的交易";}
		            		else if(val.equals("58")){val="("+original+")不允许终端进行的交易";}
		            		else if(val.equals("59")){val="("+original+")有作弊嫌疑";}
		            		else if(val.equals("60")){val="("+original+")受卡方与安全保密部门联系";}
		            		else if(val.equals("61")){val="("+original+")超出取款金额限制";}
		            		else if(val.equals("62")){val="("+original+")受限制的卡";}
		            		else if(val.equals("63")){val="("+original+")违反安全保密规定";}
		            		else if(val.equals("64")){val="("+original+")原始金额不正确";}
		            		else if(val.equals("65")){val="("+original+")超出取款次数限制";}
		            		else if(val.equals("66")){val="("+original+")受卡方呼受理方安全保密部门";}
		            		else if(val.equals("67")){val="("+original+")捕捉（没收卡）";}
		            		else if(val.equals("68")){val="("+original+")交易违法、不能完成";}
		            		else if(val.equals("69")){val="("+original+")需要向网络中心签到";}
		            		else if(val.equals("70")){val="("+original+")数据库操作失败";}
		            		else if(val.equals("71")){val="("+original+")接收数据空";}
		            		else if(val.equals("72")){val="("+original+")PIN转换失败";}
		            		else if(val.equals("73")){val="("+original+")mac生成失败";}
		            		else if(val.equals("74")){val="("+original+")终端mac校验失败";}
		            		else if(val.equals("75")){val="("+original+")发送数据失败";}
		            		else if(val.equals("76")){val="("+original+")接收数据失败(原则上调整到，98)";}
		            		else if(val.equals("77")){val="("+original+")解包出错";}
		            		else if(val.equals("78")){val="("+original+")打包出错";}
		            		else if(val.equals("79")){val="("+original+")脱机交易对帐不平";}
		            		else if(val.equals("79")){val="("+original+")脱机交易对帐不平";}
		            		else if(val.equals("81")){val="("+original+")用户状态不合法--未签到";}
		            		else if(val.equals("82")){val="("+original+")终端状态不合法";}
		            		else if(val.equals("83")){val="("+original+")原交易不存在或异常（由12 和 25替代）";}
		            		else if(val.equals("84")){val="("+original+")受限服务号";}
		            		else if(val.equals("85")){val="("+original+")与原交易的金额不一致";}
		            		else if(val.equals("86")){val="("+original+")当批次金额已超限,请结算后再试";}
		            		else if(val.equals("87")){val="("+original+")通道错误（关闭）";}
		            		else if(val.equals("88")){val="("+original+")路由错误";}
		            		else if(val.equals("89")){val="("+original+")终端需签到";}
		            		else if(val.equals("90")){val="("+original+")程序需更新";}
		            		else if(val.equals("91")){val="("+original+")参数需更新";}
		            		else if(val.equals("93")){val="("+original+")需下载KEK";}
		            		else if(val.equals("94")){val="("+original+")不支持的业务";}
		            		else if(val.equals("95")){val="("+original+")机构停用";}
		            		else if(val.equals("96")){val="("+original+")交易失败,请联系收单方";}
		            		else if(val.equals("97")){val="("+original+")第三方返回错误";}
		            		else if(val.equals("98")){val="("+original+")银联收不到发卡行应答(超时或者收到未知)";}
		            		else if(val.equals("99")){val="("+original+")未知错误";}
		            		else
		            			val="("+original+")其它错误";
		            		wsheet.addCell(new Label(i, j+3,val));
		            		continue;
		                }*/
						wsheet.addCell(new Label(i, j+3,o.toString()));
					}
				}
			}
			// 主体内容生成结束
			wbook.write(); // 写入文件
			wbook.close();
			os.close(); // 关闭流
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
