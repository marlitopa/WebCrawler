package com.example.testeA.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import com.example.testeA.service.dto.GitHubDTO;

public interface GithubCrawlerService {

	public GitHubDTO fetchGitHubRepository(String repositoryUrl) throws MalformedURLException, IOException;

}
