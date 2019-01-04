package com.example.SystemDatabase.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.SystemDatabase.domain.LogFile;

public interface LogFileRepository extends CrudRepository<LogFile, Long>{

}
