package hello.controllers;

import hello.model.Customer;
import hello.repository.CustomerRepository;
import hello.repository.CustomerRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired private CustomerRepository2 customerRepository2;

  @Autowired private CustomerRepository customerRepository1;

  @GetMapping("/{id}")
  public String getCustomerName(@PathVariable final Long id) {
    System.out.println("ID: " + id);
    Optional<Customer> customer = customerRepository1.findById(id);
    if (customer.get() == null) {
      return "Output is null";
    }
    String name = customer.get().getFirstName() + " " + customer.get().getLastName();
    System.out.println("customer name: " + name);
    return name;
  }

  @GetMapping("/searchByName")
  public String get(@RequestParam String firstName,@RequestParam String lastName) {
    Customer customer = customerRepository1.findByFirstNameAndLastName(firstName,lastName);
    if (customer == null) {
      return "Output is null";
    }
    String name = customer.getFirstName() + " " + customer.getLastName();
    System.out.println("customer name: " + name);
    return name;
  }

  @GetMapping("/view")
  public String getCustomerNameUsingView() {
    return customerRepository2.getDataByView();
  }

  // TODO: not working
  @PostMapping("/save")
  public String saveCustomer(@RequestBody Customer customer) {
    customerRepository2.save(customer);
    return "Customer info got saved successfully";
  }
}
