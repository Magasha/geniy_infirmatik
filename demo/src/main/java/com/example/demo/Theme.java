package com.example.demo;

import java.util.ArrayList;

public class Theme {
    String nameT;
    ArrayList<String> comments = new ArrayList<>();
    ArrayList<Integer> indexT = new ArrayList<>();

//public Theme()
//{
//
//}
    public Theme(String nameT, ArrayList<String> comment)
    {
        this.nameT = nameT;
        this.comments.addAll(comment);
    }

    public ArrayList<String> getComment()
    {
        return comments;
    }

    public void setComment(ArrayList<String> comment)
    {
        this.comments = comment;
    }

    public String getNameT()
    {
        return nameT;
    }

    public void setNameT(String nameT)
    {
        this.nameT = nameT;
    }
}

