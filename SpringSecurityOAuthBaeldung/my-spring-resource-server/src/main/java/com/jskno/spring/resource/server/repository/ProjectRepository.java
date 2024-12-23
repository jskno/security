package com.jskno.spring.resource.server.repository;

import com.jskno.spring.resource.server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
