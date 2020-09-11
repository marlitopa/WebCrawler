package com.example.testeA.text;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class LinkExtractor {

	public ArrayList<String> processText(String html) {
		HashMap<String, String> uniqueLinkUrls = new HashMap();
		ArrayList<String> linkUrls = new ArrayList();

		/*
		 * (?<=href=)["\']?((?:.(?!["\']?\\s+(?:\S+)=|[>"\']))+.)["\']?
		 */

		String patternString = "(?<=href=)[\"\\']?((?:.(?![\"\\']?\\\\s+(?:\\S+)=|[>\"\\']))+.)[\"\\']?";

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(html);

		while (matcher.find()) {
			uniqueLinkUrls.put(matcher.group(), "");
		}

		uniqueLinkUrls.entrySet().forEach((entry) -> {
			linkUrls.add(entry.getKey().replace("\"", ""));
		});

		return linkUrls;
	}

}
