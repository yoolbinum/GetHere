package com.example.demo.utils;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) throws Exception{
        Role ownerRole = new Role();
        ownerRole.setRole("OWNER");
        roleRepository.save(ownerRole);

        Role employeeRole = new Role();
        employeeRole.setRole("EMPLOYEE");
        roleRepository.save(employeeRole);

        AppUser appUser = new AppUser();
        appUser.setFirstName("Harry");
        appUser.setLastName("Potter");
        appUser.setPassword("admin");
        appUser.setUsername("admin");
        appUser.addRole(ownerRole);
        userService.addRole(appUser, "OWNER");
        userService.saveNewOwner(appUser);
    }
}
