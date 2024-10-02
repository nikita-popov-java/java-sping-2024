package ru.nikitapopov.skillbox.mod3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nikitapopov.skillbox.mod3.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}

