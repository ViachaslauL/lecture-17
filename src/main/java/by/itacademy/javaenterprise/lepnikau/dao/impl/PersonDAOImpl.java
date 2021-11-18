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
    public Person save(Person person) throws IllegalArgumentException{
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
        Person person = null;

        try {
            person = entityManager.find(Person.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            entityManager.getTransaction().rollback();
        }
        return person;
    }
}
