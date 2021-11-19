package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.AddressDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressDAOImplTest {

    @Mock
    private EntityTransaction entityTransactionMock;
    @Mock
    private EntityManager entityManagerMock;
    private AddressDAO addressDAO;

    @BeforeEach
    void beforeEach() {
        addressDAO = new AddressDAOImpl(entityManagerMock);
    }

    @Test
    void saveTest() {
        Address address = new Address();

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        Address actual = addressDAO.save(address);

        verify(entityManagerMock, times(1)).persist(address);

        assertNotNull(actual);

        assertEquals(address, actual);
    }

    @Test
    void saveTestWithEntityNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            addressDAO.save(null);
        });
    }

    @Test
    void findTest() {
        Long queryId = 1L;
        Address address = new Address();
        address.setAddressId(queryId);

        Class<Address> anyObject = Mockito.any();

        Long eqValue = Mockito.eq(queryId);

        when(entityManagerMock.find(anyObject, eqValue)).thenReturn(address);

        assertEquals(queryId, addressDAO.find(address).getAddressId());

        verify(entityManagerMock).find(Mockito.<Class<Address>>any(), Mockito.eq(queryId));
    }

    @Test
    void findTestWithWrongId() {
        Address address = new Address();
        address.setAddressId(-1L);

        assertNull(addressDAO.find(address));
    }

    @Test
    void updateTest() {
        Long queryId = 1L;
        Address address = new Address();
        address.setAddressId(queryId);

        Class<Address> anyObject = Mockito.any();

        Long eqValue = Mockito.eq(queryId);

        when(entityManagerMock.find(anyObject, eqValue)).thenReturn(address);

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        assertTrue(addressDAO.update(address));

        verify(entityManagerMock).find(Mockito.<Class<Address>>any(), Mockito.eq(queryId));

        verify(entityManagerMock).persist(address);
    }

    @Test
    void updateTestWithArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () ->{
            addressDAO.update(null);
        });
    }

    @Test
    void updateTestNonExistentAddress() {
        Address address = new Address();
        address.setAddressId(-1L);

        assertFalse(addressDAO.update(address));
    }

    @Test
    void deleteTest() {
        Long queryId = 5L;
        Address address = new Address();
        address.setAddressId(queryId);

        Class<Address> anyClass = Mockito.any();

        Long eqValue = Mockito.eq(queryId);

        when(entityManagerMock.find(anyClass, eqValue)).thenReturn(address);

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        assertTrue(addressDAO.delete(address));

        verify(entityManagerMock).find(Mockito.<Class<Address>>any(), Mockito.eq(queryId));

        verify(entityManagerMock).remove(address);
    }

    @Test
    void deleteTestWithArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () ->{
            addressDAO.delete(null);
        });
    }

    @Test
    void deleteTestNonExistentAddress() {
        Address address = new Address();
        address.setAddressId(-1L);

        assertFalse(addressDAO.delete(address));
    }
}