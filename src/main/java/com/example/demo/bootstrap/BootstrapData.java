package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootstrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Check to see if sample customers are already populated
        //Assuming provided database will either have one customer already in it or be empty.
        if (customerRepository.count() <= 1){
            //All sample customers will come from Florida cause that's where I come from
            Division florida = divisionRepository.findById(9L).orElseThrow();

            //sample customer one
            Customer customerOne = new Customer();
            customerOne.setFirstName("Sample");
            customerOne.setLastName("One");
            customerOne.setAddress("111 sample st");
            customerOne.setPostal_code("11111");
            customerOne.setPhone("1111111111");
            customerOne.setDivision(florida);

            //sample customer two
            Customer customerTwo = new Customer();
            customerTwo.setFirstName("Sample");
            customerTwo.setLastName("Two");
            customerTwo.setAddress("222 sample st");
            customerTwo.setPostal_code("22222");
            customerTwo.setPhone("2222222222");
            customerTwo.setDivision(florida);

            //sample customer three
            Customer customerThree = new Customer();
            customerThree.setFirstName("Sample");
            customerThree.setLastName("Three");
            customerThree.setAddress("333 sample st");
            customerThree.setPostal_code("33333");
            customerThree.setPhone("3333333333");
            customerThree.setDivision(florida);

            //sample customer four
            Customer customerFour = new Customer();
            customerFour.setFirstName("Sample");
            customerFour.setLastName("Four");
            customerFour.setAddress("444 sample st");
            customerFour.setPostal_code("44444");
            customerFour.setPhone("4444444444");
            customerFour.setDivision(florida);

            //sample customer five
            Customer customerFive = new Customer();
            customerFive.setFirstName("Sample");
            customerFive.setLastName("Five");
            customerFive.setAddress("555 sample st");
            customerFive.setPostal_code("55555");
            customerFive.setPhone("5555555555");
            customerFive.setDivision(florida);

            //save sample customers to database
            customerRepository.save(customerOne);
            customerRepository.save(customerTwo);
            customerRepository.save(customerThree);
            customerRepository.save(customerFour);
            customerRepository.save(customerFive);

        }
    }
}
