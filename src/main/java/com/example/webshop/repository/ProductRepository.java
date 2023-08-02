package com.example.webshop.repository;

import com.example.webshop.domain.Address;
import com.example.webshop.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void delete(Product product) {
        entityManager.remove(product);
    }

    public Product save(Product toSave) {
        entityManager.persist(toSave);
        return toSave;
    }


    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }
}
