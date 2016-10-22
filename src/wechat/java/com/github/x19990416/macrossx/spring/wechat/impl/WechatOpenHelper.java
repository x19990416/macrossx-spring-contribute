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

import com.github.x19990416.macrossx.spring.wechat.IWechatOpenHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatOpenAccessToken;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatResponseObj;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;

import lombok.extern.java.Log;

@Log
@Component 
public class WechatOpenHelper implements IWechatOpenHelper {
	@Value("#{applicationProperties['macrossx.wechat.open.appid']}")
	private String appid;
	@Value("#{applicationProperties['macrossx.wechat.open.appsecret']}")
	private String appsecret;

	public Optional<WechatOpenAccessToken> getAccessToken(String code) {
		try {
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(
					new URI(MessageFormat.format(WechatConstants.OPEN_ACCESS_TOKEN_URL, appid, appsecret, code)));
			return new WechatHttpClient().send(httpGet, WechatOpenAccessToken.class);
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
			return Optional.empty();
		}
	}

	public Optional<WechatOpenAccessToken> refreshAccessToken(String refreshToken) {
		try {
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(
					new URI(MessageFormat.format(WechatConstants.OPEN_ACCESS_TOKEN_REFRESH_URL, appid, refreshToken)));
			return new WechatHttpClient().send(httpGet, WechatOpenAccessToken.class);
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
			return Optional.empty();
		}
	}

	public boolean checkAccessToken(String token, String openid) {
		try {
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(new URI(MessageFormat.format(WechatConstants.OPEN_ACCESS_TOKEN_CHECK_URL, token, openid)));
			Optional<WechatResponseObj> WechatResponseObj = new WechatHttpClient().send(httpGet,
					WechatResponseObj.class);

			if (WechatResponseObj.isPresent()) {
				return WechatResponseObj.get().getErrcode() == 0;
			} else
				return false;
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
			return false;
		}
	}

}
