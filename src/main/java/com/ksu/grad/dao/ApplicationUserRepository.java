package com.ksu.grad.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksu.grad.entity.Login;

public interface ApplicationUserRepository extends JpaRepository<Login, Long> {
	Login findByUserName(String userName);
}