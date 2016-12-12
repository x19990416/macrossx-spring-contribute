package com.github.x19990416.macrossx.spring.component.remotefile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.github.x19990416.macrossx.spring.ftp.pool.FTPClientFactory;

public class FtpRemoteFileService implements IRemoteFileService {
	private String baseUrl;
	private GenericObjectPool<FTPClient> ftpPool;

	public FtpRemoteFileService(String host, int port, String username,
			String password, String baseUrl, int maxPool, int timeOutMs) {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(maxPool);

		this.baseUrl = baseUrl;
		this.ftpPool = new GenericObjectPool<FTPClient>(new FTPClientFactory(
				host, port, username, password, timeOutMs), config);
	}

	@Override
	public boolean save(InputStream in, String subUrl, String name) {
		FTPClient ftpClient = createReq(subUrl);
		if (ftpClient == null)
			return false;

		boolean result = false;
		boolean removeReq = false;

		try {
			result = ftpClient.storeFile(name, in);
		} catch (IOException e) {
			removeReq = true;
			e.printStackTrace();
		} finally {
			releaseReq(ftpClient, removeReq);
		}

		return result;
	}

	@Override
	public byte[] load(String subUrl, String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(String subUrl, String name) {
		FTPClient ftpClient = createReq(subUrl);
		if (ftpClient == null)
			return false;

		boolean result = false;
		boolean removeReq = false;

		try {
			result = ftpClient.deleteFile(name);
		} catch (IOException e) {
			removeReq = true;
			e.printStackTrace();
		} finally {
			releaseReq(ftpClient, removeReq);
		}

		return result;
	}

	@Override
	public List<String> list(String subUrl) {
		FTPClient ftpClient = createReq(subUrl);
		if (ftpClient == null)
			return null;

		List<String> resultList = null;
		boolean removeReq = false;

		try {
			FTPFile[] fileList = ftpClient.listFiles();
			resultList = new ArrayList<String>(fileList.length);
			for (FTPFile file : fileList)
				resultList.add(file.getName());
		} catch (IOException e) {
			removeReq = true;
			e.printStackTrace();
		} finally {
			releaseReq(ftpClient, removeReq);
		}

		return resultList;
	}

	protected FTPClient createReq(String subUrl) {
		FTPClient ftpClient = null;
		boolean error = true;

		try {
			ftpClient = ftpPool.borrowObject();
			String workingDir = combineUrl(baseUrl, subUrl);
			if (!StringUtils.isEmpty(workingDir)) {
				error = !ftpClient.changeWorkingDirectory(workingDir);
			} else
				error = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (error) {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
			}
			ftpClient = null;
		}

		return ftpClient;
	}

	protected void releaseReq(FTPClient ftpClient, boolean remove) {
		try {
			if (remove)
				ftpPool.invalidateObject(ftpClient);
			else
				ftpPool.returnObject(ftpClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
