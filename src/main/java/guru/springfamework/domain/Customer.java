package guru.springfamework.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String customer_url;

    public Customer(){

    }

    public Customer(String firstname, String lastname, String customer_url) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.customer_url = customer_url;
    }
}
