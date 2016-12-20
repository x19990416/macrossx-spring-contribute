package com.github.x19990416.macrossx.spring.wechat;

import java.io.InputStream;
import java.util.Optional;

import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardActivation;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUpdateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUpdateRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatLogo;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatResponseObj;

public interface IWechatCardHelper {
	public Optional<WechatLogo> uploadLogo(InputStream inputStream);

	public Optional<WechatResponseObj> create(WechatCardCreation wechatCard);

	public Optional<WechatResponseObj> activate(WechatCardActivation activation);

	public Optional<WechatCardUpdateRespObj> update(WechatCardUpdateReqObj update);
}
