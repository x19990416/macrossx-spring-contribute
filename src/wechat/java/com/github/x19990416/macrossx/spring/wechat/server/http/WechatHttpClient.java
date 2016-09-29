/**
 * Copyright (C) 2016 X-Forever.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.x19990416.macrossx.spring.wechat.server.http;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import lombok.extern.java.Log;

@Log
public class WechatHttpClient {

	public <T> Optional<T> send( final HttpUriRequest request, Class<T> clazz) {
		String result = this.send(request);
		if (result.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(new Gson().fromJson(result, clazz));
		}
	}

	public String send(final HttpUriRequest request) {
		String result = "";
		try {
			CloseableHttpResponse response = HttpClients.createDefault().execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != statusCode) {
				log.info("Failed to get!");
				return "";
			}
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "utf-8");
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return result;
	}

}
