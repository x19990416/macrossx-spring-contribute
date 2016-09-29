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
package com.github.x19990416.macrossx.spring.wechat.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.wechat.IWechatHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatAccessToken;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;

import lombok.extern.java.Log;

@Log
@Component
public class WechatHelper implements IWechatHelper {
	@Value("#{applicationProperties['macrossx.wechat.appid']}")
	private String appid;
	@Value("#{applicationProperties['macrossx.wechat.appsecret']}")
	private String appsecret;

	private static WechatAccessToken _access_token;
	private static long _token_time = -1;

	public Optional<WechatAccessToken> getAccessToken() {
		try {
			if (System.currentTimeMillis() < _token_time) {
				return Optional.of(_access_token);
			}
			long current = System.currentTimeMillis();
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(new URI(MessageFormat.format(WechatConstants.ACCESS_TOKEN_URL, appid, appsecret)));
			return new WechatHttpClient().send(httpGet, WechatAccessToken.class).map((e) -> {
				_access_token = e;
				_token_time = current + _access_token.getExpires_in() - 60;
				return _access_token;
			});
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
			return Optional.empty();
		}

	}

}
