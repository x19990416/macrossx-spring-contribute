package com.github.x19990416.macrossx.spring.wechat.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class WechatCardCreation extends WechatRequestObj {
	private Card card;

	@Data
	public static class Card {
		private String card_type;
		private GeneralCard general_card;
	}

	@Data
	public static class GeneralCard {
		private SUB_CARD_TYPE sub_card_type;
		private BaseInfo base_info;
	}

	@Data
	public static class BaseInfo {
		private boolean use_dynamic_code;
		private String logo_url;
		private String brand_name;
		private CODE_TYPE code_type;
		private String title;
		private String color;
		private String notice;
		private String service_phone;
		private String description;
		private DateInfo date_info;
		private Sku sku;
		private Long get_limit;
		private boolean use_custom_code;
		private boolean can_give_friend;
		private List<Long> location_id_list;
		private String custom_url_name;
		private String custom_url;
		private boolean need_push_on_view;
		private boolean supply_bonus;
		private boolean supply_balance;
		private String activate_url;
		private CustomField custom_field1;
		private CustomField custom_field2;
		private CustomField custom_field3;
	}

	@Data
	public static class CustomField {
		private NAME_TYPE name_type;

	}

	@Data
	public static class DateInfo {
		private DATE_INFO_TYPE type;
		private Long begin_timestamp;
		private Long end_timestamp;
		private Long fixed_term;
		private Long fixed_begin_term;
	}

	@Data
	public static class Sku {
		private Long quantity;
	}

	public static enum SUB_CARD_TYPE {
		STORED_VALUE_CARD, GIFT_CARD, CAMPUS_CARD, READER_CARD, METRO_CARD, HOSPITAL_CARD, DRIVERS_LICENSE, DRIVING_LICENSE, SCHOOLFELLOW, SOCIAL_SECURITY

	}

	public static enum CODE_TYPE {
		CODE_TYPE_TEXT, CODE_TYPE_BARCODE, CODE_TYPE_QRCODE, CODE_TYPE_ONLY_QRCODE, CODE_TYPE_ONLY_BARCODE
	}

	public static enum DATE_INFO_TYPE {
		DATE_TYPE_FIX_TERM_RANGE, DATE_TYPE_FIX_TERM, DATE_TYPE_PERMANENT
	}

	public static enum NAME_TYPE {
		FIELD_NAME_TYPE_LEVEL, FIELD_NAME_TYPE_COUPON, FIELD_NAME_TYPE_STAMP, FIELD_NAME_TYPE_DISCOUNT, FIELD_NAME_TYPE_ACHIEVEMEN, FIELD_NAME_TYPE_MILEAGE, FIELD_NAME_TYPE_SET_POINTS, FIELD_NAME_TYPE_TIMS
	}
}
