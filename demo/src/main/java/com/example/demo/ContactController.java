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
    // curl -X POST -H "Content-Type: application/json" -d "{"nameT":"Masha", "number":"239", "email" : "aaaaaabbbbbb"}" http://localhost:8080/contacts
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

    // curl -X PUT -H "Content-Type: application/json" -d "{"nameT":"Masha", "number":"239", "email" : "aaaaaabbbbbb"}" http://localhost:8080/contacts/0
    @PutMapping("contacts/{index}")
    public ResponseEntity<Void> updateContact(@PathVariable("index") Integer i, @RequestBody Contact contact)
    {
        contacts.remove((int) i);
        contacts.add(i, contact);
        return ResponseEntity.accepted().build();
    }

    // curl -X GET http://localhost:8080/contacts/
    @GetMapping("contacts/{num}")
    public ResponseEntity<Contact> searchContact(@RequestBody Integer num)
    {
        for (Contact contact : contacts) {
            if (contact.email.contains(num.toString()) || (contact.number + "").contains(num.toString()))
                ResponseEntity.ok(contact);
        }
        return ResponseEntity.accepted().build();
    }

    /*//curl -X GET 'http://localhost:8080/contacts/get/1'
    @GetMapping("contacts/get/{text}")
        public ResponseEntity<List<Contacts» getTextContacts("text" String text)
    {
        final List<Contacts> temp = new ArrayList<>();
        for (Contacts c: contacts)
        {
            if((c.nameT+" "+c.number+" "+c.email).contains(text))
                temp.add(c);
        }
        return ResponseEntity.ok(temp);
    }

    //curl -X GET 'http://localhost:8080/contacts/sort/number'
    //curl -X GET 'http://localhost:8080/contacts/sort/name'
    //curl -X GET 'http://localhost:8080/contacts/sort/email'
    @GetMapping("contacts/sort/{text}")
    public ResponseEntity<List<Contacts» sortContacts("text" String text)
    {
    ArrayList temp = (ArrayList) contacts;
    switch (text) {
        case "nameT":
            Collections.sort(temp, new NameComparator());
            break;
        case "number":
            Collections.sort(temp, new NumberComparator());
            break;
        case "email":
            Collections.sort(temp, new EmailComparator());
            break;
        }
        return ResponseEntity.ok(temp);
    }

    class NameComparator implements Comparator<Contacts>
    {
        public int compare(Contacts a, Contacts b)
        {
            return a.nameT.compareToIgnoreCase(b.nameT);
        }
    }

    class NumberComparator implements Comparator<Contacts>
    {
        public int compare(Contacts a, Contacts b)
        {
            return Integer.compare(a.number, b.number);
        }
    }

    class EmailComparator implements Comparator<Contacts>
    {
        public int compare(Contacts a, Contacts b)
        {
            return a.email.compareToIgnoreCase(b.email);
        }
    }

    /curl -X GET 'http://localhost:8080/contacts/page/0' -H 'Content-Type: application/json' -data-raw '2'
    @GetMapping("contacts/page/{index}")
    public ResponseEntity<List<Contacts» pagingContacts(@RequestBody Integer limit, "index" Integer index)
    {
    final List<Contacts> temp = contacts.subList(index, Math.min(index+limit, contacts.size()-1));
    if (limit <= 0 || index < 0)
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return ResponseEntity.ok(temp);
    }*/
}
