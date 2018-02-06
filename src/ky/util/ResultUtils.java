package ky.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultUtils {
	private String result;
	private String msg;
	private Object data;
	
	public ResultUtils(){
		
	}
	
	public ResultUtils(String result, String msg, Object data){
		this.result = result;
		this.msg = msg;
		this.data = data;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static void main(String[] args){
		ResultUtils r = new ResultUtils();
		r.setResult("SUCCESS");
		r.setMsg("注册成功");
		Map<String, String> m = new HashMap<String, String>();
		m.put("111", "rrr");
		m.put("112", "zzz");
		List<Object> data = new ArrayList<>();
		data.add(m);
		
		r.setData(data);
		
		System.out.println(JSonUtils.toJSon(r));
		
	}
}

/*
 * Location: C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\ Qualified
 * Name: ky.util.PageView JD-Core Version: 0.6.0
 */