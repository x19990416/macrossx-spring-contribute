package com.github.x19990416.macrossx.spring.component.remotefile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpRemoteFileService implements IRemoteFileService {
	private String host;
	private int port;
	private String username;
	private String password;
	private String baseUrl;
	private int timeOutMs;

	public FtpRemoteFileService(String host, int port, String username,
			String password, String baseUrl, int maxPool, int timeOutMs) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.baseUrl = baseUrl;
		this.timeOutMs = timeOutMs;
	}

	@Override
	public boolean save(InputStream in, String subUrl, String name) {
		FTPClient ftpClient = createReq(subUrl);
		if (ftpClient == null)
			return false;

		boolean result = false;
		
		try {
			result = ftpClient.storeFile(name, in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected())
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
		}

		return result;
	}

	@Override
	public boolean remove(String subUrl, String name) {
		FTPClient ftpClient = createReq(subUrl);
		if (ftpClient == null)
			return false;
		
		boolean result = false;
		
		try {
			result = ftpClient.deleteFile(name);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected())
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
		}

		return result;
	}

	@Override
	public List<String> list(String subUrl) {
		FTPClient ftpClient = createReq(subUrl);
		if (ftpClient == null)
			return null;
		
		List<String> resultList = null;
		
		try {
			FTPFile[] fileList = ftpClient.listFiles();
			resultList = new ArrayList<String>(fileList.length);
			for (FTPFile file : fileList)
				resultList.add(file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected())
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
		}
		
		return resultList;
	}

	protected FTPClient createReq(String subUrl) {
		FTPClient ftpClient = new FTPClient();
		boolean error = true;

		try {
			ftpClient.connect(host, port);
			int reply = ftpClient.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				ftpClient.setControlKeepAliveTimeout(timeOutMs);
				if (ftpClient.login(username, password)) {
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					String workingDir = combineUrl(baseUrl, subUrl);
					if (!StringUtils.isEmpty(workingDir)) {
						error = !ftpClient.changeWorkingDirectory(workingDir);
					} else
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
		}

		return ftpClient;
	}
}
