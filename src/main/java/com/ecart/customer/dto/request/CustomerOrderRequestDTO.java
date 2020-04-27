package com.ecart.customer.dto.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderRequestDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4491704031246954398L;

    //@Nullable
	private Long orderId;
	
	private Long customerId;
	
	private List<Long> productIds=new ArrayList<Long>();
	
	
}
