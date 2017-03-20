package com.github.x19990416.macrossx.spring.wechat.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class WechatCardUserGetCardListRespObj extends  WechatResponseObj{
	private Long result_bonus;
	private Long result_balance;
	private String openId;
	private List<Card> card_list;
	
	@Data
	public static class Card{
		private String code;
		private String card_id;
	}
}