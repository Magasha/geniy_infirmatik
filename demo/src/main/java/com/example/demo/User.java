package com.example.demo;

import java.util.ArrayList;

public class User
{
    String name;
    int age;

    ArrayList<Theme> themes;

    public User()
    {
        this.age = 16;
        this.name = "Mary";
    }

    public User(String name, int age)
    {
        this.age = age;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return name + " " + age;
    }
}

