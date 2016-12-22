package com.github.x19990416.macrossx.spring.wechat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
public class WechatCardUserUpdateReqObj extends WechatRequestObj{
	private String code;
	private String card_id;
	private String background_pic_url;
	private String record_bonus;
	private Long bonus;
	private String custom_field_value1;
	private String custom_field_value2;
	private String custom_field_value3;
	private Long balance;
}
