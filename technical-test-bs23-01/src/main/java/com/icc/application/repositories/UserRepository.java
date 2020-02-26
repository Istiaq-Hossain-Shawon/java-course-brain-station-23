package com.icc.application.repositories;


import com.icc.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    //Optional<User> findByRole(String role);
//    Optional<User> findByUsernameAndRole(String username,String role);
}
