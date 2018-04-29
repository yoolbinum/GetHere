package com.example.demo.utils;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.model.Transfer;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.TransferService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    private TransferService transferService;

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
        employee.setNumHours(10);
        employee.setHourlyWage(new BigDecimal(8.50));
        employee.setTotalWage(new BigDecimal(80.50));
        userService.saveNewEmployee(employee);

        AppUser employee2 = new AppUser();
        employee2.setName("Hello World");
        employee2.setPassword("hello");
        employee2.setUsername("hello");
        employee2.addRole(employeeRole);
        employee2.setNumHours(10);
        employee2.setHourlyWage(new BigDecimal(8.50));
        employee2.setTotalWage(new BigDecimal(80.50));
        userService.saveNewEmployee(employee2);

        AppUser employee3 = new AppUser();
        employee3.setName("Jon Snow");
        employee3.setPassword("jon");
        employee3.setUsername("jon");
        employee3.addRole(employeeRole);
        employee3.setNumHours(10);
        employee3.setHourlyWage(new BigDecimal(8.50));
        employee3.setTotalWage(new BigDecimal(80.50));
        userService.saveNewEmployee(employee3);

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);

        Transfer transfer = new Transfer();
        transfer.setAmount(new BigDecimal(80.5));
        transfer.setDate(strDate);
        transfer.setReciever("Jon Snow");
        transfer.setTransactionIdentifier("381228649430011");
        transferService.saveTransfer(transfer);

        Transfer transfer2 = new Transfer();
        transfer2.setAmount(new BigDecimal(80.5));
        transfer2.setDate(strDate);
        transfer2.setReciever("Hello World");
        transfer2.setTransactionIdentifier("466491086113330");
        transferService.saveTransfer(transfer2);

        Transfer transfer3 = new Transfer();
        transfer3.setAmount(new BigDecimal(80.5));
        transfer3.setDate(strDate);
        transfer3.setReciever("Rob Stark");
        transfer3.setTransactionIdentifier("101685605152686");
        transferService.saveTransfer(transfer3);
    }
}
