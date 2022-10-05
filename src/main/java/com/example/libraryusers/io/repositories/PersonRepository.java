package com.example.libraryusers.io.repositories;

import com.example.libraryusers.io.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    PersonEntity findByPersonId(String personId);
    PersonEntity findByEmail(String email);
    PersonEntity findByPhoneNumber(int phoneNumber);
}
