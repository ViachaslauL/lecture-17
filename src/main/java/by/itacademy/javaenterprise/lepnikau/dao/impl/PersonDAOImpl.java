package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.PersonDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class PersonDAOImpl implements PersonDAO {

    private static final Logger LOG = LoggerFactory.getLogger(PersonDAOImpl.class);

    private final EntityManager entityManager;

    public PersonDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Person save(Person person) {
        if (person == null) throw new IllegalArgumentException();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            entityManager.getTransaction().rollback();
        }
        return person;
    }

    @Override
    public Person find(Long id) {
        if (id == null) throw new IllegalArgumentException();

        Person person = null;

        try {
            person = entityManager.find(Person.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return person;
    }

    @Override
    public boolean update(Person person) {
        if (person == null) throw new IllegalArgumentException();

        Person fondedPerson = null;

        try {
            fondedPerson = entityManager.find(Person.class, person.getPersonId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (fondedPerson != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(person);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                entityManager.getTransaction().rollback();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Person person) {
        if (person == null) throw new IllegalArgumentException();

        try {
            person = entityManager.find(Person.class, person.getPersonId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (person != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(person);
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
