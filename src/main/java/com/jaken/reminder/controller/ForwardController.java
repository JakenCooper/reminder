package com.jaken.reminder.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jaken.reminder.service.ArticleService;

@Controller
public class ForwardController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private JdbcTemplate jt;
	
	private static boolean refreshtag=false;
	
	@RequestMapping("/main")
	public String main(Model model) {
		if(!refreshtag) {
			String execResult=jt.execute(new CallableStatementCreator() {
				
				@Override
				public CallableStatement createCallableStatement(Connection conn) throws SQLException {
					CallableStatement cs=conn.prepareCall("{call p_article_refresher(?)}");
					cs.registerOutParameter(1,Types.VARCHAR);
					return cs;
				}
			}, new CallableStatementCallback<String>() {
				@Override
				public String doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					cs.execute();
					return cs.getString(1);
				}
			});
			System.out.println("execute result======== "+execResult);
			refreshtag=true;
		}
		model.addAttribute("reqResult", articleService.findAllArticlesByPage(0, 40));
		return "main";
	}
}
