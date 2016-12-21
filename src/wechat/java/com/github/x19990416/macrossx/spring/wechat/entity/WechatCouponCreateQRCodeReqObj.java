/**
 * Copyright (C) 2016 X-Forever.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.x19990416.macrossx.spring.wechat.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder	
public class WechatCouponCreateQRCodeReqObj extends WechatRequestObj {
	private ACTON_NAME action_name;
	private Long expire_seconds;
	private ActionInfo action_info;
	
	@Data
	@Builder
	public static class ActionInfo{
		private Card card;
		private MultipleCard mutiple_card;
	}
	@Data
	@Builder	
	public static class Card{
		private String card_id;
		private String code;
		private String openid;
		private Boolean is_unique_code;
		private String outer_str;
		
	}
	@Data
	@Builder
	public static class MultipleCard{
		private List<Card> card_list;
		
	}
	
	public static enum ACTON_NAME{
		QR_CARD,QR_MULTIPLE_CARD
	}
}
