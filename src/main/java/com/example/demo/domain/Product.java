package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @author YeCheng
 */
@Data
@Entity
@RequiredArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 库存
	 */
	private Long stock = 0L;

	/**
	 * 备注
	 */
	private String remark;
}
