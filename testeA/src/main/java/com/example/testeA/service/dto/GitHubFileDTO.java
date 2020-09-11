package com.example.testeA.service.dto;

public class GitHubFileDTO {
	String extension;
	int lines;
	float bytes;
	String file;

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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
