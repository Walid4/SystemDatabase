package com.example.SystemDatabase.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.SystemDatabase.domain.Application;

public interface AppRepository extends CrudRepository<Application, Long>{

}
