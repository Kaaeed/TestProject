package com.example.libraryusers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
public class PersonDto {
    private long id;
    private String personId;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int rentedBooks;
}
