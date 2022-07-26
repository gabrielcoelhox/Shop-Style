package com.shopstyle.msbffshop.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.msbffshop.clients.CatalogClient;
import com.shopstyle.msbffshop.clients.CustomerClient;
import com.shopstyle.msbffshop.clients.OrderClient;
import com.shopstyle.msbffshop.clients.PaymentClient;
import com.shopstyle.msbffshop.clients.catalog.dto.CategoryDTO;
import com.shopstyle.msbffshop.clients.catalog.dto.ProductDTO;
import com.shopstyle.msbffshop.clients.customer.dto.AddressDTO;
import com.shopstyle.msbffshop.clients.customer.dto.AddressFormDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerChangePasswordDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerFormDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerLoginDTO;
import com.shopstyle.msbffshop.clients.enums.Status;
import com.shopstyle.msbffshop.clients.order.dto.OrderDTO;
import com.shopstyle.msbffshop.clients.order.dto.OrderFormDTO;
import com.shopstyle.msbffshop.clients.payment.dto.PaymentDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bffshop")
@RequiredArgsConstructor
public class BffShopController {

	private final CustomerClient customerClient;
	
	private final CatalogClient catalogClient;
	
	private final PaymentClient paymentClient;
	
	private final OrderClient orderClient;

	@GetMapping("/v1/customers/{id}")
	@ApiOperation(value= "Customer by id")	
	public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Long id) {
		return new ResponseEntity<>(customerClient.getCustomer(id), HttpStatus.OK);
	}
	
	@PostMapping("/v1/customers")
	@ApiOperation(value= "Insert a new Customer")	
	public ResponseEntity<CustomerDTO> insertCustomer(@RequestBody CustomerFormDTO form) {
		return new ResponseEntity<>(customerClient.saveCustomer(form), HttpStatus.CREATED);
	}
	
	@PostMapping("/v1/login")
	@ApiOperation(value= "Customer Login")	
	public ResponseEntity<CustomerDTO> loginCustomer(@RequestBody CustomerLoginDTO form) {
		return new ResponseEntity<>(customerClient.loginCustomer(form), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/v1/customers/{id}")
	@ApiOperation(value= "Update Customer")	
	public ResponseEntity<CustomerDTO> updateCustomerById(@RequestBody CustomerFormDTO form, @PathVariable Long id) {
		return new ResponseEntity<>(customerClient.updateCustomerById(id, form), HttpStatus.OK);
	}
	
	@PutMapping("/v1/customers/{id}/password")
	@ApiOperation(value= "Change Password")	
	public ResponseEntity<CustomerDTO> changePassword(@RequestBody CustomerChangePasswordDTO passwordDto, @PathVariable Long id) {
		return new ResponseEntity<>(customerClient.changePasswordCustomer(passwordDto, id), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/v1/addresses")
	@ApiOperation(value= "Insert a new Address")	
	public ResponseEntity<AddressDTO> insertAddress(@RequestBody AddressFormDTO form){
		return new ResponseEntity<>(customerClient.saveAddress(form), HttpStatus.CREATED);
	}
	
	@PutMapping("/v1/addresses/{id}")
	@ApiOperation(value= "Update Address")	
	public ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long id, @RequestBody AddressFormDTO form){
		return new ResponseEntity<>(customerClient.updateAddressById(id, form), HttpStatus.OK);
	}
	
	@DeleteMapping("/v1/addresses/{id}")
	@ApiOperation(value= "Delete Address")
	public ResponseEntity<Void> deleteAddressById(@PathVariable Long id){
		customerClient.deleteAddressById(id); 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/v1/products")
	@ApiOperation(value= "Find all Products")	
	public ResponseEntity<List<ProductDTO>> findAllProducts() {
		return new ResponseEntity<>(catalogClient.findAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/v1/products/{id}")
	@ApiOperation(value= "Find Product")	
	public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
		return new ResponseEntity<>(catalogClient.findProductById(id), HttpStatus.OK);
	}
	
	@GetMapping("/v1/categories")
	@ApiOperation(value= "Find all Categories")	
	public ResponseEntity<List<CategoryDTO>> findAllCategories() {
		return new ResponseEntity<>(catalogClient.findAllCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/v1/categories/{id}/products")
	@ApiOperation(value= "Find Product by id Category")	
	public ResponseEntity<List<ProductDTO>> findListProductsByIdCategory(@PathVariable Long id) {
		return new ResponseEntity<>(catalogClient.findProductsByIdCategory(id), HttpStatus.OK);
	}
	
	@GetMapping("/v1/payments")
	@ApiOperation(value= "Find all Payments")
	public ResponseEntity<List<PaymentDTO>> findAllPayments() {
		return new ResponseEntity<>(paymentClient.findAllPayments(), HttpStatus.OK);
	}
	
	@PostMapping("/v1/orders")
	@ApiOperation(value= "Insert a new Order")	
	public ResponseEntity<OrderDTO> insertOrder(@RequestBody OrderFormDTO form){
		return new ResponseEntity<>(orderClient.saveOrder(form), HttpStatus.CREATED);
	}
	
	@GetMapping("/v1/orders/customers/{customerId}")
	@ApiOperation(value= "Find Order by Customer")
	public ResponseEntity<List<OrderDTO>> findOrdersByCustomerId(@PathVariable Long customerId, @RequestParam(required = false) LocalDate startDate, 
			@RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) Status status) {
		return new ResponseEntity<>(orderClient.findOrdersByCustomerId(customerId, startDate, endDate, status), HttpStatus.OK);
	}
}
