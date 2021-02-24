package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

/**
 * @author YeCheng
 */
@Data
@Entity
@NoArgsConstructor
public class Order {

	@Id
	private Long id = 0L;

	/**
	 * 下单数量
	 */
	private Long number;

	/**
	 * 下单产品
	 */
	private Long productId;

	/**
	 * 订单状态
	 */
	private String status;

	public Order(String status) {
		this.status = status;
	}

}
