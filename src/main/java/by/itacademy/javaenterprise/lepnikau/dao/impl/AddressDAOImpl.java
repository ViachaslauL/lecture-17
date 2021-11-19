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
    public Address save(Address address) {
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
    public Address find(Address address) {
        if (address == null) throw new IllegalArgumentException();

        try {
            address = entityManager.find(Address.class, address.getAddressId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return address;
    }

    @Override
    public boolean update(Address address) {
        if (address == null) throw new IllegalArgumentException();

        Address foundedAddress = null;

        try {
            foundedAddress = entityManager.find(Address.class, address.getAddressId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (foundedAddress != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(address);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                entityManager.getTransaction().rollback();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Address address) {
        if (address == null) throw new IllegalArgumentException();

        try {
            address = entityManager.find(Address.class, address.getAddressId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (address != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(address);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                entityManager.getTransaction().rollback();
            }
        }
        return false;
    }
}
