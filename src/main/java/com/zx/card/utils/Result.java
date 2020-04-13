package com.zx.card.utils;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Result extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public Result() {
		put("code", 0);
		put("msg", "操作成功");
	}

	public static Result error() {
		return error(1, "操作失败");
	}

	public static Result error(String msg) {
		return error(500, msg);
	}

	public static Result error(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static Result ok(String msg) {
		Result r = new Result();
		r.put("msg", msg);
		return r;
	}

	public static Result ok(String msg,Object obj) {
		Result r = new Result();
		r.put("msg", msg);
		r.put("data",obj);
		return r;
	}

	public static Result ok(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return r;
	}

	public static Result ok() {
		return new Result();
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static String md32formd5(String s){
		char hexDigits[] = {'0','1', '2', '3','4', '5','6','7','8','9','a', 'b','c','d','e','f'};

		try {
			byte[] strTemp = s.getBytes("UTF-8");
			//byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				//右移四位并截掉高四位
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				//直接截掉高四位
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;


	}

	public static String getSignature(HashMap<String, String> params, String dev_server_secret){
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, String> sortedParams = new TreeMap<String, String>(params);

		Set<Entry<String, String>> entrys = sortedParams.entrySet();
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder basestring = new StringBuilder(dev_server_secret);
		for (Entry<String, String> param : entrys) {
			basestring.append(param.getKey()).append(param.getValue());
		}
		basestring.append(dev_server_secret);
		// 使用MD5对待签名串求签
		return  md32formd5(basestring.toString()).toUpperCase();
	}

	public static void main(String[] args){
		String s = "kaola.zhuanke.api.queryOrderInfo";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd Hh:mm:ss");
		String stime = "";
		String etime = "";
		try {
			stime = format.parse("2019-10-12 14:40:00").getTime() + "";
			etime = format.parse("2019-10-12 14:50:00").getTime() + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HashMap<String, String> ps = new HashMap<String, String>();
		ps.put("timestamp", "2019-10-12 14:49:00");
		ps.put("v","1.0");
		ps.put("signMethod","md5");
		ps.put("unionId","zhuanke_700858931");
		ps.put("method","kaola.zhuanke.api.queryOrderInfo");
		ps.put("type","1");
		ps.put("startDate",stime);
		ps.put("endDate",etime);
		String sign = getSignature(ps,"d8f665b6-03f7-439f-b8f3-e7f049336c6f");
		System.out.println(sign);
	}

}
