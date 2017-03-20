package com.github.x19990416.macrossx.spring.wechat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
public class WechatCardCodeGetReqObj extends WechatRequestObj {
	/**
	 * ���ſ�ȯ��Ψһ��׼
	 */
	private String card_id;
	/**
	 * ��ȯID����һ�࿨ȯ���Զ���code��ȯ����
	 */
	private String code;
	/**
	 * �Ƿ�У��code����״̬������true��falseʱ��code�쳣״̬�������ݲ�ͬ
	 */
	private boolean check_consume;
}
