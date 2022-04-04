package com.abcbank.ams.repository;

import com.abcbank.ams.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserCredentialsEmailId(String emailId);

}
