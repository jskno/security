package com.jskno.iocode.securyty.app.security.entity;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
