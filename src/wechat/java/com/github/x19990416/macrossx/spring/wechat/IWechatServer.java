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

import com.github.x19990416.macrossx.spring.wechat.server.WechatHttpEntity;
import com.github.x19990416.macrossx.spring.wechat.server.WechatImageRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatLinkRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatLocationEventRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatLocationRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatMenuEventRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatQrsceneEventRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatSubscribeEventRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatTextRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatVideoRequest;
import com.github.x19990416.macrossx.spring.wechat.server.WechatVoiceRequest;

public interface IWechatServer {
	default public WechatHttpEntity onTextMessage(WechatTextRequest request) {
		System.out.println("onTextMessage[" + request + "]");

		return null;
	}

	default public WechatHttpEntity onImageMessage(WechatImageRequest request) {
		System.out.println("onImageMessage[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onVoiceMessage(WechatVoiceRequest request) {
		System.out.println("onVoiceMessage[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onVideoMessage(WechatVideoRequest request) {
		System.out.println("onVideoMessage[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onShortVideoMessage(WechatVideoRequest request) {
		System.out.println("onShortVideoMessage[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onLocationMessage(WechatLocationRequest request) {
		System.out.println("onPositionMessage[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onLinkMessage(WechatLinkRequest request) {
		System.out.println("onLinkMessage[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onEventSubscribe(WechatSubscribeEventRequest request) {
		System.out.println("onEventSubscribe[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onEventUnsubscribe(WechatSubscribeEventRequest request) {
		System.out.println("onEventUnsubscribe[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onEventQrscene(WechatQrsceneEventRequest request) {
		System.out.println("onEventQrscene[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onEventLocation(WechatLocationEventRequest request) {
		System.out.println("onEventLocation[" + request + "]");
		return null;
	}

	default public WechatHttpEntity onEventMenu(WechatMenuEventRequest request) {
		System.out.println("onEventMenu[" + request + "]");
		return null;
	}
}
