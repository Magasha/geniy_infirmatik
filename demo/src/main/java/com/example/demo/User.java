package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class User
{
    String name;
    int age;


    public User()
    {
        this.age = 17;
        this.name = "Ivan";
    }
    public User(String name, int age)
    {
        this.age = age;
        this.name = name;
    }

    public User(String name)
    {
        this.age = Integer.parseInt(name.split("&")[1]);
        this.name = name.split("&")[0];
    }

    public User(String name, String age)
    {
        this.name = name;
        this.age = Integer.parseInt(age);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

