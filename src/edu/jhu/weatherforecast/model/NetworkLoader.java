package edu.jhu.weatherforecast.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkLoader {

	private final static String BASE_URL = "http://weather.yahooapis.com/forecastrss?w=";

	public static String downloadUrl(City city) throws IOException {
		URL url = new URL(BASE_URL + city.getWOEID());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		// Starts the query
		conn.connect();
		InputStream in = conn.getInputStream();

		return getStringFromInputStream(in);
	}

	private static String getStringFromInputStream(InputStream inputStream) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
