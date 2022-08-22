package com.unipi.ipap.springnativecrudgraalvm.controller;

import com.unipi.ipap.springnativecrudgraalvm.dto.CustomerDto;
import com.unipi.ipap.springnativecrudgraalvm.entity.Customer;
import com.unipi.ipap.springnativecrudgraalvm.service.CustomerService;
import com.unipi.ipap.springnativecrudgraalvm.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(
                Mapper.toDto(customerService.addCustomer(Mapper.toEntity(customerDto))),
                HttpStatus.CREATED);
    }

    @PostMapping("/customers/add")
    public ResponseEntity<List<CustomerDto>> addCustomers(@RequestBody List<CustomerDto> customersDto) {
        List<Customer> customerList = customersDto.stream()
                .map(Mapper::toEntity).toList();
        List<CustomerDto> customerDtoList = customerService.addCustomers(customerList).stream()
                .map(Mapper::toDto).toList();
        return new ResponseEntity<>(customerDtoList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getItemById(@PathVariable Long id) {
        return ResponseEntity.of(customerService.getCustomerById(id).map(Mapper::toDto));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> customerDtoList = customerService.getCustomers().stream().map(Mapper::toDto).toList();
        return ResponseEntity.ok(customerDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateItem(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(Mapper.toDto(customerService.modifyCustomer(id, Mapper.toEntity(customerDto))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
