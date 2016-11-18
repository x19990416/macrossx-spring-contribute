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
package com.github.x19990416.macrossx.spring.component.region;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.jdbc.dao.impl.BaseDaoImpl;

@Component
public class RegionImpl extends BaseDaoImpl implements IRegion {

	public List<Region> province() {
		return this.list(1l);
	}

	private List<Region> list(Long parentId) {
		return super.listObj("select * from region where parent_id=? and region_status=1", Region.class, parentId);
	}

	@Override
	public List<Region> next(Long pid) {
		List<Region> lRegion = list(pid);
		lRegion.removeIf((e) -> {
			return "市辖区".equals(e.getRegionName());
		});
		return lRegion;
	}
}
