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
package com.github.x19990416.macrossx.spring.component.file;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.github.x19990416.macrossx.spring.jdbc.dao.impl.BaseDaoImpl;
import com.macrossx.springframework.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileServiceImpl extends BaseDaoImpl implements IFileService{

	public Long save(File file) {
		return super.insertAndGetKey(
				"insert into file(refid,name,type,file,create_user,update_user,create_time,update_time)values(?,?,?,?,?,?,SYSDATE,SYSDATE())",
				file.getRefid(), file.getName(), file.getType(), file.getFile(), file.getCreateUser(),
				file.getUpdateUser());
	}

	public int remove(Long id) {
		return super.update("delete from file where id=?", id);
	}

	public Optional<File> listById(Long id) {
		List<File> lFile = super.listObj("select * from file where id=?", File.class, id);
		return lFile.isEmpty() ? Optional.empty() : Optional.of(lFile.get(0));

	}

	public void read(final OutputStream os, Long id) throws ServiceException {
		this.listById(id).ifPresent(e -> {
			switch (e.getType().toUpperCase()) {
			case "JPG":
			case "PNG": {
				try {
					ImageIO.write(ImageIO.read(e.getFile().getBinaryStream()), e.getType().toLowerCase(), os);
					return;
				} catch (Exception e1) {
					log.error("{}", e1);
					throw new ServiceException("999999", e1);
				}
			}
			default: {
				try {
					os.write(IOUtils.toByteArray(e.getFile().getBinaryStream()));
				} catch (Exception e1) {
					log.error("{}", e1);
					throw new ServiceException("999999", e1);
				}
			}
			}

		});

	}
}
