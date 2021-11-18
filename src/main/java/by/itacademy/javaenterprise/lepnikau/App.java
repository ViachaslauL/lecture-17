package by.itacademy.javaenterprise.lepnikau;

import by.itacademy.javaenterprise.lepnikau.dao.AddressDAO;
import by.itacademy.javaenterprise.lepnikau.dao.impl.AddressDAOImpl;
import by.itacademy.javaenterprise.lepnikau.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("by.it");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AddressDAO addressDAO = new AddressDAOImpl(entityManager);

        Address address = addressDAO.find(1L);
        System.out.println(address);
    }
}
