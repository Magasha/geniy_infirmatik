package com.example.demo;

import java.util.ArrayList;

public class Theme {
    String name;
    ArrayList<String> comments;

    User user;

    public Theme()
    {
    }

    public Theme(String name, String comment, User user)
    {
        this.name = name;
        this.comments.add(comment);
        Theme theme = new Theme();
        theme.setName(name);
        theme.comments.add(comment);
        user.comments.add(theme);
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

