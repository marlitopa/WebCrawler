package com.example.testeA.service.dto;

import java.util.ArrayList;
import java.util.List;

public class GitHubGroupDTO {

	String extension;
	int lines;
	float bytes;

	private List<GitHubFileDTO> files = new ArrayList<GitHubFileDTO>();

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

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

	public List<GitHubFileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<GitHubFileDTO> files) {
		this.files = files;
	}

}
