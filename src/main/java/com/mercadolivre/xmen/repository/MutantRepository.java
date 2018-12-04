package com.mercadolivre.xmen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercadolivre.xmen.entity.Mutant;

@Repository
public interface MutantRepository extends CrudRepository<Mutant, Long>  {

}
