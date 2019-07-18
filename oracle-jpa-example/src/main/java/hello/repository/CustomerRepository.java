package hello.repository;

import java.util.List;

import hello.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByLastName(String lastName);
    Customer findByFirstNameAndLastName(String firstName,String lastName);
    Customer findById(Integer id);
}
