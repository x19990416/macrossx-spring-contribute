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

import java.util.Optional;

import com.github.x19990416.macrossx.spring.wechat.entity.WechatCouponCreateQRCodeReqObj;
import com.github.x19990416.macrossx.spring.wechat.entity.WechatCouponCreateQRCodeRespObj;

public interface IWechatCouponReleaseHelper {
	public Optional<WechatCouponCreateQRCodeRespObj> createQrCode(WechatCouponCreateQRCodeReqObj coupon);
}
