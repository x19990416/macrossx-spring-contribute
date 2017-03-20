package com.github.x19990416.macrossx.spring.wechat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
public class WechatCardCodeGetReqObj extends WechatRequestObj {
	/**
	 * 单张卡券的唯一标准
	 */
	private String card_id;
	/**
	 * 卡券ID代表一类卡券。自定义code卡券必填
	 */
	private String code;
	/**
	 * 是否校验code核销状态，填入true和false时的code异常状态返回数据不同
	 */
	private boolean check_consume;
}
