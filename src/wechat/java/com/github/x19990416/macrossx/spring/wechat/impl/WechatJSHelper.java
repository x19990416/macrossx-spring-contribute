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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.wechat.IWechatHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatJSHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatAccessToken;

import lombok.extern.java.Log;

@Log
@Component
public class WechatJSHelper implements IWechatJSHelper {
	@Autowired
	private IWechatHelper wechatHelper;

	public InputStream getMediaFile(String mediaId){
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpGet httpGet	 = new HttpGet();
		try {
			httpGet.setURI(
					new URI(MessageFormat.format(WechatConstants.FILE_MEDIA_GET, accessToken.getAccess_token(),mediaId)));
			CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet);
			return response.getEntity().getContent();
		} catch (URISyntaxException | UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
			return null;
		}
		


		
	}
}
