package com.ecart.customer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecart.customer.dao.service.ProductService;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockServletContext.class)

class CustomerServiceApplicationTests {

	
	private ProductService productService;
	
	@Test
	void contextLoads() {
		
		productService= PowerMockito.mock(ProductService.class);
	}

}
