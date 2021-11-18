package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Address;

public interface AddressDAO {

    Address save(Address address);

    Address find(Long id);
}
