package com.github.x19990416.macrossx.spring.component.remotefile;

import java.io.InputStream;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public interface IRemoteFileService {
	final static String URL_SEPARATOR = "/";

	default boolean save(InputStream in, String name) {
		return save(in, null, name);
	}

	boolean save(InputStream in, String subUrl, String name);

	default byte[] load(String name) {
		return load(null, name);
	}

	byte[] load(String subUrl, String name);

	default boolean remove(String name) {
		return remove(null, name);
	}

	boolean remove(String subUrl, String name);

	default List<String> list() {
		return list(null);
	}

	List<String> list(String subUrl);

	default String combineUrl(String baseUrl, String subUrl) {
		if (StringUtils.isEmpty(subUrl))
			return baseUrl;

		if (baseUrl.endsWith(URL_SEPARATOR))
			return baseUrl + subUrl;
		else
			return baseUrl + URL_SEPARATOR + subUrl;
	}
}
