package com.springbook.biz.test;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

public class UserServiceClient {
	public static void main(String[] args) {
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");

		UserService userService = (UserService) container.getBean("userService");
		
		UserVO vo = new UserVO();
		vo.setId("test1");
		vo.setPassword("123");
		vo.setName("홍길동");
		vo.setRole("일반회원");
		userService.insertUser(vo);
		
		List<UserVO> userList = userService.getUserList(vo);
		for (UserVO user : userList) {
			System.out.println("---> " + user.toString());
		}

		// 5. Spring 컨테이너 종료
		container.close();
	}
}