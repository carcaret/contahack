package com.hackaton.psd2;

import javax.security.auth.login.LoginException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.hackaton.psd2.security.UserTokenMgr;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContahackApplication.class)
@WebAppConfiguration
public class ContahackApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void checkGenTokenCorrect() {
		String token=null;
		try {
			token = UserTokenMgr.getUserToken("alberto.gonzalez.perez.x.x@example.com", "b95eb3");
			System.out.println("[checkGenTokenCorrect] Token generated: "+token);
		} catch (LoginException e) {
			System.out.println("[checkGenTokenCorrect] Oops! "+e.getMessage());
		}
	}

	@Test
	public void checkGenTokenIncorrect() {
		String token=null;
		try {
			token = UserTokenMgr.getUserToken("alberto.gonzalez.perez.x.x@example.com", "xxxx");
			System.out.println("[checkGenTokenCorrect] Token generated: "+token);
		} catch (LoginException e) {
			System.out.println("[checkGenTokenCorrect] Oops! "+e.getMessage());
		}
	}

}
