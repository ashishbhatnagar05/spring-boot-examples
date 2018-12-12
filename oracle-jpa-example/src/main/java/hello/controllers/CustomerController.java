package hello.controllers;

import hello.model.Customer;
import hello.repository.CustomerRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class CustomerController {

    @Autowired
    private CustomerRepository2 customerRepository;

    @GetMapping("/{id}")
    public String getCustomerName(@PathVariable final Long id) {
        System.out.println("ID: " + id);
        Customer customer = customerRepository.getById(id);
        if (customer == null) {
            return "Output is null";
        }
        String name = customer.getFirstName() + " " + customer.getLastName();
        System.out.println("customer name: " + name);
        return name;
    }

    @GetMapping("/view")
    public String getCustomerNameUsingView() {
        return customerRepository.getDataByView();
    }

    //TODO: not working
    @PostMapping("/save")
    public String saveCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return "Customer info got saved successfully";
    }

}
