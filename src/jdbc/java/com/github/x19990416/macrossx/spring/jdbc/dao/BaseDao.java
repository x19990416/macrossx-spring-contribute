package com.github.x19990416.macrossx.spring.jdbc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface BaseDao {
	/**
	 * 获取单个对象实例
	 * 
	 * @param sql
	 * @param requiredType
	 * @param args
	 *            可以为null
	 * @return
	 */
	public <T> T get(String sql, Class<T> requiredType, Object... args);

	/**
	 * 更新
	 * 
	 * @param sql
	 * @param args
	 *            可以为null
	 * @return
	 */
	public int update(String sql, Object... args);

	/**
	 * 
	 * @param sql
	 * @param elementType
	 * @param args
	 * @return
	 */
	public <T> List<T> listObj(String sql, Class<T> elementType, Object... args);

	public <T> List<T> listObj(String sql, Class<T> elementType, Map<String, Object> pMap);

	public <T> List<T> queryForList(String string, Class<T> type, Object... args);

	public Map<String, Object> listMap(String sql, Object... args);

	public JdbcTemplate getJdbcTemplate();

	public long getLastInsertId();

	public SqlRowSet queryForRowSet(String string, Object... args);

	public long getCountBySql(String sql, String countName, Object... args);

	public long insertAndGetKey(String sql, Object... args);
	
	public int updateByKey(String tableName, String keyName,Map<String,Object> params);

}
