package com.github.x19990416.macrossx.spring.wechat.impl;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.wechat.IWechatCardHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatHelper;
import com.github.x19990416.macrossx.spring.wechat.WechatConstants;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatAccessToken;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardActivation;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatLogo;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatResponseObj;
import com.github.x19990416.macrossx.spring.wechat.server.http.WechatHttpClient;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatCardHelper implements IWechatCardHelper {

	@Autowired
	private IWechatHelper wechatHelper;

	public Optional<WechatLogo> uploadLogo(InputStream inputStream) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.UPLOAD_LOGO, accessToken.getAccess_token())));
			httpPost.setEntity(MultipartEntityBuilder.create().addBinaryBody("buffer", inputStream).build());
			return new WechatHttpClient().send(httpPost, WechatLogo.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}
	}

	public Optional<WechatResponseObj> create(WechatCardCreation wechatCard) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.UPLOAD_LOGO, accessToken.getAccess_token())));
			httpPost.setEntity(new StringEntity(new Gson().toJson(wechatCard), "utf-8"));
			return new WechatHttpClient().send(httpPost, WechatResponseObj.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}

	}

	public Optional<WechatResponseObj> activate(WechatCardActivation activation) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.UPLOAD_LOGO, accessToken.getAccess_token())));
			httpPost.setEntity(new StringEntity(new Gson().toJson(activation), "utf-8"));
			return new WechatHttpClient().send(httpPost, WechatResponseObj.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}

	}

}
