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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "customer_oder")
@SequenceGenerator(name = CustomerOrderDbRecord.ID_SEQ, allocationSize = 1, sequenceName = CustomerOrderDbRecord.ID_SEQ)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderDbRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7942377934661402638L;

	protected static final String ID_SEQ = "customer_order_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
	private Long orderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id",nullable = false)
	private CustomerDbRecord customer;
	
	@OneToMany(mappedBy ="customerOrder",fetch = FetchType.LAZY)
	@Builder.Default
	private Set<OrderDetailsDbRecord> customerOrderDetails = new HashSet<>();

}
