package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.PersonDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Person;
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
class PersonDAOImplTest {

    @Mock
    private EntityTransaction entityTransactionMock;
    @Mock
    private EntityManager entityManagerMock;
    private PersonDAO personDAO;

    @BeforeEach
    void beforeEach() {
        personDAO = new PersonDAOImpl(entityManagerMock);
    }

    @Test
    void saveTest() {
        Person person = new Person();

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        Person actual = personDAO.save(person);

        verify(entityManagerMock, times(1)).persist(person);

        assertNotNull(actual);

        assertEquals(person, actual);
    }

    @Test
    void saveTestWithEntityEqualToNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            personDAO.save(null);
        });

    }

    @Test
    void findTest() {
        Long queryId = 5L;
        Person person = new Person();
        person.setPersonId(queryId);

        Class<Person> anyObject = Mockito.any();

        Long eqValue = Mockito.eq(queryId);

        when(entityManagerMock.find(anyObject, eqValue)).thenReturn(person);

        assertEquals(queryId, personDAO.find(queryId).getPersonId());
    }

    @Test
    void findTestWithWrongId() {
        Long queryId = -1L;

        assertNull(personDAO.find(queryId));
    }
}