package com.ecart.customer.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecart.customer.dao.domain.CustomerOrderDbRecord;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrderDbRecord, Long> {


}
