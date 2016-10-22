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

@Data
public class WechatOpenUserInfo {
	private String openid;
	private String nickname;
	private String sex;
	private String city;
	private String province;
	private String country;
	private String headimurl;
	private String unionid;
	private List<String> privilege;	
}
