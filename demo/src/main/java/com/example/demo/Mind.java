package com.example.demo;

import java.util.ArrayList;

public class Mind
{
    User user;
    Theme theme;

    Mind (String nameT, ArrayList<String> comment, String nameU, int age)
    {
        this.theme = new Theme(nameT, comment);
        this.user = new User(nameU, age);
    }

}
