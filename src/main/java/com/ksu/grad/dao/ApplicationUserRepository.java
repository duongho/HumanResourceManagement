package com.ksu.grad.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksu.grad.entity.Person;

public interface ApplicationUserRepository extends JpaRepository<Person, Long> {
    Person findByusername(String userName);
}