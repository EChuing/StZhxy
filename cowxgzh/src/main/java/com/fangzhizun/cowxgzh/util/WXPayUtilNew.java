package com.fangzhizun.cowxgzh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WXPayUtilNew {

		public static String post(String reqmap, String strUrl) {

			URL url = null;
			HttpURLConnection connection = null;
			BufferedReader breader = null;
			StringBuffer strb = new StringBuffer();

			try {
				url = new URL(strUrl);
				// url = new URL(strUrl);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestProperty("Content-Type", "text/html");
				connection.connect();

				strb = new StringBuffer();
				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream(), "UTF-8");
				writer.write(reqmap);
				writer.flush();
				writer.close();

				breader = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "UTF-8"));
				String str;
				while ((str = breader.readLine()) != null) {
					strb.append(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				breader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			connection.disconnect();
			return strb.toString();
		}
}
