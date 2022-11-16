package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ThemeController {

    private List<Theme> themes = new ArrayList<>();

    //на 3

    //curl -X POST 'http://localhost:8080/themes/' -H 'Content-Type: application/json' --data-raw '{"name":"name", "comment": ["comment1"],
    // "user": "name": "Mary", "age": "16"}'
    @PostMapping("themes")
    public ResponseEntity<Void> addTheme (@RequestBody Theme theme)
    {
        themes.add(theme);
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

    //curl -X PUT -H "Content-Type: application/json" -d {name} http://localhost:8080/themes/{index}
    @PutMapping("themes/{index}")
    public ResponseEntity<Void> updateTheme(@PathVariable("index") Integer i, @RequestBody String name)
    {
        (themes.get(i)).setName(name);
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
        return ResponseEntity.ok(themes.get(index));
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
    @DeleteMapping("themes/{index}/comments/{index}")
    public ResponseEntity<Void> deleteComment(@PathVariable("index") Integer index1, @PathVariable("index") Integer index2)
    {
        (themes.get(index1 - 1)).comments.remove((int) index2);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d {text} http://localhost:8080/themes/{index}/comments/{index}
    @PutMapping("themes/{index}/comments/{index}")
    public ResponseEntity<Void> updateComment(@PathVariable("index") Integer index1, @PathVariable("index") Integer index2, @RequestBody String text)
    {
        (themes.get(index1 - 1)).comments.remove((int) index2);
        (themes.get(index1 - 1)).comments.add(index2, text);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET -H "Content-Type: application/json" -d  http://localhost:8080/themes/{index}/comments
    @GetMapping("themes/{index}/comments")
    public ResponseEntity<ArrayList<String>> getComments(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(themes.get(index - 1).getComment());
    }
}
