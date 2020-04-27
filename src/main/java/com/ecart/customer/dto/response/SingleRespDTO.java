package com.ecart.customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SingleRespDTO<T> extends ResponseBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1910249277667852073L;
	private T resp;
	
	
}
