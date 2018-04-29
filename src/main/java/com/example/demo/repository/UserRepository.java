package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findUserByUsername(String username);
    Set<AppUser> findAppUsersByRoles(Set<Role> roles);
}
