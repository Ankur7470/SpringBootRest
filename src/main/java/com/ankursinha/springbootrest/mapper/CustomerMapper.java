package com.ankursinha.springbootrest.mapper;

import com.ankursinha.springbootrest.dto.CustomerRequest;
import com.ankursinha.springbootrest.dto.CustomerResponse;
import com.ankursinha.springbootrest.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPassword(request.password());
        return customer;
    }
}

//@Component
//public class CustomerMapper {
//
//    // Convert CustomerRequest to Customer entity
//    public Customer toEntity(CustomerRequest request) {
//        return new Customer(
//                null, // ID will be generated automatically
//                request.firstName(),
//                request.lastName(),
//                request.email(),
//                request.password()
//        );
//    }
//
//    // Convert Customer entity to CustomerResponse
//    public CustomerResponse toResponse(Customer customer) {
//        return new CustomerResponse(
//                customer.getFirstName(),
//                customer.getLastName(),
//                customer.getEmail()
//        );
//    }
//}
