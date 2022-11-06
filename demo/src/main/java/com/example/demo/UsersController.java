package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController
{
    private final List<User> users = new ArrayList<User>();

    //curl -X  GET   http://localhost:8080/users
    @GetMapping("users")
    public ResponseEntity<List<User>> getUser()
    {
        return ResponseEntity.ok(users);
    }

    //curl -X  POST  -d Toha  http://localhost:8080/users/add/416
    /*@PostMapping("users/add/{age}")
    public ResponseEntity<Void> addUser(@PathVariable("age") Integer age, @RequestBody String name)
    {
        String basa [] = data.split("/");
        String name = basa[0];
        int age = Integer.parseInt(basa[1]);
        users.add("User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
        return ResponseEntity.accepted().build();
    }*/

    //curl -X POST -H "Content-Type: application/json" -d URAPOBEDAAAAAAAAAAAAAAAAAAAA -d 239  http://localhost:8080/users
    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody String base)
    {
        users.add(new User(base));
        // new User(base.split("&")[0],base.split("&")[1]));
        return ResponseEntity.accepted().build();
    }

    //curl -X  DELETE  http://localhost:8080/users/1
    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index)
    {
        users.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X  GET   http://localhost:8080/users
    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(users.get(index));
    }

    //curl -X PUT -H "Content-Type: application/json" -d 239239  http://localhost:8080/users/0
    @PutMapping("users/{index}")
    public ResponseEntity<Void> updateAge(@PathVariable("index") Integer i, @RequestBody Integer age)
    {
        String name = users.get(i).name;
        users.remove((int) i);
        users.add(new User(name, age));
        return ResponseEntity.accepted().build();
    }
}
