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

        AppUser owner = new AppUser();
        owner.setName("Harry Potter");
        owner.setPassword("admin");
        owner.setUsername("admin");
        owner.addRole(ownerRole);
        userService.saveNewOwner(owner);

        AppUser employee = new AppUser();
        employee.setName("Rob Stark");
        employee.setPassword("rob");
        employee.setUsername("rob");
        employee.addRole(employeeRole);
        userService.saveNewEmployee(employee);

        AppUser employee2 = new AppUser();
        employee2.setName("Hello World");
        employee2.setPassword("hello");
        employee2.setUsername("hello");
        employee2.addRole(employeeRole);
        userService.saveNewEmployee(employee2);

        AppUser employee3 = new AppUser();
        employee3.setName("Jon Snow");
        employee3.setPassword("jon");
        employee3.setUsername("jon");
        employee3.addRole(employeeRole);
        userService.saveNewEmployee(employee3);
    }
}
