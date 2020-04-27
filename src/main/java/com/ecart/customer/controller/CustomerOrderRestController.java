package com.ecart.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.customer.dao.service.CustomerOrderService;
import com.ecart.customer.dto.request.CustomerOrderRequestDTO;
import com.ecart.customer.dto.response.CustomerOrderDTO;
import com.ecart.customer.dto.response.ResponseBase;
import com.ecart.customer.dto.response.SingleRespDTO;
import com.ecart.customer.util.ApplicationConstants;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(ApplicationConstants.CUSTOMER_ORDER_PATH)
@Log4j2
public class CustomerOrderRestController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBase getCustomerOrdersById(@PathVariable(name = "id") long id) {
		log.info("getCustomerOrdersById "+id);
		CustomerOrderDTO customerDTO=customerOrderService.findByOrderId(id);
		
			var res=new SingleRespDTO<CustomerOrderDTO>();
			res.setResp(customerDTO);
			return res;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseBase addToBasktet(@RequestBody CustomerOrderRequestDTO customerOrderReq) {
		log.info("Adding to basket "+customerOrderReq);
		
		var res=new SingleRespDTO<CustomerOrderDTO>();
		res.setResp(customerOrderService.addToBasket(customerOrderReq));

		return res;
	}

	
}
