package com.github.x19990416.macrossx.spring.wechat;

import java.io.File;
import java.util.Optional;

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

public interface IWechatCardHelper {
	public Optional<WechatLogo> uploadLogo(File file);

	public Optional<WechatCardCreateRespObj> create(WechatCardCreateReqObj wechatCard);

	public Optional<WechatResponseObj> activateGeneralCard(WechatGeneralCardActivate activation);

	public Optional<WechatCardUserUpdateRespObj> userUpdate(WechatCardUserUpdateReqObj update);
	
	public Optional<WechatCardCreateRespObj> update(WechatCardUpdateReqObj wechatCard);
	
	public Optional<WechatCardBatchgetRespObj> batchget(WechatCardBatchgetReqObj request);
}
