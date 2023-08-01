package com.example.webshop.repository;

import com.example.webshop.domain.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Address save(Address toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }

    public Optional<Address> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    public void delete(Address address){
        entityManager.remove(address);
    }

    public List<Address> findByZipcode(Integer zipcode){
        return entityManager.createQuery("SELECT a FROM Address a WHERE a.zipcode = :zipcode",Address.class)
                .setParameter("zipcode",zipcode)
                .getResultList();
    }

    public List<Address> findAddressByCity(String city){
        return entityManager.createQuery("SELECT a FROM Address a WHERE a.city = :city", Address.class)
                .setParameter("city",city)
                .getResultList();
    }
}
