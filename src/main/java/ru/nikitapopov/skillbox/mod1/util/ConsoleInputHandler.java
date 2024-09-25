package ru.nikitapopov.skillbox.mod1.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nikitapopov.skillbox.mod1.model.Contact;
import ru.nikitapopov.skillbox.mod1.service.ContactService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleInputHandler {

    private final ContactService contactService;
    private final FileContactInitializer fileContactInitializer;
    private final Scanner scanner = new Scanner(System.in);

    @PostConstruct
    private void run() {
        String command;
        while (true) {
            System.out.println("Введите команду (add, view, delete, save, exit):");
            command = scanner.nextLine();
            switch (command) {
                case "add":
                    addContact();
                    break;
                case "view":
                    viewContacts();
                    break;
                case "delete":
                    deleteContact();
                    break;
                case "save":
                    saveContacts();
                    break;
                case "exit":
                    System.out.println("Выход из приложения.");
                    return;
                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }

    private void addContact() {
        System.out.println("Введите контакт (Ф. И. О.; номер телефона; адрес электронной почты):");
        String input = scanner.nextLine();
        String[] parts = input.split(";");
        if (parts.length == 3) {
            contactService.addContact(new Contact(parts[0].trim(), parts[1].trim(), parts[2].trim()));
            System.out.println("Контакт добавлен.");
        } else {
            System.out.println("Неверный формат ввода.");
        }
    }

    private void viewContacts() {
        List<Contact> contacts = contactService.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("Нет контактов.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private void deleteContact() {
        System.out.println("Введите email контакта для удаления:");
        String email = scanner.nextLine();
        contactService.deleteContactByEmail(email);
        System.out.println("Контакт удалён.");
    }

    private void saveContacts() {
        try {
            contactService.saveContactsToFile();
            System.out.println("Контакты сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении контактов: " + e.getMessage());
        }
    }
}

