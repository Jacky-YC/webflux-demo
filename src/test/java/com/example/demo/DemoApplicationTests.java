package com.example.demo;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword("Jacky0713.");
		if (passwordEncryptor.checkPassword("Jacky0713.", encryptedPassword)) {
			// correct!
			System.out.println(encryptedPassword);
		} else {
			// bad login!
			System.out.println(2);
		}
		
		
	}

}
