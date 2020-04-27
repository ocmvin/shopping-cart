package com.ecart.customer.dao.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ecart.customer.dao.domain.CustomerDbRecord;
import com.ecart.customer.dao.repository.CustomerRepository;
import com.ecart.customer.dto.response.CustomerDTO;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDTO getCustomerById(long id) {

		CustomerDbRecord custRec = customerRepository.getOne(id);
		Assert.notNull(custRec, "failed to getCustomerById: Customer not found");

		CustomerDTO resp = CustomerDTO.builder().id(custRec.getId()).name(custRec.getName())
				.customerTypeId(custRec.getCustomerTypeId()).build();
		return resp;
	}
	
	
	public List<CustomerDTO> findAll(){
		
		List<CustomerDbRecord> allCustomers=customerRepository.findAll();
		Assert.notNull(allCustomers, "failed to getCustomerById: Customer not found");
		
		
		return allCustomers.stream().map(CustomerService::getCustomerDTO).collect(Collectors.toList());
	}
	
	private static CustomerDTO getCustomerDTO(CustomerDbRecord custRec)  {
		CustomerDTO resp = CustomerDTO.builder().id(custRec.getId()).name(custRec.getName())
				.customerTypeId(custRec.getCustomerTypeId()).build();
		
		return resp;
	}

}
