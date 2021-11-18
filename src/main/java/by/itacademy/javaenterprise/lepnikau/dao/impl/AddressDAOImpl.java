package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.AddressDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class AddressDAOImpl implements AddressDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AddressDAOImpl.class);

    private final EntityManager entityManager;

    public AddressDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Address save(Address address) throws IllegalArgumentException {
        if (address == null) throw new IllegalArgumentException();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            entityManager.getTransaction().rollback();
        }
        return address;
    }

    @Override
    public Address find(Long id) {
        Address address = null;

        try {
            address = entityManager.find(Address.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            entityManager.getTransaction().rollback();
        }
        return address;
    }
}
