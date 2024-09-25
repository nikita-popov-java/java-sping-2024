package ru.nikitapopov.skillbox.mod1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
public class Contact {

    private String fullName;
    private String phoneNumber;
    @Getter
    private String email;

    @Override
    public String toString() {
        return String.join("|", fullName, phoneNumber, email);
    }
}
