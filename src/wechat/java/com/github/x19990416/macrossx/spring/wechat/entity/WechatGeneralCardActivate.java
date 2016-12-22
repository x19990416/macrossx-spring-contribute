package com.github.x19990416.macrossx.spring.wechat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
public class WechatGeneralCardActivate extends WechatRequestObj {
	private Long init_bonus;
	private Long init_balance;
	private String card_number;
	private String code;
	private String card_id;
	private String background_pic_url;
	private String init_custom_field_value1;
	private String init_custom_field_value2;
	private String init_custom_field_value3;
	private Long activate_begin_time;
	private Long activate_end_time;
	
}
