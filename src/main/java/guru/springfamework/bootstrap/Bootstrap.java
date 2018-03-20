package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor v1 = new Vendor();
        Vendor v2 = new Vendor();
        Vendor v3 = new Vendor();

        v1.setName("FruitsAndCo");
        v2.setName("BioCCool");
        v3.setName("Pestipouah");

        vendorRepository.save(v1);
        vendorRepository.save(v2);
        vendorRepository.save(v3);

        System.out.println("Data Vendor Loaded = " + vendorRepository.count() );
    }

    private void loadCustomers() {
        // Bootstrap of the Customer POJO

        Customer c1 = new Customer("John", "Tutu");
        Customer c2 = new Customer("Paul", "Lanvin");
        Customer c3 = new Customer("Suzan", "Lala");
        Customer c4 = new Customer("Amandine", "Lespieds");
        Customer c5 = new Customer("Nicolas", "MacFlanigan");

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);

        System.out.println("Data Customer Loaded = " + customerRepository.count() );
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Category Loaded = " + categoryRepository.count() );
    }
}
