package com.mdbytes.app.repository;

import com.mdbytes.app.entity.Alert;
import com.mdbytes.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "alerts")
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findByUser(User user);
}
