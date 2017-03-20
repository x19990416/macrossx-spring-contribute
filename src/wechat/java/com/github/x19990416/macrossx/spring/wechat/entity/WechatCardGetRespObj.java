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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class WechatCardGetRespObj extends WechatResponseObj {
	private Card card;
	private String openid;
	private boolean can_consume;
	private String user_card_status;

	@Data
	public static class Card {
		private String card_type;
		private Discount discount;

	}

	@Data
	public static class Discount {
		private BaseInfo base_info;
		private Long dicount;
		private AdvancedInfo advanced_info;
		private Boolean share_friends;
	}

	@Data
	public static class AdvancedInfo {
		private List<TimeLimit> time_limit;
	}

	@Data
	public static class TimeLimit {
		private String type;
	}

	@Data
	public static class BaseInfo {
		private String id;
		private String logo_url;
		private String code_type;
		private String brand_name;
		private String title;
		private DateInfo date_info;
		private String color;
		private String notice;
		private String description;
		private List<Long> location_id_list;
		private Long get_limit;
		private Boolean can_share;
		private Boolean can_give_friend;
		private String status;
		private Sku sku;
		private Long create_time;
		private Long update_time;

	}

	@Data
	public static class DateInfo {
		private String type;
		private Long fixed_term;
		private Long fixed_begin_term;
	}

	@Data
	public static class Sku {
		private Long quantity;
		private Long total_quantity;
	}

}
