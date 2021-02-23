package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author YeCheng
 */
@Data
@Entity
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 下单数量
	 */
	private Long number;

	/**
	 * 下单产品
	 */
	@OneToOne
	private Product product;

	/**
	 * 订单状态
	 */
	private String status;

	public Order(String status) {
		this.status = status;
	}

}
