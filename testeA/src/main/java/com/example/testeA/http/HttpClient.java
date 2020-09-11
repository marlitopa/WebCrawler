package com.example.testeA.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {

	public String load(String urlText) throws MalformedURLException, IOException {
		StringBuilder content = new StringBuilder();
		BufferedReader in = null;

		URL url = new URL(urlText);
		in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		return content.toString();
	}

}
