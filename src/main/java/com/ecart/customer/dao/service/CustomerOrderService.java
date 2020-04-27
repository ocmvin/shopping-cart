package com.ecart.customer.dao.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecart.customer.dao.domain.CustomerOrderDbRecord;
import com.ecart.customer.dao.domain.DiscountSlabDBRecord;
import com.ecart.customer.dao.domain.OrderDetailsDbRecord;
import com.ecart.customer.dao.repository.CustomerOrderRepository;
import com.ecart.customer.dao.repository.CustomerRepository;
import com.ecart.customer.dao.repository.OrderDetailsRepository;
import com.ecart.customer.dto.request.CustomerOrderRequestDTO;
import com.ecart.customer.dto.response.CustomerOrderDTO;
import com.ecart.customer.dto.response.CustomerOrderDetailsDTO;
import com.ecart.customer.exception.RecordNotFound;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class CustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	@Autowired
	private OrderDetailsRepository orderDetailRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private DiscountSlabService discountSlabService;
	
	public CustomerOrderDTO addToBasket(CustomerOrderRequestDTO req) {
		CustomerOrderDbRecord customerOrder=null;
		if(req.getOrderId()==null) { //Create new Order

			customerOrder=new CustomerOrderDbRecord();
			
			customerOrder.setCustomer(customerRepository.getOne(req.getCustomerId()));
			customerOrder=customerOrderRepository.save(customerOrder);
			Set<OrderDetailsDbRecord> customerOrderDetails = saveOrderDetails(req, customerOrder);
			customerOrder.setCustomerOrderDetails(customerOrderDetails);
		
		}else {
			 //Add to existing order
			customerOrder=customerOrderRepository.getOne(req.getOrderId());
			Assert.notNull(customerOrder, "failed to getOrderId: Order not found");
			
			Set<OrderDetailsDbRecord> existingOder =customerOrder.getCustomerOrderDetails();
			for(Long productId : req.getProductIds()) {
			boolean isAdded=existingOder.add(OrderDetailsDbRecord.builder().productId(productId).build());//TODO:Remainng to handle

			}
			customerOrder.setCustomerOrderDetails(existingOder);
		}
		
		CustomerOrderDTO resp =CustomerOrderDTO.builder().orderId(customerOrder.getOrderId()).customerId(customerOrder.getCustomer().getId())
				.customerOrderDetailsList(getCustomerOrderDetails(customerOrder.getCustomerOrderDetails())).build();
		calculateDiscount(customerOrder,resp);
		return resp;
	}

	private void calculateDiscount(CustomerOrderDbRecord customerOrder,CustomerOrderDTO resp) {
	
		//TODO: Calculate product price
		  //ProductDBRecord progetProduct
		//productService.getProduct();
		
		Double totalProductsPrice=customerOrder.getCustomerOrderDetails().stream().
				collect(Collectors.summingDouble(orderDetail-> productService.getProductDBRecord(orderDetail.getProductId()).getPrice()));
		log.info("Total Bucket Price "+totalProductsPrice);
		
		//TODO: Get Discount Slab as per Customer type
			
		List<DiscountSlabDBRecord>  applicableSlabs=discountSlabService.getDiscountSlabForCustomerType(customerOrder.getCustomer().getCustomerTypeId(),totalProductsPrice);
		double discount=0;
		//TODO : Calculate discount
		List<Double > slabWiseAmount =breakAsPerSpab(applicableSlabs,totalProductsPrice);
		int counter =0;
		for(DiscountSlabDBRecord slab: applicableSlabs) {
				
					Double slabDiscount=(slabWiseAmount.get(counter)/100)*slab.getDiscountPercentage();
					discount+=slabDiscount;
					++counter;
		}
		
		//TODO: Prepare response
		
		resp.setTotalAmount(totalProductsPrice);
		resp.setTotalDiscount(discount);
		resp.setDiscountedTotal(totalProductsPrice-discount);
		
		
		
	}
	
	/**
	 * 50
	 * 0 10 5
	 * 10 20 10
	 * 20 30 20
	 *  
	 * @param applicableSlabs
	 * @param totalProductsPrice
	 * @return
	 */
	private List<Double> breakAsPerSpab(List<DiscountSlabDBRecord> applicableSlabs, Double totalProductsPrice) {
		List<Double> slabVal=new ArrayList<Double>();
		applicableSlabs.stream().map(slab -> 
		{
			
			Double diffrence= (totalProductsPrice<slab.getToSlab() || slab.getToSlab()==-1l )? totalProductsPrice-slab.getFromSlab()  :slab.getToSlab()-slab.getFromSlab();
			slabVal.add(diffrence);
			
			return slab.getToSlab()-slab.getFromSlab();
		}).collect(Collectors.toList());

		return slabVal;
	}

	private Set<OrderDetailsDbRecord> saveOrderDetails(CustomerOrderRequestDTO req,CustomerOrderDbRecord customerOrder) {
		
		Set<OrderDetailsDbRecord> customerOrderDetails = new HashSet();
		for(Long productId : req.getProductIds()) {
			boolean isAdded=customerOrderDetails.add(OrderDetailsDbRecord.builder().productId(productId).customerOrder(customerOrder)
							.build());
			Assert.isTrue(isAdded,"can not add duplicate product: invalid request");//Product already present increment the count
		
		}
		this.orderDetailRepository.saveAll(customerOrderDetails);
		return customerOrderDetails;
	}

	private Set<CustomerOrderDetailsDTO> getCustomerOrderDetails(Set<OrderDetailsDbRecord> customerOrderDetails) {
		 Set<CustomerOrderDetailsDTO> customerOrdersDTO=new HashSet<CustomerOrderDetailsDTO>();
		 for(OrderDetailsDbRecord orderDetail : customerOrderDetails) {
				boolean isAdded=customerOrdersDTO.add(CustomerOrderDetailsDTO.builder().
						id(orderDetail.getId()).productId(orderDetail.getProductId()).orderId(orderDetail.getCustomerOrder().getOrderId())
						.itemCount(orderDetail.getItemCount()).build());
				log.info("Is record added "+isAdded+" Record "+customerOrdersDTO);
				}
		return customerOrdersDTO;
	}

	public CustomerOrderDTO findByOrderId(long id) {
		CustomerOrderDbRecord existingOrder=null;
	
				Optional<CustomerOrderDbRecord> existingOrder1=customerOrderRepository.findById(id);
				if( existingOrder1.isEmpty())
				{
					throw new RecordNotFound("Record with Id "+id+" not Found");
				}
		
		return CustomerOrderDTO.builder().orderId(existingOrder.getOrderId()).customerId(existingOrder.getCustomer().getId())
				.customerOrderDetailsList(getCustomerOrderDetails(existingOrder.getCustomerOrderDetails())).build();
	}
	
	
	
	
}
