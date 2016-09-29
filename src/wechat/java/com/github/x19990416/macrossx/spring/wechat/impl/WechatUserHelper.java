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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.wechat.IWechatHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatUserHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatAccessToken;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatUserGet;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatUserInfo;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;

import lombok.extern.java.Log;

@Log
@Component
public class WechatUserHelper implements IWechatUserHelper {
	@Autowired
	private IWechatHelper helper;

	public Optional<WechatUserGet> userGet(String nextOpenid) {
		try {
			Optional<WechatAccessToken> token = helper.getAccessToken();
			if (token.isPresent()) {
				WechatAccessToken accessToken = token.get();
				HttpGet httpGet = new HttpGet();

				httpGet.setURI(new URI(MessageFormat.format(WechatConstants.USER_GET_URL, accessToken.getAccess_token(),
						nextOpenid == null ? "" : nextOpenid)));
				return new WechatHttpClient().send(httpGet, WechatUserGet.class);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return Optional.empty();
	}
	
	public Optional<WechatUserInfo> userInfo(String openid) {
		try {
			Optional<WechatAccessToken> token = helper.getAccessToken();
			if (token.isPresent()) {
				WechatAccessToken accessToken = token.get();
				HttpGet httpGet = new HttpGet();

				httpGet.setURI(new URI(MessageFormat.format(WechatConstants.USER_INFO_URL, accessToken.getAccess_token(),
						openid)));
				return new WechatHttpClient().send(httpGet, WechatUserInfo.class);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return Optional.empty();
	}
}
