package com.ecart.customer.dao.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DiscountSlabDBRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double fromSlab;

    private Double toSlab;

    private Integer discountPercentage;
    
	private Long customerTypeId;

    public DiscountSlabDBRecord(Long id, Double fromSlab,Double toSlab,Integer discountPercentage,Long customerTypeId) {
        this.id = id;
        this.fromSlab = fromSlab;
        this.toSlab = toSlab;
        this.discountPercentage =discountPercentage;
        this.customerTypeId = customerTypeId;
    }

    public DiscountSlabDBRecord() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getFromSlab() {
		return fromSlab;
	}

	public void setFromSlab(Double fromSlab) {
		this.fromSlab = fromSlab;
	}

	public Double getToSlab() {
		return toSlab;
	}

	public void setToSlab(Double toSlab) {
		this.toSlab = toSlab;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Long getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(Long customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

    
   
}