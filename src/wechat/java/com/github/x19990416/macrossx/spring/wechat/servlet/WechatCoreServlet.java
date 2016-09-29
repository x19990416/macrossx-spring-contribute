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
package com.github.x19990416.macrossx.spring.wechat.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.x19990416.macrossx.spring.wechat.IWechatServer;
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
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import lombok.Data;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value = "/wechat")
public class WechatCoreServlet {

	@Value("#{applicationProperties['macrossx.wechat.token']}")
	private String wechatToken;

	@Autowired
	private IWechatServer wechatServer;

	@RequestMapping(value = "/wechat.do", method = RequestMethod.GET)
	@ResponseBody
	public void wechat(HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (checkSignature(req)) {
				resp.getWriter().write(req.getParameter("echostr"));
				resp.getWriter().flush();
				resp.getWriter().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

	}

	@RequestMapping(value = "/wechat.do", method = RequestMethod.POST)
	@ResponseBody
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (checkSignature(req)) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int i = -1;
				while ((i = req.getInputStream().read()) != -1) {
					baos.write(i);
				}
				MessageType messageType = phraseXml(new String(baos.toString().getBytes(), "utf-8"), MessageType.class);
				WechatHttpEntity entity = null;
				if (messageType != null) {
					switch (messageType.getMsgType()) {
					case "text": {
						entity = wechatServer.onTextMessage(phraseXml(baos.toString(), WechatTextRequest.class));
						break;
					}
					case "image": {
						entity = wechatServer.onImageMessage(phraseXml(baos.toString(), WechatImageRequest.class));
						break;
					}
					case "voice": {
						entity = wechatServer.onVoiceMessage(phraseXml(baos.toString(), WechatVoiceRequest.class));
						break;
					}
					case "video": {
						entity = wechatServer.onVideoMessage(phraseXml(baos.toString(), WechatVideoRequest.class));
						break;
					}
					case "shortvideo": {
						entity = wechatServer.onShortVideoMessage(phraseXml(baos.toString(), WechatVideoRequest.class));
						break;
					}
					case "location": {
						entity = wechatServer
								.onLocationMessage(phraseXml(baos.toString(), WechatLocationRequest.class));
						break;
					}
					case "link": {
						entity = wechatServer.onLinkMessage(phraseXml(baos.toString(), WechatLinkRequest.class));
						break;
					}
					case "event": {
						switch (messageType.getEvent()) {
						case "subscribe": {
							entity = wechatServer
									.onEventSubscribe(phraseXml(baos.toString(), WechatSubscribeEventRequest.class));
							break;
						}
						case "unsubscribe": {
							entity = wechatServer
									.onEventUnsubscribe(phraseXml(baos.toString(), WechatSubscribeEventRequest.class));
							break;
						}
						case "LOCATION": {
							entity = wechatServer
									.onEventLocation(phraseXml(baos.toString(), WechatLocationEventRequest.class));
							break;
						}
						case "SCAN": {
							entity = wechatServer
									.onEventQrscene(phraseXml(baos.toString(), WechatQrsceneEventRequest.class));
							break;
						}
						case "CLICK": {
							entity = wechatServer.onEventMenu(phraseXml(baos.toString(), WechatMenuEventRequest.class));
							break;
						}
						}
						break;
					}
					}
				}
				String result = entity == null ? "success" : phraseBean(entity);
				// log.info(result);
				resp.setContentType("text/html");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(result);
				resp.getWriter().flush();
				resp.getWriter().close();
			}

		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

	}

	private boolean checkSignature(final HttpServletRequest req) {
		String[] params = { wechatToken, req.getParameter("timestamp"), req.getParameter("nonce") };
		Arrays.sort(params);
		StringBuilder builder = new StringBuilder();
		for (String param : params) {
			builder.append(param);
		}
		return Hashing.sha1().hashString(builder.toString(), Charsets.UTF_8).toString()
				.equals(req.getParameter("signature"));

	}

	@SuppressWarnings("unchecked")
	public <T> T phraseXml(String xml, Class<T> clazz) {
		try {
			System.out.println(xml);
			return (T) JAXBContext.newInstance(clazz).createUnmarshaller()
					.unmarshal(new StringReader(new String(xml.getBytes(), "utf-8")));
		} catch (JAXBException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return null;
	}

	private <T> String phraseBean(T t) {
		try {
			Marshaller marshaller = JAXBContext.newInstance(t.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");//
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			marshaller.marshal(t, output);
			return new String(output.toByteArray(), "utf-8");
		} catch (JAXBException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return "";
	}

	@XmlRootElement(name = "xml")
	@Data
	public static class MessageType {
		@XmlElement(name = "MsgType")
		private String MsgType;
		@XmlElement(name = "Event")
		private String Event;
	}
}
