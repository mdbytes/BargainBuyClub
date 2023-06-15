package com.mdbytes.app.repository;

import com.mdbytes.app.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "stores")
public interface StoreRepository extends JpaRepository<Store, Integer> {

}
