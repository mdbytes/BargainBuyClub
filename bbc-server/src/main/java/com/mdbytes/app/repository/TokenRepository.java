package com.mdbytes.app.repository;

import com.mdbytes.app.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            select t from Token t where t.user.id = :id
            """)
    List<Token> findAllValidTokenByUser(Integer id);


    Optional<Token> findByToken(String token);

}
