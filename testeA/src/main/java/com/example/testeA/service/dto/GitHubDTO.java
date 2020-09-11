package com.example.testeA.service.dto;

import java.util.ArrayList;
import java.util.List;

public class GitHubDTO {

	int lines;
	float bytes;

	private List<GitHubGroupDTO> groups = new ArrayList<GitHubGroupDTO>();

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public float getBytes() {
		return bytes;
	}

	public void setBytes(float bytes) {
		this.bytes = bytes;
	}

	public List<GitHubGroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GitHubGroupDTO> groups) {
		this.groups = groups;
	}
	
	

}
