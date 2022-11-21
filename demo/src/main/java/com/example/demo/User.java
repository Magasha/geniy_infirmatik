package com.example.demo;

public class User
{
    String nameU;
    int age;

    public User()
    {
        this.age = 16;
        this.nameU = "Mary";
    }

    public User(String nameU, int age)
    {
        this.age = age;
        this.nameU = nameU;
    }

    public String getNameU()
    {
        return nameU;
    }

    public void setNameU(String nameU)
    {
        this.nameU = nameU;
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
        return nameU + " " + age;
    }
}

