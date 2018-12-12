package hello.repository;

import hello.DBViews.CustomerView;
import hello.model.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerRepository2 {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Customer customer) {
        Session session = entityManager.unwrap(Session.class);
        session.save(customer);
    }

    public Customer getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    public String getDataByView() {
        List<CustomerView> customerviewList = entityManager.createQuery(
                "select cv from CustomerView2 cv", CustomerView.class).getResultList();

        return customerviewList.get(0).getFirstName();
    }

}
