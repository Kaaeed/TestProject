package com.example.libraryusers.controller;

import com.example.libraryusers.controller.response.PersonRest;
import com.example.libraryusers.dto.PersonDto;
import com.example.libraryusers.io.PersonPDFExporter;
import com.example.libraryusers.service.PersonService;
import com.lowagie.text.DocumentException;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("person")
public class PersonController {
    private PersonService personService;
    private ModelMapper modelMapper;

    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public PersonRest getPerson(@PathVariable String id){
        PersonDto personDto = this.personService.getPersonByPersonId(id);
        return this.modelMapper.map(personDto, PersonRest.class);
    }

    @GetMapping("/all")
    public List<PersonRest> getAllCustomers(){
        List<PersonRest> returnValue = new ArrayList<>();
        List<PersonDto> customers = this.personService.getCustomers();

        for(PersonDto personDto : customers){
            returnValue.add(this.modelMapper.map(personDto, PersonRest.class));
        }

        return returnValue;
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<PersonRest> listCustomers = new ArrayList<>();
        List<PersonDto> customers = this.personService.getCustomers();

        for(PersonDto personDto : customers){
            listCustomers.add(this.modelMapper.map(personDto, PersonRest.class));
        }

        PersonPDFExporter exporter = new PersonPDFExporter(listCustomers);
        exporter.export(response);
    }

    @PostMapping()
    public PersonRest createPerson(@RequestBody PersonRest personDetails){
        if(!personDetails.getFirstName().isEmpty() && !personDetails.getLastName().isEmpty()){
            PersonDto personDto = this.modelMapper.map(personDetails, PersonDto.class);
            PersonDto createdPerson = this.personService.createPerson(personDto);

            return this.modelMapper.map(createdPerson, PersonRest.class);
        }
        throw new RuntimeException("Person already exists");
    }

    @PutMapping("/{id}")
    public PersonRest updatePerson(@RequestBody PersonRest personDetails, @PathVariable String id){
        PersonDto personDto = this.modelMapper.map(personDetails, PersonDto.class);
        PersonDto updatedPerson = this.personService.updatePerson(personDto, id);

        return this.modelMapper.map(updatedPerson, PersonRest.class);
    }

    @DeleteMapping("/{id}")
    public boolean deletePerson(@PathVariable String id){
        this.personService.deletePersonById(id);
        return true;
    }

}
