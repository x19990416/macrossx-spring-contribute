package com.github.x19990416.macrossx.spring.wechat;

import java.util.Optional;

import com.github.x19990416.macrossx.spring.wechat.entity.WechatOpenAccessToken;

public interface IWechatOpenHelper {
	
	public Optional<WechatOpenAccessToken> getAccessToken(String code);

	public Optional<WechatOpenAccessToken> refreshAccessToken(String refreshToken);
	/**
	 * true 有效 
	 */
	public boolean checkAccessToken(String token, String openid);
	
	
}
