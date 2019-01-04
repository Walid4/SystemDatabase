package com.example.SystemDatabase.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.SystemDatabase.domain.Defect;

public interface DefectsRepository extends CrudRepository<Defect, Long>{

}
