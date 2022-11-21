package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class ContactController
{
    private final List<Contacts> contacts = new ArrayList<>();
    // curl -X POST -H "Content-Type: application/json" -d "{"nameT":"Masha", "number":"239", "email" : "aaaaaabbbbbb"}" http://localhost:8080/contacts
    @PostMapping("contacts")
    public ResponseEntity<Void> addContact(@RequestBody Contacts contacts)
    {
        this.contacts.add(contacts);
        return ResponseEntity.accepted().build();
    }

    // curl -X DELETE http://localhost:8080/contacts/{index}
    @DeleteMapping("contacts/{index}")
    public ResponseEntity<Void> deleteContact(@PathVariable("index") Integer index)
    {
        contacts.remove((int) index);
        return ResponseEntity.accepted().build();
    }

    // curl -X GET http://localhost:8080/contacts
    @GetMapping("contacts")
    public ResponseEntity<List<Contacts>> getContact()
    {
        return ResponseEntity.ok(contacts);
    }

    // curl -X GET http://localhost:8080/contacts/{index}
    @GetMapping("contacts/{index}")
    public ResponseEntity<Contacts> getContact(@PathVariable("index") Integer index)
    {
        return ResponseEntity.ok(contacts.get(index));
    }

    // curl -X PUT -H "Content-Type: application/json" -d "{"nameT":"Masha", "number":"239", "email" : "aaaaaabbbbbb"}" http://localhost:8080/contacts/{i}
    @PutMapping("contacts/{index}")
    public ResponseEntity<Void> updateContact(@PathVariable("index") Integer i, @RequestBody Contacts contacts)
    {
        this.contacts.remove((int) i);
        this.contacts.add(i, contacts);
        return ResponseEntity.accepted().build();
    }

    // curl -X GET http://localhost:8080/contacts/{index}
    @GetMapping("contacts/{index}")
    public ResponseEntity<Contacts> searchContact(@RequestBody Integer index)
    {
        for (Contacts contacts : this.contacts) {
            if (contacts.email.contains(index.toString()) || (contacts.number + "").contains(index.toString()))
                ResponseEntity.ok(contacts);
        }
        return ResponseEntity.accepted().build();
    }

    //curl -X GET 'http://localhost:8080/contacts/get/{text}'
    @GetMapping("contacts/get/{text}")
        public ResponseEntity<List<Contacts>> getTextContacts(@PathVariable ("text") String text)
    {
        final List<Contacts> temp = new ArrayList<>();
        for (Contacts c: contacts)
        {
            if((c.name+" "+c.number+" "+c.email).contains(text))
                temp.add(c);
        }
        return ResponseEntity.ok(temp);
    }

    //curl -X GET 'http://localhost:8080/contacts/sort/(number/name/email)'
    @GetMapping("contacts/sort/{text}")
    public ResponseEntity sortContacts(@PathVariable ("text") String text)
    {
    ArrayList temp = (ArrayList) contacts;
        switch (text) {
            case "name" -> temp.sort(new NameComparator());
            case "number" -> temp.sort(new NumberComparator());
            case "email" -> temp.sort(new EmailComparator());
        }
        return ResponseEntity.ok(temp);
    }
    static class NameComparator implements Comparator<Contacts>
    {
        public int compare(Contacts a, Contacts b)
        {
            return a.name.compareToIgnoreCase(b.name);
        }
    }
    static class NumberComparator implements Comparator<Contacts>
    {
        public int compare(Contacts a, Contacts b)
        {
            return Integer.compare(a.number, b.number);
        }
    }
    static class EmailComparator implements Comparator<Contacts>
    {
        public int compare(Contacts a, Contacts b)
        {
            return a.email.compareToIgnoreCase(b.email);
        }
    }

    //curl -X GET 'http://localhost:8080/contacts/page/{index}' -H 'Content-Type: application/json' -data-raw 'limit'
    @GetMapping("contacts/page/{index}")
    public ResponseEntity<List<Contacts>> pagingContacts(@RequestBody Integer limit, @PathVariable ("index") Integer index)
    {
    final List<Contacts> temp = contacts.subList(index, Math.min(index+limit, contacts.size()-1));
    if (limit <= 0 || index < 0)
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return ResponseEntity.ok(temp);
    }
}
