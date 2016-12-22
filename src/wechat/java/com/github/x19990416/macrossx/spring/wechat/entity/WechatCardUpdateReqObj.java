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

import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj.CODE_TYPE;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj.COLOR;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj.CustomField;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj.DATE_INFO_TYPE;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj.DateInfo;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj.FIELD_NAME_TYPE_LEVEL;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreateReqObj.SUB_CARD_TYPE;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
public class WechatCardUpdateReqObj extends WechatRequestObj {
	private String card_id;
	private Card general_card;

	@Data
	@Builder
	public static class Card {
		private BaseInfo base_info;
		private SUB_CARD_TYPE sub_card_type;
		private String activate_url;
		private String bonus_rules;
		private String bonus_cleared;
		private String prerogative;
		private CustomField custom_field1;
		private CustomField custom_field2;
		private CustomField custom_field3;
		private FIELD_NAME_TYPE_LEVEL name_type;
		private String url;
		private CustomField custom_cell1;
		private String detail;
		private Long departure_time;
		private Long landing_time;
		private String gate;
		private Long boarding_time;
		private String guide_url;
		private String map_url;
		private String balance_rules;
		

	}

	@Data
	@Builder
	public static class BaseInfo {
		private String brand_name;
		private String logo_url;
		private CODE_TYPE code_type;
		private String title;
		private COLOR color;
		private String notice;
		private String service_phone;
		private String description;
		private List<Long> location_id_list;
		private String center_title;
		private String center_sub_title;
		private String center_url;
		private String custom_url_name;
		private String custom_url;
		private String custom_url_sub_title;
		private String promotion_url_name;
		private String promotion_url;
		private String promotion_url_sub_title;
		private Integer get_limit;
		private Boolean can_share;
		private Boolean can_giv_firend;
		private DateInfo date_info;
		private DATE_INFO_TYPE type;
		private Long begin_timestamp;
		private Long end_timestamp;
	}

	@Data
	@Builder
	public static class CustomCell {
		private String name;
		private String tips;
		private String url;

	}
}
