package com.chit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{

    @Autowired
    private CustomerRepository customerRepository;

    private void saveCustomers(){
        customerRepository.save(new Customer("Alice","Smith"));
        customerRepository.save(new Customer("Bob","Smith"));
    }

    public static void main( String[] args )
    {
        ConfigurableApplicationContext ac = new SpringApplicationBuilder(App.class).run(args);
        CustomerRepository customerRepository = ac.getBean(CustomerRepository.class);
//        customerRepository.save(new Customer("Alice","Smith"));
        for(Customer customer : customerRepository.findAll()){
            System.out.println(customer.toString());
        }

    }

//    @Override
//    public void run(String... strings) throws Exception {
//        saveCustomers();
//    }
}
