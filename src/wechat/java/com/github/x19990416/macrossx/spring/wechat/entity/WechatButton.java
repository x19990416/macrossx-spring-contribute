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
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WechatButton extends WechatRequestObj {
	private String name;
	private String type;
	private String key;
	private String url;
	private List<WechatButton> sub_button;
	
	public static void main(String...s){
		List<WechatButton> t = Lists.newArrayList();
		WechatButton b = new WechatButton();
		b.setName("a");
		b.setType("b");
		t.add(b);
		
		WechatButton b1 = new WechatButton();
		b1.setName("a");
		b1.setType("b");
		t.add(b1);
		Map<String,List<WechatButton>>tx = Maps.newHashMap();
		tx.put("button", t);
		System.out.println(new Gson().toJsonTree(tx));
	}
}
