package com.github.x19990416.macrossx.spring.wechat;

import java.io.InputStream;
import java.util.Optional;

import com.github.x19990416.macrossx.spring.wechat.entity.WechatLogo;

public interface IWechatCardHelper {
	public Optional<WechatLogo> uploadLogo(InputStream inputStream);
}
