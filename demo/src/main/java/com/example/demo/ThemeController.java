package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ThemeController {

    private List<Theme> themes = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    //на 3

    //curl -X POST 'http://localhost:8080/themes/' -H 'Content-Type: application/json' --data-raw '{"nameT":"Inform", "comment":["Class", "Okey"],"nameU":"Mary","age":17}'
    @PostMapping("themes")
    public ResponseEntity<Void> addTheme (@RequestBody Mind mind)
    {
        int a = 0, b = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).nameU.equals(mind.user.nameU) && users.get(i).age == mind.user.age) {
                a++;
                b = i;
            }
        }
        if(a == 0)
        {
            users.add(mind.user);
            b = users.size() - 1;
        }
        a = 0;
        for (int i = 0; i < themes.size(); i++) {
            if (themes.get(i).nameT.equals(mind.theme.nameT))
            {
                for (String str : mind.theme.comments)
                {
                    themes.get(i).comments.add(str);
                    themes.get(i).indexT.add(b);
                }
                a++;
            }
        }
        if(a == 0) {
            themes.add(mind.theme);
            for (int i = 0; i < mind.theme.comments.size(); i++)
                themes.get(themes.size()-1).indexT.add(b);
        }
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE  http://localhost:8080/themes/{index}
    @DeleteMapping("themes/{index}")
    public ResponseEntity<Void> deleteTheme(@PathVariable("index") Integer index)
    {
        themes.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X GET http://localhost:8080/themes/
    @GetMapping("themes")
    public ResponseEntity<List<Theme>> getTheme()
    {
        return ResponseEntity.ok(themes);
    }

    //curl -X GET http://localhost:8080/themes/users
    @GetMapping("themes/users")
    public ResponseEntity<List<User>> getUsers()
    {
        return ResponseEntity.ok(users);
    }

    //curl -X PUT -H "Content-Type: application/json" -d {nameT} http://localhost:8080/themes/{index}
    @PutMapping("themes/{index}")
    public ResponseEntity<Void> updateTheme(@PathVariable("index") Integer i, @RequestBody String name)
    {
        (themes.get(i)).setNameT(name);
        return ResponseEntity.accepted().build();
    }

    // curl -X  GET  http://localhost:8080/themes/count
    @GetMapping("themes/count")
    public ResponseEntity<Integer> countThemes()
    {
        return ResponseEntity.ok(themes.size());
    }

    //curl -X DELETE http://localhost:8080/themes/
    @DeleteMapping("themes")
    public ResponseEntity<Void> deleteThemes()
    {
        themes = new ArrayList<>();
        return ResponseEntity.noContent().build();
    }

    //curl -X GET http://localhost:8080/themes/{index}
    @GetMapping("themes/{index}")
    public ResponseEntity<Theme> getTheme(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(themes.get(index-1));
    }


    //на 4

    //curl -X POST -H 'Content-Type: application/json' --data-raw 'text' 'http://localhost:8080/topic/0/create'
    @PostMapping("themes/{index}/comments")
    public ResponseEntity<Void> addComment(@PathVariable("index") Integer index, @RequestBody String text)
    {
        (themes.get(index - 1)).comments.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE  http://localhost:8080/themes/{index}/comments/{index}
    @DeleteMapping("themes/{index}/comments/{index1}")
    public ResponseEntity<Void> deleteComment(@PathVariable("index") Integer index, @PathVariable("index1") Integer index1)
    {
        (themes.get(index - 1)).comments.remove((int) index1);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d {text} http://localhost:8080/themes/{index}/comments/{index}
    @PutMapping("themes/{index}/comments/{index1}")
    public ResponseEntity<Void> updateComment(@PathVariable("index") Integer index, @PathVariable("index1") Integer index1, @RequestBody String text)
    {
        (themes.get(index - 1)).comments.remove((int) index1);
        (themes.get(index - 1)).comments.add(index1, text);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET -H "Content-Type: application/json" -d  http://localhost:8080/themes/{index}/comments
    @GetMapping("themes/{index}/comments")
    public ResponseEntity<ArrayList<String>> getComments(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(themes.get(index - 1).getComment());
    }

    //curl -X GET -H "Content-Type: application/json" -d  http://localhost:8080/themes/{index}/b
    @GetMapping("themes/{index}/b")
    public ResponseEntity<ArrayList<Integer>> getb(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(themes.get(index - 1).indexT);
    }


    //на 5

    //curl -X GET http://localhost:8080/themes/users/{index}
    @GetMapping("themes/users/{index}")
    public ResponseEntity<List<String>> getUsers(@PathVariable("index") Integer index)
    {
        ArrayList<String> comment = new ArrayList<String>();
        for(int j = 0; j < themes.size(); j++)
        {
            for (int i = 0; i < themes.get(j).indexT.size(); i++)
            {
                if(themes.get(j).indexT.get(i) == index)
                    comment.add((themes.get(j).comments.get(i)+ " from " + themes.get(j).nameT));
            }
        }
        return ResponseEntity.ok(comment);
    }

    //curl -X PUT -H "Content-Type: application/json" -d {text} http://localhost:8080/themes/users/{index}/comments/{index}
    @PutMapping("themes/users/{index}/comments/{index1}")
    public ResponseEntity<Void> updateUsersComment(@PathVariable("index") Integer index, @PathVariable("index1") Integer index1, @RequestBody String text)
    {
        for(int j = 0; j < themes.size(); j++)
        {
            int a = 0;
            for (int i = 0; i < themes.get(j).indexT.size(); i++)
            {
                if(themes.get(j).indexT.get(i) == index)
                {
                    if(a == index1)
                    {
                        themes.get(j).comments.remove((int) i);
                        themes.get(j).comments.add(i, text);
                    }
                    a++;
                }

            }
        }
        return ResponseEntity.accepted().build();
    }


    //curl -X DELETE  http://localhost:8080/themes/users/{index}/deleteComments
    @DeleteMapping("themes/users/{index}/deleteComments")
    public ResponseEntity<Void> deleteUsersComments(@PathVariable("index") Integer index)
    {
        for(int j = themes.size()-1; j > 0; j--)
        {
            int a = 0;
            for (int i = themes.get(j).indexT.size()-1; i > 0; i--)
            {
                if(themes.get(j).indexT.get(i) == index)
                {
                    themes.get(j).comments.remove(i);
                }

            }
        }
        return ResponseEntity.noContent().build();
    }
}
