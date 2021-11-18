package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Person;

public interface PersonDAO {

    Person save(Person person);

    Person find(Long id);
}
