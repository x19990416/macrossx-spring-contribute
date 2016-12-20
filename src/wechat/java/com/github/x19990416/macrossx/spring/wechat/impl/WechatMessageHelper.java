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

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.wechat.IWechatHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatMessageHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatMessageTemplateRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatAccessToken;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatMessageTemplate;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;
import com.google.gson.Gson;

import lombok.extern.java.Log;

@Log
@Component
public class WechatMessageHelper implements IWechatMessageHelper {
	@Autowired
	private IWechatHelper wechatHelper;

	@Override
	public Optional<WechatMessageTemplateRespObj> sendTemplate(WechatMessageTemplate template) {
		try {
			Optional<WechatAccessToken> token = wechatHelper.getAccessToken();
			if (token.isPresent()) {
				WechatAccessToken accessToken = token.get();
				HttpPost httpPost = new HttpPost();
				httpPost.setEntity(new StringEntity(new Gson().toJson(template), "utf-8"));
				httpPost.setURI(new URI(MessageFormat.format(WechatConstants.MESSAGE_TEMPLATE_SEND_URL,
						accessToken.getAccess_token())));
				return new WechatHttpClient().send(httpPost,
						WechatMessageTemplateRespObj.class);
			}
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
		}catch(Exception e){
			log.info(e.getMessage());
		}
		return Optional.empty();
	}
}
