package hello.DBViews;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "CUSTOMER_VIEW")
public class CustomerView {

  @Id
  @Column(name="FIRSTNAME")
  private String firstName;
  @Column(name = "LASTNAME")
  private String lastName;

  public CustomerView(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public CustomerView() {}

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
