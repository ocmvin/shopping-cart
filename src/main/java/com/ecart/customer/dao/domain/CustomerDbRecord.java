package com.ecart.customer.dao.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "customer")
@SequenceGenerator(name = CustomerDbRecord.ID_SEQ,allocationSize = 1 ,sequenceName = CustomerDbRecord.ID_SEQ)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDbRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1305868610970827527L;
	
	protected static final String ID_SEQ = "customer_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	
	private Long customerTypeId;
	

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerOrderDbRecord> customerOrders = new HashSet<>();
	

}
