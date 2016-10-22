package com.github.x19990416.macrossx.spring.wechat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class WechatOpenAccessToken extends WechatAccessToken{
	private String refresh_token;
	private String openid;
	private String scope;
	private String unionid; 
}
