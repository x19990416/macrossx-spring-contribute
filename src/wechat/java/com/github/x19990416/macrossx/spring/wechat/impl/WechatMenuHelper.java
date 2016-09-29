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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.wechat.IWechatHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatMenuHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatAccessToken;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatButton;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatMenu;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatResponseObj;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import lombok.extern.java.Log;

@Log
@Component
public class WechatMenuHelper implements IWechatMenuHelper {

	@Autowired
	private IWechatHelper wechatHelper;

	/**
	 * create menu
	 * 
	 * @param menu
	 *            menu to create
	 *            {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN}
	 * @return true sucess
	 */
	@Override
	public boolean createMenu(List<WechatButton> menu) {
		try {
			Map<String, List<WechatButton>> menus = Maps.newConcurrentMap();
			menus.put("button", menu);
			Optional<WechatAccessToken> token = wechatHelper.getAccessToken();
			if (token.isPresent()) {
				WechatAccessToken accessToken = token.get();
				HttpPost httpPost = new HttpPost();
				try{
				httpPost.setEntity(new StringEntity(new Gson().toJson(menus), "utf-8"));
				}catch(Exception e){
					
				}
				httpPost.setURI(
						new URI(MessageFormat.format(WechatConstants.MENU_CREATE_URL, accessToken.getAccess_token())));
				Optional<WechatResponseObj> response = new WechatHttpClient().send(httpPost,
						WechatResponseObj.class);
				if (response.isPresent()) {
					WechatResponseObj e = response.get();
					log.info(e.toString());
					if (0 == e.getErrcode()) {
						return true;
					}
				}
			}
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
		}
		return false;
	}

	/**
	 * delete menu
	 * 
	 * @param menu
	 *            menu to create
	 *            {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN}
	 * @return true sucess
	 */
	public boolean deleteMenu() {
		try {
			Optional<WechatAccessToken> token = wechatHelper.getAccessToken();
			if (token.isPresent()) {
				WechatAccessToken accessToken = token.get();

				HttpGet httpGet = new HttpGet();
				httpGet.setURI(
						new URI(MessageFormat.format(WechatConstants.MENU_DELETE_URL, accessToken.getAccess_token())));
				Optional<WechatResponseObj> response = new WechatHttpClient().send(httpGet,
						WechatResponseObj.class);
				if (response.isPresent()) {
					WechatResponseObj e = response.get();
					log.info(e.toString());
					if (0 == e.getErrcode()) {
						return true;
					}
				}
			}
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
		}
		return false;
	}

	@Override
	public Optional<WechatMenu> getMenu() {
		try {
			Optional<WechatAccessToken> token = wechatHelper.getAccessToken();
			if (token.isPresent()) {
				WechatAccessToken accessToken = token.get();

				HttpGet httpGet = new HttpGet();
				httpGet.setURI(
						new URI(MessageFormat.format(WechatConstants.MENU_GET_URL, accessToken.getAccess_token())));
				return new WechatHttpClient().send(httpGet, WechatMenu.class);
			}
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
		}
		return Optional.empty();
	}
}
