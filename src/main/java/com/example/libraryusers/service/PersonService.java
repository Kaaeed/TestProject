package com.example.libraryusers.service;

import com.example.libraryusers.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto getPersonByPersonId(String id);
    List<PersonDto> getCustomers();
    PersonDto createPerson(PersonDto person);
    PersonDto updatePerson(PersonDto personDto, String personId);
    void deletePersonById(String personId);
}
