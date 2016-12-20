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
package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.x19990416.macrossx.spring.wechat.IWechatApplicationHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatCardHelper;
import com.github.x19990416.macrossx.spring.wechat.IWechatStoreHelper;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.BaseInfo;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.CODE_TYPE;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.Card;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.DATE_INFO_TYPE;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.DateInfo;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.GeneralCard;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.SUB_CARD_TYPE;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCardCreation.Sku;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-wechat-context.xml")

public class Test1 {
	@Autowired
	IWechatApplicationHelper service;
	@Autowired
	IWechatCardHelper cardHelper;
	@Autowired
	IWechatStoreHelper storeHelper;
	private static String logo = "http://mmbiz.qpic.cn/mmbiz_png/I6tjtDB8IxqjtsWJm3iaCHDJUpfQpvZVbQ4lYbFQkm5FDCA5UnOBUicfzAnZJ9YuQicVxWhQnh9hMQtnBJPHoib9bQ/0";
	private static String back = "http://mmbiz.qpic.cn/mmbiz_png/I6tjtDB8IxqjtsWJm3iaCHDJUpfQpvZVbr5ibBwJF2b7SYo4UIYYYfU5mHicfkJ9uVNwsujOibQAhUbWcVcByibDkbA/0";

	@Test
	public void test1() {

		// System.out.println(service.getSessionKey("0313AJai0Z5o8k1Yf8bi0ecrai03AJaE"));
		String sessionkey = "BA5Ghw6rtLvd+WI87uw+8Q==";
		String data = "AFgNKyYo6Vv8A/CxbVslHYKkrInYDoMVtnPtthYiHAFxot4b1MrS5dwklnj672az0yTtaOoPt9Pnhzw3zsDxJ3SU8BWoLYvvDKEG8SiJslLhSRgP0NpQtxcX8s+zPRUh7vfJoVJIdD8ZbolTwBZzAQO4ZwaTILSMcZ7HrUwG8Ga8/V5ip45yFgEo2qcHNenwHnztbqP4XDH83A9KPNGb2j2LAzSByZpwCrHybjNCya9ZuMXv4M1Pqn5pTvbY62vezW0RrNZiZRZqlEaCwBpIzIEfiPZjsrPP9tdqYdGwwPM6AuFFVOePTuCqM6PmTLkPhOHXH5iITH1ThIP7l+4z4jHXdfI2pPZTQBmo1Bq9aRXVgfhZrhDdanS8l0GpLKGCPWQ8dm3Zar4XLMLLVXqc+m4ZWVHARyYaK1YGNLpamwPsyUwHiJt+ueMhG+luWvdogSwRChH2PIhyRMM8B11UBw==";
		String iv = "zpfcJxENXxvmsnAa+zvoCw==";
		// remote.load("http://118.178.187.204:8123",
		// "59613bb9-b57b-4298-8327-5b1e2ac37429");

		try {
			WechatCardCreation wechatCard = new WechatCardCreation();
			wechatCard.setCard(Card.builder().card_type("GENERAL_CARD").general_card(GeneralCard.builder().activate_url("www.library.sh.cn").supply_bonus(false).supply_balance(false).sub_card_type(SUB_CARD_TYPE.READER_CARD).base_info(BaseInfo.builder().use_dynamic_code(true).logo_url(logo)
					.code_type(CODE_TYPE.CODE_TYPE_QRCODE).brand_name("上海图书馆").title("上海图书馆读者证").color("Color030")
					.notice("欢迎使用上海图书馆电子读者证").description("由上海图书馆提供").need_push_on_view(true)
					.date_info(DateInfo.builder().type(DATE_INFO_TYPE.DATE_TYPE_PERMANENT).build())
					.sku(Sku.builder().quantity(0l).build()).location_id_list(Lists.newArrayList(275927035l)).use_all_locations(true).use_custom_code(true).can_give_friend(false).can_share(false).service_phone("021-64455555").custom_url_name("上海图书馆").custom_url("http://www.library.sh.cn").build()).build()).build());
			
			System.out.println(new Gson().toJson(wechatCard));	
			
			

			//cardHelper.create(wechatCard);
			
		System.out.println(cardHelper.create(wechatCard));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
