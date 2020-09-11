package com.example.testeA.text;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LinkExtractorTest {

	private static LinkExtractor linkExtractor;

	@BeforeAll
	public static void setup() {
		linkExtractor = new LinkExtractor();
	}

	@Test
	void testProcessText() {
		ArrayList<String> urlList = linkExtractor.processText("<a href='/topics/php'>php</a>");
		assertTrue(urlList.get(0).replace("'", "").startsWith("/topics/php"), "Link URL not found");
		urlList = linkExtractor.processText("<a href='/topics/php'>php</a>");
		assertFalse(urlList.get(0).replace("'", "").startsWith("<a"), "String not filtered");
	}

}
