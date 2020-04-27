package com.ecart.customer.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ecart.customer.dao.domain.CustomerDbRecord;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDbRecord, Long> , JpaSpecificationExecutor<CustomerDbRecord>{


}
