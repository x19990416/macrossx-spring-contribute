package com.github.x19990416.macrossx.spring.wechat;

import java.io.InputStream;
import java.util.Optional;

import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardActivation;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUserUpdateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUserUpdateRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatLogo;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatResponseObj;

public interface IWechatCardHelper {
	public Optional<WechatLogo> uploadLogo(InputStream inputStream);

	public Optional<WechatCardCreateRespObj> create(WechatCardCreateReqObj wechatCard);

	public Optional<WechatResponseObj> activate(WechatCardActivation activation);

	public Optional<WechatCardUserUpdateRespObj> userUpdate(WechatCardUserUpdateReqObj update);
	
	public Optional<WechatCardCreateRespObj> update(WechatCardCreateReqObj wechatCard);
}
