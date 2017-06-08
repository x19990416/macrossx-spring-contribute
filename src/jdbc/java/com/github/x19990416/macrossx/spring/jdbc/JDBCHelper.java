package com.github.x19990416.macrossx.spring.jdbc;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JDBCHelper {
	private static final String lastId_mysql = "select LAST_INSERT_ID()";

	public static <T> T get(JdbcTemplate jdbcTemplate, String sql, Class<T> requiredType, Object... args) {
		try {
			RowMapper<T> rm = BeanPropertyRowMapper.newInstance(requiredType);
			if (null == args || args.length == 0) {
				return jdbcTemplate.queryForObject(sql, rm);
			}
			return jdbcTemplate.queryForObject(sql, rm, args);
		} catch (IncorrectResultSizeDataAccessException e) {
			log.info("{}", e);
			return null;
		}
	}

	public static int update(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		if (null == args || args.length == 0) {
			return jdbcTemplate.update(sql);
		}
		return jdbcTemplate.update(sql, args);
	}

	public static <T> List<T> listObj(JdbcTemplate jdbcTemplate, String sql, Class<T> elementType, Object... args) {
		if (null == args || args.length == 0) {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(elementType));
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(elementType), args);
	}

	public Map<String, Object> listMap(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		if (null == args || args.length == 0) {
			return jdbcTemplate.queryForMap(sql);
		}
		return jdbcTemplate.queryForMap(sql, args);
	}

	public static long insertAndGetKey(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(java.sql.Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				for (Object o : args) {
					ps.setObject(i++, o);
				}
				return ps;
			}
		}, keyHolder);
		Long generatedId = keyHolder.getKey().longValue();
		return generatedId;
	}

	public static <T> List<T> listObj(JdbcTemplate jdbcTemplate, String sql, Class<T> elementType,
			Map<String, Object> pMap) {
		Object[] args = null;
		if (pMap != null && pMap.size() > 0) {
			args = new Object[pMap.size()];
			sql += " where ";
			int index = 0;
			for (Map.Entry<String, Object> e : pMap.entrySet()) {
				if (index + 1 == pMap.size()) {
					sql += e.getKey() + "=?";
				} else {
					sql += e.getKey() + "=? and ";
				}
				args[index] = e.getValue();
				index++;
			}
		}
		return listObj(jdbcTemplate, sql, elementType, args);
	}

	public static long getLastInsertId(JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.queryForObject(lastId_mysql, Long.class);
	}

	public static SqlRowSet queryForRowSet(JdbcTemplate jdbcTemplate, String string, Object... args) {
		return jdbcTemplate.queryForRowSet(string, args);
	}

	public static <T> List<T> queryForList(JdbcTemplate jdbcTemplate, String string, Class<T> type, Object... args) {
		return jdbcTemplate.queryForList(string, type, args);
	}

	public static long getCountBySql(JdbcTemplate jdbcTemplate, String sql, String countName, Object... args) {
		Map<String, Object> counts = jdbcTemplate.queryForMap(sql, args);
		return (long) counts.get(countName);
	}

	public static int updateByKey(JdbcTemplate jdbcTemplate, String tableName, String keyName,
			Map<String, Object> params) {
		if (params == null || params.isEmpty() || StringUtils.isEmpty(keyName))
			return -1;
		Object[] objects = new Object[params.values().size()];
		StringBuilder sql = new StringBuilder("update " + tableName);
		Object id = params.remove(keyName);
		sql.append(" set ");
		for (String key : params.keySet()) {
			sql.append(key + "=?,");
		}
		sql = new StringBuilder(sql.subSequence(0, sql.length() - 1));
		sql.append(" where " + keyName + "=?");
		int i = 0;
		for (Object o : params.values()) {
			objects[i++] = o;
		}
		objects[i] = id;
		return update(jdbcTemplate, sql.toString(), objects);
	}

	public static int updateByKey(JdbcTemplate jdbcTemplate, Object o) {
		Map<String, Object> params = Maps.newHashMap();
		Table table = o.getClass().getDeclaredAnnotation(Table.class);
		if (table == null) {
			log.error("no declare annotation of Table in  {}", o.getClass());
			return -1;
		}
		String tableName = table.value();
		if (StringUtils.isEmpty(tableName)) {
			log.error("no value of Table in {}" + o.getClass());
			return -1;
		}
		String keyName = null;
		for (Field field : o.getClass().getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null && StringUtils.isEmpty(column.value()))
				continue;
			field.setAccessible(true);
			try {
				Object value = field.get(o);
				if (value != null)
					params.put(column.value(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				log.error("{}", e);
			}
			if (field.getAnnotation(Key.class) != null) {
				keyName = column.value();
			}
		}
		return updateByKey(jdbcTemplate, tableName, keyName, params);
	}

}
