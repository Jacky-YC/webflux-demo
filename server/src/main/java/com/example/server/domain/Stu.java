package com.example.server.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author: YeCheng
 * @date: 2020/11/10
 * @time: 17:15
 */
@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class Stu {

	private Long id;

	private String name;

	private Integer age;

	public Stu(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
