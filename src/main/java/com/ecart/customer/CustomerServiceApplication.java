package com.ecart.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecart.customer.dao.domain.DiscountSlabDBRecord;
import com.ecart.customer.dao.domain.ProductDBRecord;
import com.ecart.customer.dao.service.DiscountSlabService;
import com.ecart.customer.dao.service.ProductService;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	/*
	 @Bean
	    CommandLineRunner runner(ProductService productService) {
	        return args -> {
	            productService.save(new ProductDBRecord(1L, "TV Set", 3000.00, "http://placehold.it/200x100"));
	            productService.save(new ProductDBRecord(2L, "Game Console", 2000.00, "http://placehold.it/200x100"));
	            productService.save(new ProductDBRecord(3L, "Sofa", 1000.00, "http://placehold.it/200x100"));
	            productService.save(new ProductDBRecord(4L, "Icecream", 5000.00, "http://placehold.it/200x100"));
	            productService.save(new ProductDBRecord(5L, "Beer", 3000.00, "http://placehold.it/200x100"));
	            productService.save(new ProductDBRecord(6L, "Phone", 6000.00, "http://placehold.it/200x100"));
	            productService.save(new ProductDBRecord(7L, "Watch", 3000.00, "http://placehold.it/200x100"));
	        };
	    }
	 
	 
	 @Bean
	    CommandLineRunner runner(DiscountSlabService discSlabService) {
	        return args -> {
	            discSlabService.save(new DiscountSlabDBRecord(1L, 0.0, 5000.00,0,1L));
	            discSlabService.save(new DiscountSlabDBRecord(2L, 5000.00,10000.00,10,1L));
	            discSlabService.save(new DiscountSlabDBRecord(3L, 10000.00, -1.0,20,1L));
	            
	            discSlabService.save(new DiscountSlabDBRecord(4L, 0.0, 4000.00,10,2L));
	            discSlabService.save(new DiscountSlabDBRecord(5L, 4000.00,8000.00,15,2L));
	            discSlabService.save(new DiscountSlabDBRecord(6L, 8000.00, 12000.00,20,2L));
	            discSlabService.save(new DiscountSlabDBRecord(7L, 12000.00, -1.0,20,2L));
	        };
	    }
	*/
}
