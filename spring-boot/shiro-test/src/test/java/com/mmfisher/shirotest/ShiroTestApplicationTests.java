package com.mmfisher.shirotest;

import com.mmfisher.shirotest.dao.UserRepository;
import com.mmfisher.shirotest.domain.UserInfo;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTestApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	public void contextLoads() {
	}

	@Test
	public void daoTest(){
		UserInfo user = userRepository.getUserInfoByName("管理员");
		System.out.print(user.getRoles());

	}

	@Test
	public void aesTest(){

		System.out.print(ByteSource.Util.bytes("admin8d78869f470951332959580424d4bf4f"));
		//,
	}

}
