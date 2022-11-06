package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class MessageController
{
    private final List<String> messages = new ArrayList<>();
    //curl -X GET  http://localhost:8080/messages/
    /*@GetMapping("messages")
    public ResponseEntity<List<String>> getMessages()
    {
        return ResponseEntity.ok(messages);
    }*/

    //curl -X POST -d 239  http://localhost:8080/messages/
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text)
    {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET -H "Content-Type: application/json" -d 239RUSSIA http://localhost:8080/messages/0
    @GetMapping("messages")
    public ResponseEntity<List<String>> getText(@RequestBody String text)
    {
        Iterator<String> x = messages.listIterator(0);
        int[] i = new int[messages.size()];
        int count = 0;
        List<String> str = new ArrayList<>();
            while (x.hasNext())
        {
            if (x.next().toString().startsWith(text))
                i[count] = 239;
            count++;
        }
            for (int j = 0; j < messages.size(); j++)
        {
            if (i[j] == 239)
            {
                str.add(messages.get(j));
            }
        }
            return ResponseEntity.ok(str);
    }

    //curl -X GET  http://localhost:8080/messages/0
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl -X DELETE  http://localhost:8080/messages/0
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index)
    {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d 239k http://localhost:8080/messages/0
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(@PathVariable("index") Integer i, @RequestBody String message)
    {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    // curl -X  GET  http://localhost:8080/messages/search/239
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> searchMessages(@PathVariable("text") String text)
    {
        int a = -239;
        for (int i = 0; i < messages.size(); i++)
        {
            if(messages.get(i).contains(text))
            {
                a = i;
                break;
            }
        }
        return ResponseEntity.ok(a);
    }

    // curl -X  GET  http://localhost:8080/messages/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> countMessages()
    {
        return ResponseEntity.ok(messages.size());
    }

    //curl -X  POST -d biba  http://localhost:8080/messages/
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> addMessage(@PathVariable("index") Integer index, @RequestBody String text)
    {
        messages.add(index-1, text); return ResponseEntity.accepted().build();
    }

    //curl -X  DELETE  http://localhost:8080/messages/search/239PFML
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteText(@PathVariable("text") String text)
    {
        Iterator<String> x = messages.listIterator(0);
        int[] i = new int[messages.size()];
        int count = 0;
        while (x.hasNext())
        {
            if (x.next().toString().contains(text))
                i[count] = 239;
            count++;
        }
        count = 0;
            for (int j = 0; j < messages.size(); j++)
            {
                if (i[count] == 239)
                {
                    messages.remove(j);
                    j--;
                }
                count++;
            }
            return ResponseEntity.noContent().build();
    }
}
