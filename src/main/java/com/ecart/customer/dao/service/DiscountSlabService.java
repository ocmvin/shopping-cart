package com.ecart.customer.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;

import com.ecart.customer.dao.domain.DiscountSlabDBRecord;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DiscountSlabService {

 
	private List<DiscountSlabDBRecord> discSlabService=new ArrayList<>();
	
	@PostConstruct
    public void init() {
		
        discSlabService.add(new DiscountSlabDBRecord(1L, 0.0, 5000.00,0,1L));
        discSlabService.add(new DiscountSlabDBRecord(2L, 5000.00,10000.00,10,1L));
        discSlabService.add(new DiscountSlabDBRecord(3L, 10000.00, -1.0,20,1L));
        
        discSlabService.add(new DiscountSlabDBRecord(4L, 0.0, 4000.00,10,2L));
        discSlabService.add(new DiscountSlabDBRecord(5L, 4000.00,8000.00,15,2L));
        discSlabService.add(new DiscountSlabDBRecord(6L, 8000.00, 12000.00,20,2L));
        discSlabService.add(new DiscountSlabDBRecord(7L, 12000.00, -1.0,20,2L));
    }
	
    public Iterable<DiscountSlabDBRecord> getDiscountSlab(){
    	
    	return discSlabService;
    }

    
    public DiscountSlabDBRecord getDiscountSlab(@Min(value = 1L, message = "Invalid product ID.") Long id) {

    	return discSlabService.stream().filter(slab->slab.getId().equals(id)).findFirst().get();
    	
    }

    public List<DiscountSlabDBRecord> getDiscountSlabForCustomerType(@Min(value = 1L, message = "Invalid slab ID.") Long customerTypeId,Double totalAmount) {

    	return discSlabService.stream().filter(slab->{
    		return slab.getCustomerTypeId().equals(customerTypeId) && (slab.getFromSlab()<totalAmount);
    		}).collect(Collectors.toList());
    	
    }

    
    
    
}