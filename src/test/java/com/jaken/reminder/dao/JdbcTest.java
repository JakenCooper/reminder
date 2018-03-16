package com.jaken.reminder.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;

import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jaken.reminder.config.RootConfig;
import com.jaken.reminder.model.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class JdbcTest {

	@Autowired
	private JdbcTemplate jt;

	@Test
	public void testquery() {
		String sql = "select id,name from t_article";
		jt.query(sql, new Object[] {}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println(rs.getString(1) + " || " + rs.getString(2));
			}
		});
	}

	@Test
	public void testproc() {
		String result = jt.execute(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection conn) throws SQLException {
				String sql = "{call p_article_refresher(?)}";
				CallableStatement cs = conn.prepareCall(sql);
				cs.registerOutParameter(1, Types.VARCHAR);
				return cs;
			}

		}, new CallableStatementCallback<String>() {

			@Override
			public String doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getString(1);
			}
		});
		System.out.println("=========== " + result);
	}
}
