package me.study.springsecuritycos1.repository;

import me.study.springsecuritycos1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
