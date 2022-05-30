package com.winestore.domain.repository.user;

import com.winestore.domain.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(attributePaths = {"roles"})
    User findByEmail(String apiKey);

    @Query(value = "select concat(u.firstName, ' ', u.lastName) " +
        "from User u " +
        "where u.firstName " +
        "like '%:name'")
    List<String> findAllNames(String name);
}
