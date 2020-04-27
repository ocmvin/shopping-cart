package com.ecart.customer.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecart.customer.dao.domain.OrderDetailsDbRecord;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsDbRecord, Long> {


}
