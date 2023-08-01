package com.example.webshop.repository;

import com.example.webshop.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Customer save(Customer toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    public void delete(Customer customer){
        entityManager.remove(customer);
    }

    public List<Customer> findByName(String name){
        return entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :name",Customer.class)
                .setParameter("name",name)
                .getResultList();
    }


    public List<Customer> findByCity(String city){
        return entityManager.createQuery("SELECT c FROM Customer c JOIN Address a WHERE c.address.city = :city",Customer.class)
                .setParameter("city",city)
                .getResultList();
    }
}
