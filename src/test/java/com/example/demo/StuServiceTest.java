package com.example.demo;

import com.example.demo.domain.Stu;
import com.example.demo.service.StuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StuServiceTest {
	
	@Autowired
	private StuService service;
	
	@Test
	void test() {
		Stu stu = new Stu();
		stu.setAge(24);
		stu.setName("Jacky");
		
		Mono<Stu> stuMono = service.saveOrUpdate(stu);
		stuMono.subscribe(stu1 -> {
			System.out.println(stu1);
		});
		
	}
	
	
}
