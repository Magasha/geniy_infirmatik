package com.example.demo;

import java.util.ArrayList;

public class Theme {
    String name;
    ArrayList<String> comments;

    ArrayList<User> users;

    public Theme()
    {
    }

    public Theme(String name, String comment, User user)
    {
        this.name = name;
        this.users.add(user);
        Theme theme = new Theme();
        theme.setName(name);
        this.users.get(0).themes.add(theme);
        this.users.get(0).themes.get(0).comments.add(comment);
    }

    public ArrayList<String> getComment()
    {
        return comments;
    }

    public void setComment(ArrayList<String> comment)
    {
        this.comments = comment;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

