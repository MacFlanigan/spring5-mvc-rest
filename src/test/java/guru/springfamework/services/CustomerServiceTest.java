package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private final Long ID = 1L;
    private final String FIRSTNAME = "Joe";
    private final String LASTNAME = "Satriani";
    private CustomerService customerService;

    private CustomerMapper customerMapper;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() {
        // given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        // then
        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));

        // when
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        // then
        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }

    @Test
    public void createNewCustomer() {

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");
        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        Long id = 1L;
        customerRepository.deleteById(id);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}