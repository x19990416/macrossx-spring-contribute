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
package com.github.x19990416.macrossx.spring.wechat;

public interface WechatConstants {
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}";
	public static final String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={0}";
	public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token={0}";
	public static final String MESSAGE_TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";
	public static final String USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={0}&next_openid={1}";
	public static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}";
	public static final String FILE_MEDIA_GET="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";
	public static final String UPLOAD_LOGO = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token={0}";
	public static final String CARD_CREATE="https://api.weixin.qq.com/card/create?access_token={0}";
	public static final String CARD_UPDATE="https://api.weixin.qq.com/card/update?access_token={0}";
	public static final String CARD_ACTIVATE="https://api.weixin.qq.com/card/generalcard/activate?access_token={0}";
	public static final String CARD_UPDATE_USER="https://api.weixin.qq.com/card/generalcard/updateuser?access_token={0}";
	public static final String CARD_RELEASE_CREATE="https://api.weixin.qq.com/card/qrcode/create?access_token={0}";
	public static final String STORE_POI_LIST="https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token={0}";
	
	
	
	public static final String OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	public static final String OPEN_ACCESS_TOKEN_REFRESH_URL="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={0}&grant_type=refresh_token&refresh_token={1}";
	public static final String OPEN_ACCESS_TOKEN_CHECK_URL="https://api.weixin.qq.com/sns/auth?access_token={0}&openid={1}";
	public static final String OPEN_USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token={1}&openid={0}";
	
	
	public static final String APPLICATION_SESSION_KEY ="https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code"; 
	


}
