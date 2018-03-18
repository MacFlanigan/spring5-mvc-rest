package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading customer data");
        System.out.println(customerRepository.findAll().size());

        // setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run(); // load data

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstname() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        // save original
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstname());
        assertThat(originalFirstname, not(equalTo(updatedCustomer.getFirstname())));
        assertThat(originalLastname,equalTo(updatedCustomer.getLastname()));
    }

    @Test
    public void patchCustomerUpdateLastname() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        // save original
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastname());
        assertThat(originalFirstname, equalTo(updatedCustomer.getFirstname()));
        assertThat(originalLastname, not(equalTo(updatedCustomer.getLastname())));
    }

    private Long getCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers found: " + customers.size());

        // return first id
        return customers.get(0).getId();
    }
}
