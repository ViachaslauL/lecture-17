package by.itacademy.javaenterprise.lepnikau;

import by.itacademy.javaenterprise.lepnikau.dao.AddressDAO;
import by.itacademy.javaenterprise.lepnikau.dao.impl.AddressDAOImpl;
import by.itacademy.javaenterprise.lepnikau.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("by.it");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AddressDAO addressDAO = new AddressDAOImpl(entityManager);

        Address address = new Address();
        address.setAddressId(1L);

        Address foundedAddress = addressDAO.find(address);
        LOG.info(foundedAddress.toString());

        foundedAddress.setStreet("Some street");
        foundedAddress.setBuildingNumber(44);
        foundedAddress.setFlatNumber(55);

        String result;
        if (addressDAO.delete(foundedAddress)) {
            result = "true";
        } else {
            result = "false";
        }
        LOG.info(result);

        foundedAddress = addressDAO.find(address);
        if (foundedAddress != null) {
            LOG.info(foundedAddress.toString());
        }
    }
}
