package com.ecart.customer.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;

import com.ecart.customer.dao.domain.ProductDBRecord;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductService {

	private List<ProductDBRecord> productList=new ArrayList<>();
	
	@PostConstruct
    public void init() {

		productList.add(new ProductDBRecord(1L, "TV Set", 3000.00, "http://placehold.it/200x100"));
		productList.add(new ProductDBRecord(2L, "Game Console", 2000.00, "http://placehold.it/200x100"));
		productList.add(new ProductDBRecord(3L, "Sofa", 1000.00, "http://placehold.it/200x100"));
		productList.add(new ProductDBRecord(4L, "Icecream", 5000.00, "http://placehold.it/200x100"));
		productList.add(new ProductDBRecord(5L, "Beer", 3000.00, "http://placehold.it/200x100"));
		productList.add(new ProductDBRecord(6L, "Phone", 6000.00, "http://placehold.it/200x100"));
		productList.add(new ProductDBRecord(7L, "Watch", 3000.00, "http://placehold.it/200x100"));
    
	}
	
    public Iterable<ProductDBRecord> getAllProductDBRecords(){
    	
    	return productList;
    }

    
    public ProductDBRecord getProductDBRecord(@Min(value = 1L, message = "Invalid product ID.") Long id) {

    	return productList.stream().filter(prod->prod.getId().equals(id)).findFirst().get();
    	
    }

  
}