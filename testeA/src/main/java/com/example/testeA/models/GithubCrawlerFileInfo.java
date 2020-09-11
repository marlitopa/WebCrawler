package com.example.testeA.models;

public class GithubCrawlerFileInfo {

	private String url;
	private String lineNumber;
	private String size;
	private String sizeUnity;
	private float byteSize = 0;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeUnity() {
		return sizeUnity;
	}

	public void setSizeUnity(String sizeUnity) {
		this.sizeUnity = sizeUnity;
	}

	public float getByteSize() {
		return byteSize;
	}

	public void setByteSize(float byteSize) {
		this.byteSize = byteSize;
	}

}
