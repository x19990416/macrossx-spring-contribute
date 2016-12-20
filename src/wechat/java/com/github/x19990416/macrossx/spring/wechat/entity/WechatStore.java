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

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class WechatStore extends WechatResponseObj {
	private List<BaseInfoWrapper> business_list;
	private String total_count;

	@Data
	public static class BaseInfoWrapper {
		private BaseInfo base_info;
	}

	@Data
	public static class BaseInfo {
		private String sid;
		private String business_name;
		private String branch_name;
		private String address;
		private String telephone;
		private List<String> categories;
		private String city;
		private String province;
		private Integer offset_type;
		private Double longitude;
		private Double latitude;
		private List<Photo> photo_list;
		private String introduction;
		private String recommend;
		private String special;
		private String open_time;
		private Long avg_price;
		private String poi_id;
		private Long available_state;
		private String district;
		private Integer update_status;
	}

	@Data
	public static class Photo {
		private String photo_url;
	}

	public static void main(String... s) {
		WechatStore store = new WechatStore();
		BaseInfoWrapper rapper = new BaseInfoWrapper();
		BaseInfo baseinfo = new BaseInfo();
		baseinfo.setAddress("aaa");
		rapper.setBase_info(baseinfo);
		store.setBusiness_list(Lists.newArrayList(rapper));
		System.out.println(new Gson().toJson(store));
	}
}
