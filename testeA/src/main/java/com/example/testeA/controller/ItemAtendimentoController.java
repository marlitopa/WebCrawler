package com.example.testeA.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.testeA.service.dto.GitHubDTO;
import com.example.testeA.service.impl.GithubCrawlerService;

@RestController
@RequestMapping("/")
public class ItemAtendimentoController {

	@Autowired
	private GithubCrawlerService githubCrawlerService;

	@CrossOrigin
	@GetMapping("/fetchRepository")
	public GitHubDTO fetchRepository(@RequestParam(value = "repositoryUrl", required = true) String repositoryUrl)
			throws MalformedURLException, IOException {
		return githubCrawlerService.fetchGitHubRepository(repositoryUrl);
	}

}
