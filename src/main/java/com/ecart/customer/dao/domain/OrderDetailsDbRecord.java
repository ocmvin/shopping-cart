package com.ecart.customer.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "order_details")
@SequenceGenerator(name = OrderDetailsDbRecord.ID_SEQ, allocationSize = 1, sequenceName = OrderDetailsDbRecord.ID_SEQ)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsDbRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7942377934661402638L;

	protected static final String ID_SEQ = "order_details_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
	private Long id;

	@Column
	private Long productId;
	
	@Column
	@Builder.Default
	private Integer itemCount=1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private CustomerOrderDbRecord customerOrder;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerOrder == null) ? 0 : customerOrder.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsDbRecord other = (OrderDetailsDbRecord) obj;
		if (customerOrder == null) {
			if (other.customerOrder != null)
				return false;
		} else if (!customerOrder.equals(other.customerOrder))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	
	

}
