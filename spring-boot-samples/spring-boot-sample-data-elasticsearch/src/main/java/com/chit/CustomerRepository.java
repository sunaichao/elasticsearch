package com.chit;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */
public interface CustomerRepository extends ElasticsearchRepository<Customer,String> {

    public Customer findByFirstName(String firstName);

    public List<Customer> findByLastName(String lastName);
}
