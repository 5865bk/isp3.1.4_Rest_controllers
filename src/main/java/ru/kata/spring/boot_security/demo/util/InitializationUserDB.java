package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitializationUserDB {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitializationUserDB(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createUsersWithRoles() {

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.saveRole(role1);
        roleService.saveRole(role2);

        Set<Role> set1 = new HashSet<>();
        set1.add(role1);
        Set<Role> set2 = new HashSet<>();
        set2.add(role2);

        User user1 = new User("Борис", "Бов", 50, "admin@mail.ru", passwordEncoder.encode("root"), set1);
        User user2 = new User("Елена", "Ил", 29, "user@mail.ru", passwordEncoder.encode("root"), set2);

        userService.saveUser(user1);
        userService.saveUser(user2);

    }
}