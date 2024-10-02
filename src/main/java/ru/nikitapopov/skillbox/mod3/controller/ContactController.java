package ru.nikitapopov.skillbox.mod3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikitapopov.skillbox.mod3.model.Contact;
import ru.nikitapopov.skillbox.mod3.repository.ContactRepository;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());

        return "contact-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());

        return "contact-form";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact);

        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        var contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        model.addAttribute("contact", contact);

        return "contact-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") Long id) {
        contactRepository.deleteById(id);

        return "redirect:/contacts";
    }
}

