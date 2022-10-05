package com.example.libraryusers.io.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "person")
@Getter @Setter
public class PersonEntity implements Serializable {
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 124125352632346L;

    @GeneratedValue @Id
    private long id;

    @Column(nullable = false)
    private String personId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private int phoneNumber;

    @Column(nullable = false)
    private int rentedBooks;
}
