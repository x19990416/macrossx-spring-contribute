package com.github.x19990416.macrossx.spring.wechat;

import java.io.File;
import java.util.Optional;

import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardBatchgetReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardBatchgetRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCodeGetReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCodeGetRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCodeUnavailableReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardDecryptRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardGetReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardGetRespObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUpdateReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUserGetCardListReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardUserGetCardListRespObj;
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

	public Optional<WechatResponseObj> delete(String request);

	public Optional<WechatCardBatchgetRespObj> batchget(WechatCardBatchgetReqObj request);

	public Optional<WechatCardDecryptRespObj> decrypt(String encrypt_code);

	public Optional<WechatCardCodeGetRespObj> getCardCode(WechatCardCodeGetReqObj reqObj);

	/**
	 * 查看卡券详情
	 */
	public Optional<WechatCardGetRespObj> cardGet(WechatCardGetReqObj reqObj);

	/**
	 * 获取用户已领取卡券接口
	 */
	public Optional<WechatCardUserGetCardListRespObj> cardUserGetCardList(WechatCardUserGetCardListReqObj reqObj);

	/**
	 * 设置卡券失效接口
	 */
	public Optional<WechatResponseObj> cardCodeUnavailable(WechatCardCodeUnavailableReqObj reqObj);
}
