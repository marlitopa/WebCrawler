package com.example.testeA.unityConverter;

public class UnityConverter {

	public float converToBytes(String size, String unity) {
		float byteSize = 0;

		if (size == null) {
			size = "0";
		}

		if (unity == null) {
			unity = "";
		}

		switch (unity) {
		case "KB": {
			byteSize = Float.parseFloat(size) * 1024;
			break;
		}
		case "Bytes": {
			byteSize = Float.parseFloat(size);
			break;
		}
		default: {
			break;
		}
		}

		return byteSize;
	}

}
