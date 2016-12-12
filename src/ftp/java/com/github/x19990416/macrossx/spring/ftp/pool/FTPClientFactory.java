package com.github.x19990416.macrossx.spring.ftp.pool;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class FTPClientFactory extends BasePooledObjectFactory<FTPClient> {

	private final String host;
	private final int port;
	private final String username;
	private final String password;
	private final int timeOutMs;

	public FTPClientFactory(final String host, final int port,
			final String username, final String password, final int timeOutMs) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.timeOutMs = timeOutMs;
	}

	@Override
	public FTPClient create() throws Exception {
		FTPClient ftpClient = new FTPClient();
		boolean error = true;

		try {
			ftpClient.connect(host, port);
			int reply = ftpClient.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				ftpClient.setControlKeepAliveTimeout(timeOutMs);
				if (ftpClient.login(username, password)) {
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					error = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (error) {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
			}
			ftpClient = null;
			throw new IOException();
		}

		return ftpClient;
	}

	@Override
	public PooledObject<FTPClient> wrap(FTPClient obj) {
		return new DefaultPooledObject<FTPClient>(obj);
	}

	@Override
	public void destroyObject(PooledObject<FTPClient> p) throws Exception {
		FTPClient ftpClient = p.getObject();
		try {
			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected())
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
		}
	}

}
