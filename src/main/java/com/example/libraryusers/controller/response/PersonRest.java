package com.example.libraryusers.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
public class PersonRest {
    private String personId;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int rentedBooks;
}
