package com.winestore.domain.repository.user;

import com.winestore.domain.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    @EntityGraph(attributePaths = {"roles"})
    User findByEmail(String apiKey);
}
