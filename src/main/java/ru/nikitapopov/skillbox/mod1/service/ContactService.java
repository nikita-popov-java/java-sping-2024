package ru.nikitapopov.skillbox.mod1.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import ru.nikitapopov.skillbox.mod1.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//@Service
public class ContactService {

    @Getter
    private final List<Contact> contacts = new ArrayList<>();

    @Value("${contact.file.path}")
    private String contactFilePath;

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void deleteContactByEmail(String email) {
        contacts.removeIf(contact -> contact.getEmail().equals(email));
    }

    public void saveContactsToFile() throws IOException {
        try (var writer = new BufferedWriter(new FileWriter(contactFilePath))) {
            for (var contact : contacts) {
                writer.write(contact.toString().replace(" | ", ";") + "\n");
            }
        }
    }

    public void loadContactsFromFile() throws IOException {
        try (var reader = new BufferedReader(new FileReader(contactFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                var parts = line.split(";");
                if (parts.length == 3) {
                    addContact(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        }
    }
}
