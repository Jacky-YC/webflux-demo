package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author: YeCheng
 * @date: 2020/11/10
 * @time: 17:15
 */
@Table("stu")
@Data
@RequiredArgsConstructor
public class Stu {

	@Id
	private Long id;

	private String name;

	private Integer age;

}
