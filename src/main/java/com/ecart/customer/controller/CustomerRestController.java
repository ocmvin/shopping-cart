package com.ecart.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.customer.dao.service.CustomerService;
import com.ecart.customer.dao.service.ProductService;
import com.ecart.customer.dto.response.CustomerDTO;
import com.ecart.customer.dto.response.CustomerRespDTO;
import com.ecart.customer.dto.response.ResponseBase;
import com.ecart.customer.util.ApplicationConstants;

import lombok.extern.log4j.Log4j2;

/**
 * http://localhost:8081/v1/customer/500
 *
 *
 */
@RestController
@RequestMapping(ApplicationConstants.CUSTOMER_PATH)
@Log4j2
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBase getCustomerById(@PathVariable(name = "id") long id) {

		CustomerDTO resp = customerService.getCustomerById(id);
		log.info("RESPONSE :" + resp);
		return resp;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBase getAllCustomers() {// TODO add pagination

		List<CustomerDTO> respList = customerService.findAll();
		log.info("RESPONSE :" + respList);
		return CustomerRespDTO.builder().list(respList).build();
	}

}
