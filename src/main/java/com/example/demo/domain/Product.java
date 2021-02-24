package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

/**
 * @author YeCheng
 */
@Data
@Entity
@RequiredArgsConstructor
public class Product {

	@Id
	private Long id = 0L;

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
