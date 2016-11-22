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
package com.github.x19990416.spring.http.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.x19990416.spring.http.constraint.ParamCheck;
import com.macrossx.springframework.Constants;
import com.macrossx.springframework.common.MapResponse;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class ParamVerification {
	@Autowired
	private Validator validator;

	public Object arround(ProceedingJoinPoint pjp) throws Throwable {

		int i = 0;
		for (Parameter parameter : ((MethodSignature) pjp.getSignature()).getMethod().getParameters()) {
			for (Annotation annotation : parameter.getAnnotations()) {
				if (annotation instanceof ParamCheck) {
					List<ConstraintViolation> list = validator.validate(pjp.getArgs()[i]);
					if (!list.isEmpty())
						return new MapResponse(Constants.RESPONSE_MESSAGE.M400.code(), list.get(0).getMessage());
				}
			}
			i++;
		}
		return pjp.proceed(pjp.getArgs());
	}
}
