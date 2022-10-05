package com.example.libraryusers.service.implementation;

import com.example.libraryusers.dto.PersonDto;
import com.example.libraryusers.dto.Utils;
import com.example.libraryusers.io.entity.PersonEntity;
import com.example.libraryusers.io.repositories.PersonRepository;
import com.example.libraryusers.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository repository;
    private ModelMapper modelMapper;
    private Utils utils;

    public PersonServiceImpl(PersonRepository repository, ModelMapper modelMapper, Utils utils) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.utils = utils;
    }

    @Override
    public PersonDto getPersonByPersonId(String personId) {
        PersonEntity personEntity = this.repository.findByPersonId(personId);

        if(personEntity == null) throw new RuntimeException("Person does not exist!");

        return this.modelMapper.map(personEntity, PersonDto.class);
    }

    @Override
    public List<PersonDto> getCustomers() {
        List<PersonEntity> persons = this.repository.findAll();
        List<PersonDto> returnValue = new ArrayList<>();

        for(PersonEntity personEntity : persons){
            returnValue.add(this.modelMapper.map(personEntity, PersonDto.class));
        }

        return returnValue;
    }

    @Override
    public PersonDto createPerson(PersonDto person) {
        if(this.repository.findByEmail(person.getEmail()) != null)
            throw new RuntimeException("Email already exists");
        if(this.repository.findByPhoneNumber(person.getPhoneNumber()) != null)
            throw new RuntimeException("Phone number already in use");

        PersonEntity personEntity = this.modelMapper.map(person, PersonEntity.class);
        personEntity.setPersonId(this.utils.generatePersonId(10));

        PersonEntity storedPersonDetails = this.repository.save(personEntity);

        return this.modelMapper.map(storedPersonDetails, PersonDto.class);
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto, String personId) {
        PersonEntity personEntity = this.repository.findByPersonId(personId);
        if(personEntity == null) throw new RuntimeException("Person does not exist!");
        personEntity = this.modelMapper.map(personDto, PersonEntity.class);

        PersonEntity updatedPerson = this.repository.save(personEntity);

        return this.modelMapper.map(updatedPerson, PersonDto.class);
    }

    @Override
    public void deletePersonById(String personId) {
        PersonEntity personEntity = this.repository.findByPersonId(personId);
        if(personEntity == null) throw new RuntimeException("Person does not exist!");
        this.repository.delete(personEntity);
    }
}
