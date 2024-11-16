package ru.cource.task5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.cource.task5.model.User;
import ru.cource.task5.service.UserService;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/getById/{id}")
    public User getUser(@PathVariable("id") Long id) throws SQLException {
        return userService.getUser(id);
    }

    @GetMapping(value = "/user/getByUsername/{username}")
    public User getUser(@PathVariable("username") String username) throws SQLException {
        return userService.getUser(username);
    }

    @GetMapping(value = "/user/getAll")
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/user/post")
    public HttpStatus createUser(@RequestBody User user) throws SQLException {
        userService.createUser(user);
        return HttpStatus.CREATED;
    }

    @DeleteMapping(value = "/user/deleteByAccount/{username}")
    public HttpStatus deleteUser(@PathVariable("username") String username) throws SQLException {
        userService.deleteUser(username);
        return HttpStatus.OK;
    }
}
