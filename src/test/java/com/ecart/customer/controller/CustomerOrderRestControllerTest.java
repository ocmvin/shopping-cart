package com.ecart.customer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.ecart.customer.dao.service.CustomerOrderService;
import com.ecart.customer.dto.request.CustomerOrderRequestDTO;
import com.ecart.customer.dto.response.CustomerOrderDTO;
import com.ecart.customer.dto.response.CustomerOrderDetailsDTO;
import com.ecart.customer.exception.RecordNotFound;
import com.ecart.customer.util.ApplicationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CustomerOrderRestControllerTest {

	
	@Mock
	private CustomerOrderRestController controller;
	@Mock
	private CustomerOrderService customerOrderService;
	
	private MockMvc mvc;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mvc=MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ExceptionAdviceController()).build();
		
	}
	
	@Test
	public void testAddToBasket() throws JsonProcessingException, Exception {

		when(customerOrderService.addToBasket(any(CustomerOrderRequestDTO.class))).thenReturn(getCustomerOrder());
		List<Long> list=new ArrayList<Long>();
		
		mvc.perform(MockMvcRequestBuilders.post("/"+ApplicationConstants.CUSTOMER_ORDER_PATH).
				content(parseToString(CustomerOrderRequestDTO.builder().customerId(100L)
						.productIds(list).build()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	 protected <T> String parseToString(T t) throws JsonProcessingException {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	        return ow.writeValueAsString(t);
	    }
	
	@Test
	public void testGetCustomerOrdersById() throws Exception {
		when(customerOrderService.findByOrderId(Mockito.anyLong())).thenReturn(getCustomerOrder());
		
		mvc.perform(MockMvcRequestBuilders.get("/"+ApplicationConstants.CUSTOMER_ORDER_PATH+"/1").accept(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	
	@Test(expected = AssertionError.class)
	public void testGetCustomerOrdersByIdException() throws Exception {
		when(customerOrderService.findByOrderId(Mockito.anyLong())).thenThrow(new RecordNotFound("Record with Id -1 not Found"));
		
		mvc.perform(MockMvcRequestBuilders.get("/"+ApplicationConstants.CUSTOMER_ORDER_PATH+"/1").accept(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().isNotFound());

	}
	

	private CustomerOrderDTO getCustomerOrder() {
		return CustomerOrderDTO.builder().orderId(1L).customerId(100L)
		.customerOrderDetailsList(getCustomerOrderDetails())
		.totalAmount(5000d)
		.discountedTotal(4900d)
		.totalDiscount(100d).build();
		
				
	}
	
	private Set<CustomerOrderDetailsDTO> getCustomerOrderDetails() {
		 Set<CustomerOrderDetailsDTO> customerOrdersDTO=new HashSet<CustomerOrderDetailsDTO>();
		 
		customerOrdersDTO.add(CustomerOrderDetailsDTO.builder().
						id(500L).productId(600L).orderId(1L)
						.itemCount(1).build());

		return customerOrdersDTO;
	}
}
