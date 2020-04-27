package com.ecart.customer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class CustomerOrderDetailsDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3019450203077973655L;
	
	private Long id;
	
	private Long productId;
	
	private Integer itemCount;

	private Long orderId;

	

}
