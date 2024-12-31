package com.jskno.iocode.auth.server.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRegisteredClientRepository extends CrudRepository<CustomRegisteredClient, String> {

    Optional<CustomRegisteredClient> findByClientId(String clientId);

    List<CustomRegisteredClient> findAll();

}
