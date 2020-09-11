package com.example.testeA.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.testeA.crawler.GitHubRepositoryCrawler;
import com.example.testeA.models.GithubCrawlerFileInfo;
import com.example.testeA.service.dto.GitHubDTO;
import com.example.testeA.service.dto.GitHubFileDTO;
import com.example.testeA.service.dto.GitHubGroupDTO;
import com.example.testeA.unityConverter.UnityConverter;

@Service

public class GithubCrawlerServiceImpl implements GithubCrawlerService {

	public GitHubDTO fetchGitHubRepository(String repositoryUrl) throws MalformedURLException, IOException {
		GitHubDTO gitHubDTO = new GitHubDTO();

		GitHubRepositoryCrawler gitHubRepositoryCrawler = new GitHubRepositoryCrawler();

		String url = repositoryUrl;
		String repositoryName = gitHubRepositoryCrawler.extractRepositoryNameFromUrl(url);

		gitHubRepositoryCrawler.extractAllFileLinks(url, repositoryName);
		ArrayList<GithubCrawlerFileInfo> allRepositoryLinks = gitHubRepositoryCrawler.getAllRepositoryLinks();

		allRepositoryLinks.sort((GithubCrawlerFileInfo o1, GithubCrawlerFileInfo o2) -> {

			StringBuilder stringBuilder1 = new StringBuilder();
			stringBuilder1.append(o1.getUrl());

			StringBuilder stringBuilder2 = new StringBuilder();
			stringBuilder2.append(o2.getUrl());

			String campo1 = stringBuilder1.reverse().toString();
			String campo2 = stringBuilder2.reverse().toString();

			return campo1.compareTo(campo2);

		});

		String fileExtensionGroup = "";
		float bytes = 0;
		int lines = 0;
		float groupBytes = 0;
		int groupLines = 0;

		GitHubGroupDTO gitHubGroupDTO = null;

		for (int i = 0; i < allRepositoryLinks.size(); i++) {
			UnityConverter unityConverter = new UnityConverter();
			GithubCrawlerFileInfo link = allRepositoryLinks.get(i);
			link.setByteSize(unityConverter.converToBytes(link.getSize(), link.getSizeUnity()));

			String[] linkArray = link.getUrl().split("\\.");
			String fileExtension = linkArray[linkArray.length - 1];

			if (!(fileExtensionGroup.equals(fileExtension))) {

				if (gitHubGroupDTO != null) {
					gitHubGroupDTO.setBytes(groupBytes);
					gitHubGroupDTO.setLines(groupLines);
					gitHubDTO.getGroups().add(gitHubGroupDTO);
				}

				gitHubGroupDTO = new GitHubGroupDTO();

				groupBytes = 0;
				groupLines = 0;

				fileExtensionGroup = fileExtension;

				gitHubGroupDTO.setExtension(fileExtension);
			}

			try {
				bytes += link.getByteSize();
				lines += Integer.parseInt(link.getLineNumber());
				groupBytes += link.getByteSize();
				groupLines += Integer.parseInt(link.getLineNumber());
			} catch (Throwable ex) {

			}

			GitHubFileDTO gitHubFileDTO = new GitHubFileDTO();

			gitHubFileDTO.setFile(link.getUrl());
			gitHubFileDTO.setExtension(fileExtensionGroup);
			gitHubFileDTO.setBytes(link.getByteSize());
			try {

				gitHubFileDTO.setLines(Integer.parseInt(link.getLineNumber()));

			} catch (Throwable ex) {

			}

			gitHubGroupDTO.getFiles().add(gitHubFileDTO);
		}

		gitHubGroupDTO.setBytes(groupBytes);
		gitHubGroupDTO.setLines(groupLines);

		gitHubDTO.setBytes(bytes);
		gitHubDTO.setLines(lines);

		return gitHubDTO;
	}

}
