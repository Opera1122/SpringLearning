package com.springbook.biz.user.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;	
	private final String USER_INSERT = "insert into users(id, password, name, role) values(?, ?, ?, ?)";
	private final String USER_UPDATE = "update users password=?, name=?, role=?, where id=?";
	private final String USER_DELETE = "delete users where id=?";
	private final String USER_GET = "select * from users where id=?";
	private final String USER_LIST = "select * from users order by id desc";
	
	public void insertUser(UserVO vo) {
		System.out.println("===> spring jdbc insert ");		
		jdbcTemplate.update(USER_INSERT, vo.getId(), vo.getPassword(), vo.getName(), vo.getRole());
	}

	public void updateUser(UserVO vo) {
		System.out.println("===> spring jdbc update");
		jdbcTemplate.update(USER_UPDATE, vo.getPassword(), vo.getName(), vo.getRole(), vo.getId());
	}

	public void deleteUser(UserVO vo) {
		System.out.println("===> spring jdbc delete");
		jdbcTemplate.update(USER_DELETE, vo.getId());
	}

	public UserVO getUser(UserVO vo) {
		System.out.println("===> spring jdbc getUser");
		Object[] args= {vo.getId()};
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}

	public List<UserVO> getUserList(UserVO vo) {
		System.out.println("===> spring jdbc getUserList");
		return jdbcTemplate.query(USER_LIST,new UserRowMapper());
	}
}
