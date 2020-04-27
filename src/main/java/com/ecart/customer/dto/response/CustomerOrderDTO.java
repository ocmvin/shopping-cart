package com.ecart.customer.dto.response;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class CustomerOrderDTO /* extends ResponseBase */{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3019450203077973655L;
	
	private Long orderId;
	
	private Long customerId;

	private Set<CustomerOrderDetailsDTO> customerOrderDetailsList=new HashSet<>();

	private Double totalAmount;
	
	private Double discountedTotal;
	
	private Double totalDiscount;
	
}
