package com.github.x19990416.macrossx.spring.component.remotefile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpRemoteFileService implements IRemoteFileService {
	private String baseUrl;
	private HttpClient httpClient;

	public HttpRemoteFileService(String baseUrl, int maxPool, int timeOutMs) {
		this.baseUrl = baseUrl;

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(maxPool);
		cm.setDefaultMaxPerRoute(maxPool);

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeOutMs)
				.setConnectTimeout(timeOutMs).setSocketTimeout(timeOutMs)
				.build();

		httpClient = HttpClients.custom().setConnectionManager(cm)
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
				.setDefaultRequestConfig(requestConfig).build();
	}

	@Override
	public boolean save(InputStream in, String subUrl, String name) {
		HttpRequestBase request = createCreateReq(in, subUrl, name);
		int statusCode = -1;

		try {
			HttpResponse httpResponse = httpClient.execute(request);
			statusCode = httpResponse.getStatusLine().getStatusCode();
		} catch (IOException e) {
			request.abort();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return isSuccess(statusCode);
	}

	@Override
	public boolean remove(String subUrl, String name) {
		HttpRequestBase request = createRemoveReq(subUrl, name);
		int statusCode = -1;

		try {
			HttpResponse httpResponse = httpClient.execute(request);
			statusCode = httpResponse.getStatusLine().getStatusCode();
		} catch (IOException e) {
			request.abort();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return isSuccess(statusCode);
	}

	@Override
	public List<String> list(String subUrl) {
		throw new UnsupportedOperationException();
	}

	protected HttpRequestBase createCreateReq(InputStream in, String subUrl,
			String name) {
		String fullUrl = combineUrl(combineUrl(baseUrl, subUrl), name);
		HttpPut request = new HttpPut(fullUrl);
		request.setEntity(new InputStreamEntity(in));

		return request;
	}

	protected HttpRequestBase createRemoveReq(String subUrl, String name) {
		String fullUrl = combineUrl(combineUrl(baseUrl, subUrl), name);
		HttpDelete request = new HttpDelete(fullUrl);

		return request;
	}

	private static boolean isSuccess(int code) {
		return ((200 <= code) && (code <= 299));
	}
}
