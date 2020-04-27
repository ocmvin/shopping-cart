package com.ecart.customer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecart.customer.dao.service.CustomerService;
import com.ecart.customer.dto.response.CustomerDTO;
import com.ecart.customer.exception.RecordNotFound;
import com.ecart.customer.util.ApplicationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CustomerRestControllerTest {

	
	@Mock
	private CustomerRestController controller;
	@Mock
	private CustomerService customerService;
	
	private MockMvc mvc;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mvc=MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ExceptionAdviceController()).build();
		
	}
	
	@Test
	public void testGetAllCustomers() throws JsonProcessingException, Exception {

		when(customerService.findAll()).thenReturn(getAll());
	
		
		mvc.perform(MockMvcRequestBuilders.get("/"+ApplicationConstants.CUSTOMER_PATH)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	public void testGetCustomerById() throws Exception {
		when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(getCustomer(1l,1l));
		
		mvc.perform(MockMvcRequestBuilders.get("/"+ApplicationConstants.CUSTOMER_PATH+"/1").accept(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	
	@Test(expected = AssertionError.class)
	public void testGetCustomerByIdException() throws Exception {
		when(customerService.getCustomerById(Mockito.anyLong())).thenThrow(new RecordNotFound("Record with Id -1 not Found"));
		
		mvc.perform(MockMvcRequestBuilders.get("/"+ApplicationConstants.CUSTOMER_PATH+"/1").accept(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().isNotFound());

	}
	
	
	private List<CustomerDTO> getAll(){
		List<CustomerDTO> list =new ArrayList<>();

		 for (int i = 1; i <=2; i++) {
			 list.add( getCustomer(1l,1l));
		}
		return list;
	}

	private CustomerDTO getCustomer(long custId,long custTypeId) {
		return CustomerDTO.builder().id(custId).name("test")
		.customerTypeId(custTypeId).build();
		
				
	}
}
