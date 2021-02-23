package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 * @author YeCheng
 */
@Data
@Entity
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
	@SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
	private Long id;

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
