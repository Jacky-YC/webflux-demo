package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author: YeCheng
 * @date: 2020/11/10
 * @time: 17:15
 */
@Data
@Entity
@RequiredArgsConstructor
public class Stu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer age;

}
