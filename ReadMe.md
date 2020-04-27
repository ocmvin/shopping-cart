# Getting Started

### Reference Documentation
Connect to DB by changing details of local DB 
hibernate will create DB schema

Customers can be added as below
insert into customer (id,customer_type_id,name) values(501,2,'test2');

Note : ProductService and DiscountSlabService mocked through program

1: Add product to Basket
POST : http://localhost:8081/v1/customer-order

			Json Request Body :
			{
	
				"customerId":504,
				"productIds":[1,2,6,7]
			}
						
			Response 	:
			{
			    "message": "Success",
			    "status": true,
			    "httpStatus": "OK",
			    "resp": {
			        "orderId": 37,
			        "customerId": 504,
			        "customerOrderDetailsList": [
			            {
			                "id": 69,
			                "productId": 7,
			                "itemCount": 1,
			                "orderId": 37
			            },
			            {
			                "id": 68,
			                "productId": 6,
			                "itemCount": 1,
			                "orderId": 37
			            },
			            {
			                "id": 70,
			                "productId": 1,
			                "itemCount": 1,
			                "orderId": 37
			            },
			            {
			                "id": 71,
			                "productId": 2,
			                "itemCount": 1,
			                "orderId": 37
			            }
			        ],
			        "totalAmount": 14000.0,
			        "discountedTotal": 11800.0,
			        "totalDiscount": 2200.0
			    }
			}		
#############################################################################################################
1: get customer byId

URL :
GET 	http://localhost:8081/v1/customer/500

Response 	:
			{
			    "message": "Success",
			    "status": true,
			    "httpStatus": "OK",
			    "id": 500,
			    "name": "test",
			    "customerTypeId": 1
			}
###############################################################################################################
2: Add Customer Order to Basket
URL : 
POST	http://localhost:8081/v1/customer-order

			Json Request Body :
			{
				
				"customerId":501,
				"productIds":[6,7]
			}
			
			Response 	:
			
			{
			    "message": "Success",
			    "status": true,
			    "httpStatus": "OK",
			    "resp": {
			        "orderId": 34,
			        "customerId": 501,
			        "customerOrderDetailsList": [
			            {
			                "id": 59,
			                "productId": 6,
			                "itemCount": 1,
			                "orderId": 34
			            },
			            {
			                "id": 58,
			                "productId": 7,
			                "itemCount": 1,
			                "orderId": 34
			            }
			        ]
			    }
			}

#############################################################################################
3: Find by order Id 

URL : 
GET : http://localhost:8081/v1/customer-order/27

RESPONSE :
		{
		    "message": "Success",
		    "status": true,
		    "httpStatus": "OK",
		    "resp": {
		        "orderId": 27,
		        "customerId": 500,
		        "customerOrderDetailsList": [
		            {
		                "id": 44,
		                "productId": 1,
		                "itemCount": 1,
		                "orderId": 27
		            },
		            {
		                "id": 43,
		                "productId": 2,
		                "itemCount": 1,
		                "orderId": 27
		            }
		        ]
		    }
		}			