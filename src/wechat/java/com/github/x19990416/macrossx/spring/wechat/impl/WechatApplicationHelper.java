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

import com.github.x19990416.macrossx.spring.wechat.IWechatApplicationHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatApplicationEntryDataUserInfo;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatApplicationSessionKey;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatApplicationHelper implements IWechatApplicationHelper {
	@Value("#{applicationProperties['macrossx.wechat.application.appid']}")
	private String appid;
	@Value("#{applicationProperties['macrossx.wechat.application.appsecret']}")
	private String appsecret;

	public Optional<WechatApplicationSessionKey> getSessionKey(String code) {
		try {
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(
					new URI(MessageFormat.format(WechatConstants.APPLICATION_SESSION_KEY, appid, appsecret, code)));
			return new WechatHttpClient().send(httpGet, WechatApplicationSessionKey.class);
		} catch (URISyntaxException e) {
			log.error("{0}", e);
			return Optional.empty();
		}
	}

	@Override
	public Optional<WechatApplicationEntryDataUserInfo> getUserInfo(String contentData, String iv, String sessionKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<WechatApplicationEntryDataUserInfo> getUserInfo(String contentData, String iv,
			WechatApplicationSessionKey sessionKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
