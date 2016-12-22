package com.github.x19990416.macrossx.spring.wechat.impl;

import java.io.File;
import java.io.IOException;
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
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardBatchgetReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardBatchgetRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUpdateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUserUpdateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUserUpdateRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatGeneralCardActivate;
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

	public Optional<WechatLogo> uploadLogo(File file) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.UPLOAD_LOGO, accessToken.getAccess_token())));
			
			httpPost.setEntity(MultipartEntityBuilder.create().addBinaryBody("buffer",file).build());
			return new WechatHttpClient().send(httpPost, WechatLogo.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}
	}

	public Optional<WechatCardCreateRespObj> create(WechatCardCreateReqObj wechatCard) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.CARD_CREATE, accessToken.getAccess_token())));
			httpPost.setEntity(new StringEntity(new Gson().toJson(wechatCard), "utf-8"));
			return new WechatHttpClient().send(httpPost, WechatCardCreateRespObj.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}

	}
	

	public Optional<WechatCardCreateRespObj> update(WechatCardUpdateReqObj wechatCard) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.CARD_UPDATE, accessToken.getAccess_token())));
			
			
			String sx = new Gson().toJson(wechatCard);
			System.out.println(sx);
			httpPost.setEntity(new StringEntity(sx,"utf-8"));
			return new WechatHttpClient().send(httpPost, WechatCardCreateRespObj.class);
		} catch (URISyntaxException | UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			e.printStackTrace();
			return Optional.empty();
		}

	}

	public Optional<WechatResponseObj> activateGeneralCard(WechatGeneralCardActivate activation) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.CARD_ACTIVATE, accessToken.getAccess_token())));
			httpPost.setEntity(new StringEntity(new Gson().toJson(activation), "utf-8"));
			return new WechatHttpClient().send(httpPost, WechatResponseObj.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}
	}
	
	public Optional<WechatCardBatchgetRespObj> batchget(WechatCardBatchgetReqObj request){
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.CARD_BATCHGET, accessToken.getAccess_token())));
			httpPost.setEntity(new StringEntity(new Gson().toJson(request), "utf-8"));
			return new WechatHttpClient().send(httpPost, WechatCardBatchgetRespObj.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}
		
	}
	
	public Optional<WechatCardUserUpdateRespObj> userUpdate(WechatCardUserUpdateReqObj update) {
		WechatAccessToken accessToken = wechatHelper.getAccessToken().get();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(MessageFormat.format(WechatConstants.CARD_UPDATE_USER, accessToken.getAccess_token())));
			httpPost.setEntity(new StringEntity(new Gson().toJson(update), "utf-8"));
			return new WechatHttpClient().send(httpPost, WechatCardUserUpdateRespObj.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			log.error("{0}", e);
			return Optional.empty();
		}
	}

}
