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
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.wechat.IWechatOpenUserHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatOpenUserInfo;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;

import lombok.extern.java.Log;

@Log
@Component
public class WechatOpenUserHelper implements IWechatOpenUserHelper{
	public Optional<WechatOpenUserInfo> userInfo(String accessToken,String openid) {
		try {
				HttpGet httpGet = new HttpGet();
				httpGet.setURI(new URI(MessageFormat.format(WechatConstants.OPEN_USER_INFO_URL, openid,
						accessToken	)));
				return new WechatHttpClient().send(httpGet, WechatOpenUserInfo.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return Optional.empty();
	}
}
