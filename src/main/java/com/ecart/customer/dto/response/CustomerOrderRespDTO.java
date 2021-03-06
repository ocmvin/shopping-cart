package com.ecart.customer.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class CustomerOrderRespDTO extends ResponseBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3019450203077973655L;
	
	@Builder.Default
	private List<CustomerOrderDTO> list=new ArrayList<CustomerOrderDTO>();

	

}
