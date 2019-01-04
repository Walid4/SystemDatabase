package com.example.SystemDatabase.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.SystemDatabase.domain.Solution;

public interface SolutionsRepositroy extends CrudRepository<Solution, Long> {

}
