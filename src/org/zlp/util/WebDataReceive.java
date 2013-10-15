package org.zlp.util;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created with eclipse
 * 
 * @Description: 获取网页数据
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-11
 * @Time: 上午10:56:37
 * 
 */
public enum WebDataReceive {

	HTTPS {
		@Override
		public String receiveData(String urlPath, String streamData) {
			StringBuilder responseStringBuilder = new StringBuilder();
			URL url = null;
			URLConnection urlConnection = null;
			Scanner scanner = null;
			try {
				url = new URL(urlPath);
				urlConnection = url.openConnection();
				HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) urlConnection;
				httpsUrlConnection.setDoOutput(true);
				httpsUrlConnection.setDoInput(true);
				httpsUrlConnection.setUseCaches(false);
				httpsUrlConnection.setRequestProperty("Content-Type",
						"application/json;charset=UTF-8");
				httpsUrlConnection.setRequestMethod("POST");
				httpsUrlConnection.connect();
				if (streamData != null) {
					OutputStream outputStream = httpsUrlConnection.getOutputStream();
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,
							"UTF-8");
					outputStreamWriter.write(streamData);
					outputStreamWriter.flush();
					outputStreamWriter.close();
				}
				scanner = new Scanner(urlConnection.getInputStream(), "UTF-8");
				while (scanner.hasNext()) {
					String tempString = scanner.nextLine().trim();
					responseStringBuilder.append(tempString);
				}
				scanner.close();
			} catch (Exception e) {
				return null;
			}
			return responseStringBuilder.toString();
		}
	};

	/**
	 * 获取网页数据
	 * 
	 * @param urlPath
	 *            路劲
	 * @param streamData
	 *            POST数据(流方式)
	 * @return
	 */
	public abstract String receiveData(String urlPath, String streamData);

}