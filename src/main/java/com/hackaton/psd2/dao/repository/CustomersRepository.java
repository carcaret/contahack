package com.hackaton.psd2.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackaton.psd2.dao.model.Customers;

/**
 * @author cesarrodriguezmedina
 *
 */
public interface CustomersRepository extends CrudRepository<Customers, String> {

}
