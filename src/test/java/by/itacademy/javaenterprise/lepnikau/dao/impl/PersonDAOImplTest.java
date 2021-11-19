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

        verify(entityManagerMock, never()).persist(null);

    }

    @Test
    void findTest() {
        Long queryId = 5L;
        Person person = new Person();
        person.setPersonId(queryId);

        Class<Person> anyClass = Mockito.any();

        Long eqValue = Mockito.eq(queryId);

        when(entityManagerMock.find(anyClass, eqValue)).thenReturn(person);

        assertEquals(queryId, personDAO.find(queryId).getPersonId());

        verify(entityManagerMock, times(1))
                .find(Mockito.<Class<Person>>any(), Mockito.eq(queryId));
    }

    @Test
    void findTestWithWrongId() {
        Long queryId = -1L;

        assertNull(personDAO.find(queryId));

        assertThrows(IllegalArgumentException.class, () -> {
            personDAO.find(null);
        });
    }

    @Test
    void updateTest() {
        Long queryId = 5L;
        Person person = new Person();
        person.setPersonId(queryId);

        Class<Person> anyClass = Mockito.any();

        Long eqValue = Mockito.eq(queryId);

        when(entityManagerMock.find(anyClass, eqValue)).thenReturn(person);

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        assertTrue(personDAO.update(person));

        verify(entityManagerMock).find(Mockito.<Class<Person>>any(), Mockito.eq(queryId));

        verify(entityManagerMock).persist(person);
    }

    @Test
    void updateTestWithArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () ->{
            personDAO.update(null);
        });
    }

    @Test
    void updateTestNonExistentPerson() {
        Person person = new Person();
        person.setPersonId(-1L);

        assertFalse(personDAO.update(person));
    }

    @Test
    void deleteTest() {
        Long queryId = 5L;
        Person person = new Person();
        person.setPersonId(queryId);

        Class<Person> anyClass = Mockito.any();

        Long eqValue = Mockito.eq(queryId);

        when(entityManagerMock.find(anyClass, eqValue)).thenReturn(person);

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        assertTrue(personDAO.delete(person));

        verify(entityManagerMock).find(Mockito.<Class<Person>>any(), Mockito.eq(queryId));

        verify(entityManagerMock).remove(person);
    }

    @Test
    void deleteTestWithArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () ->{
            personDAO.delete(null);
        });
    }

    @Test
    void deleteTestNonExistentPerson() {
        Person person = new Person();
        person.setPersonId(-1L);

        assertFalse(personDAO.delete(person));
    }
}