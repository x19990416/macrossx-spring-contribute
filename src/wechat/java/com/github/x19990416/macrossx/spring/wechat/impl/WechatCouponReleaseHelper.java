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

import com.github.x19990416.macrossx.spring.wechat.IWechatCouponReleaseHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatAccessToken;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCouponCreateQRCodeReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCouponCreateQRCodeRespObj;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatCouponReleaseHelper implements IWechatCouponReleaseHelper{
	@Autowired
	private IWechatHelper wechatHelper;

	public Optional<WechatCouponCreateQRCodeRespObj> createQrCode(WechatCouponCreateQRCodeReqObj coupon) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.CARD_RELEASE_CREATE, accessToken.getAccess_token())));
			httpPost.setEntity(new StringEntity(new Gson().toJson(coupon),"UTF-8"));
			return new WechatHttpClient().send(httpPost, WechatCouponCreateQRCodeRespObj.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}
	}
}
