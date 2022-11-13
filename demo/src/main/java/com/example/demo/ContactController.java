package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ContactController
{
    private final List<Contact> contacts = new ArrayList<>();
    // curl -X POST -H "Content-Type: application/json" -d "{\"name/":/"Masha/", /"number/":/"88005553535/", /"email/" = /"aaaaaabbbbbb/"}" http://localhost:8080/contacts
    @PostMapping("contacts")
    public ResponseEntity<Void> addContact(@RequestBody Contact contact)
    {
        contacts.add(contact);
        return ResponseEntity.accepted().build();
    }

    // curl -X DELETE http://localhost:8080/contacts/1
    @DeleteMapping("contacts/{index}")
    public ResponseEntity<Void> deleteContact(@PathVariable("index") Integer index)
    {
        contacts.remove((int) index);
        return ResponseEntity.accepted().build();
    }

    // curl -X GET http://localhost:8080/contacts
    @GetMapping("contacts")
    public ResponseEntity<List<Contact>> getContact()
    {
        return ResponseEntity.ok(contacts);
    }

    // curl -X GET http://localhost:8080/contacts/2
    @GetMapping("contacts/{index}")
    public ResponseEntity<Contact> getContact(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(contacts.get(index));
    }

    // curl -X PUT -H "Content-Type: application/json" -d "{\"name/":/"Masha/", /"number/":/"88005553535/", /"email/" = /"aaaaaabbbbbb/"}" http://localhost:8080/contacts/0
    @PutMapping("contacts/{index}")
    public ResponseEntity<Void> updateContact(@PathVariable("index") Integer i, @RequestBody Contact contact)
    {
        contacts.remove((int) i);
        contacts.add(i, contact);
        return ResponseEntity.accepted().build();
    }
}
