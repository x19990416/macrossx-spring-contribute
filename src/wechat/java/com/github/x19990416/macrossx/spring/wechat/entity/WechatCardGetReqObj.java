package com.github.x19990416.macrossx.spring.wechat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
public class WechatCardGetReqObj extends WechatRequestObj {
	/**
	 * 单张卡券的唯一标准
	 */
	private String card_id;
}
