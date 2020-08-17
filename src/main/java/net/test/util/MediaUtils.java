package net.test.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	private static Map<String, org.springframework.http.MediaType> mediaMap;
	
	static {
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", org.springframework.http.MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", org.springframework.http.MediaType.IMAGE_GIF);
		mediaMap.put("PNG", org.springframework.http.MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}

}
