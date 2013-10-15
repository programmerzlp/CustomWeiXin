package org.zlp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created with eclipse
 * 
 * @Description: JSON处理工具
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-12
 * @Time: 上午9:58:47
 * 
 */
public enum JsonDeal {

	INSTANCE {
		@Override
		public synchronized String jsonStr2OneLine(String jsonStr) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(jsonStr);
			return m.replaceAll("");
		}

		@Override
		public synchronized String jsonStr2PrettyLine(String jsonStr) {
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(jsonStr);
			String prettyJsonString = prettyGson.toJson(je);
			return prettyJsonString;
		}

		@Override
		public synchronized <T> T jsonStr2Obj(String jsonStr, Class<T> clazz) {
			return prettyGson.fromJson(jsonStr, clazz);
		}

		@Override
		public String jsonObj2PrettyLine(Object json) {
			return prettyGson.toJson(json);
		}
	};

	protected Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * 格式化的json串转换为一行字符串
	 * 
	 * @param jsonStr
	 * @return
	 */
	public abstract String jsonStr2OneLine(String jsonStr);

	/**
	 * 将没有格式化的json字符串格式化输出
	 * 
	 * @param jsonStr
	 * @return
	 */
	public abstract String jsonStr2PrettyLine(String jsonStr);

	/**
	 * 对象格式化的json字符串格式化输出
	 * 
	 * @param jsonStr
	 * @return
	 */
	public abstract String jsonObj2PrettyLine(Object json);

	/**
	 * json字符串转换成对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public abstract <T> T jsonStr2Obj(String jsonStr, Class<T> clazz);

}