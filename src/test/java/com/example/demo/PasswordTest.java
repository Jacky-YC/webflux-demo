package com.example.demo;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class PasswordTest {
	
	private static final String USER_PASSWORD = "Jacky0713.";
	
	public static StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("password");
		config.setAlgorithm("PBEWithMD5AndDES");
//		config.setAlgorithm("PBEWithHmacSHA512AndAES_256");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}
	
	public static void main(String[] args) {
		StringEncryptor stringEncryptor = stringEncryptor();
		String encrypt = stringEncryptor.encrypt("Jacky0713.");
		System.out.println("密文："+ encrypt);
		System.out.println("明文："+ stringEncryptor.decrypt("YQJQEhJWGK6skBJvzLwfx1aX+c1sYxjY"));
		
		//		ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
//
//		encryptor.setAlgorithm("SHA-1");
//		encryptor.setStringOutputType("base64");
//
//		String secret = encryptor.encryptPassword(USER_PASSWORD);
//
//		System.out.println(secret);
	
	}
}
