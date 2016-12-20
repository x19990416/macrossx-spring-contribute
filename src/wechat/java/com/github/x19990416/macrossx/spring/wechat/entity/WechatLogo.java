package com.github.x19990416.macrossx.spring.wechat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class WechatLogo extends WechatRequestObj {
	private String url;
}
