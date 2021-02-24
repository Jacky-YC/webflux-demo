package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author YeCheng
 */
@Data
@Table("orders")
@NoArgsConstructor
public class Order {

	@Id
	private Long id;

	/**
	 * 下单数量
	 */
	private Long number;

	/**
	 * 下单产品
	 */
	@Column("product_id")
	private Long productId;

	/**
	 * 订单状态
	 */
	private String status;

	public Order(String status) {
		this.status = status;
	}

}
