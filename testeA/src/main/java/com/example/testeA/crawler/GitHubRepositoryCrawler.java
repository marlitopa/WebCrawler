package com.example.testeA.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.example.testeA.http.HttpClient;
import com.example.testeA.models.GithubCrawlerFileInfo;
import com.example.testeA.text.LinkExtractor;

public class GitHubRepositoryCrawler {

	private ArrayList<String> allRepositoryDirectorys = new ArrayList<String>();
	ArrayList<GithubCrawlerFileInfo> allRepositoryLinks = new ArrayList<GithubCrawlerFileInfo>();

	public String extractRepositoryNameFromUrl(String url) {
		String repositoryName = "";
		repositoryName = url.replace("https://github.com", "");
		if (!repositoryName.endsWith("/")) {
			repositoryName = repositoryName + "/";
		}
		return repositoryName;
	}

	public void extractAllFileLinks(String url, String repositoryName) throws MalformedURLException, IOException {

		allRepositoryDirectorys.add(url);

		HttpClient httpClient = new HttpClient();
		String html = httpClient.load(url);

		LinkExtractor linkExtractor = new LinkExtractor();
		ArrayList<String> allPageLinks = linkExtractor.processText(html);

		allPageLinks.forEach((item) -> {
			if (item.startsWith(repositoryName + "blob/master")) {
				GithubCrawlerFileInfo githubCrawlerFileInfo = new GithubCrawlerFileInfo();
				githubCrawlerFileInfo.setUrl("https://github.com" + item);
				try {
					this.fillInfo(githubCrawlerFileInfo);
				} catch (Throwable ex) {
					// Erro de IO
				}
				allRepositoryLinks.add(githubCrawlerFileInfo);
			} else if (item.startsWith(repositoryName + "tree/master")
					&& !(allRepositoryDirectorys.contains("https://github.com" + item))) {
				try {
					this.extractAllFileLinks("https://github.com" + item, repositoryName);
				} catch (Throwable ex) {
					// Erro de IO
				}
			}
		});

	}

	private void fillInfo(GithubCrawlerFileInfo githubCrawlerFileInfo) throws MalformedURLException, IOException {
		HttpClient httpClient = new HttpClient();
		String html = httpClient.load(githubCrawlerFileInfo.getUrl());
		String[] htmlArray = html.split("<span class=\"file-info-divider\"></span>");
		String[] htmlLinesArray = htmlArray[0].split(">");
		String[] htmlSizeArray = htmlArray[1].split("<");

		String htmlLineNumber = htmlLinesArray[htmlLinesArray.length - 1];
		String lineNumber = htmlLineNumber.split("lines")[0].trim();

		String size = htmlSizeArray[0].trim().split(" ")[0];
		String sizeUnity = htmlSizeArray[0].trim().split(" ")[1];

		githubCrawlerFileInfo.setLineNumber(lineNumber);
		githubCrawlerFileInfo.setSize(size);
		githubCrawlerFileInfo.setSizeUnity(sizeUnity);
	}

	public ArrayList<GithubCrawlerFileInfo> getAllRepositoryLinks() {
		return allRepositoryLinks;
	}

}
