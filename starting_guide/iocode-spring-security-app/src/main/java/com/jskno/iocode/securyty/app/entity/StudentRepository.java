package com.jskno.iocode.securyty.app.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findAll();

}
