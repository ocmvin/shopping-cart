package com.ecart.customer.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@ToString
public class CustomerDTO extends ResponseBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3019450203077973655L;
	
	private Long id;
	
	private String name;
	
	private Long customerTypeId;
	

}
