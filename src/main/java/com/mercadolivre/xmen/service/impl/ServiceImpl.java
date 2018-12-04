package com.mercadolivre.xmen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolivre.xmen.entity.Mutant;
import com.mercadolivre.xmen.exception.ServiceException;
import com.mercadolivre.xmen.repository.MutantRepository;
import com.mercadolivre.xmen.service.IService;

@Service
public class ServiceImpl implements IService {

	@Autowired
	private MutantRepository repository;

	@Override
	public void saveInfoMutant(boolean isMutant) throws ServiceException {
	
		int countMutantDna = 0;
		int countHumanDna = 0;
		
		Mutant mutant = new Mutant();
		
		Iterable<Mutant> mutants = repository.findAll();
		if(mutants != null && mutants.iterator().hasNext()) {
			mutant = mutants.iterator().next();
			countMutantDna = mutant.getCountMutantDna();
			countHumanDna = mutant.getCountHumanDna();
		}
		
		if(isMutant) {
			countMutantDna += 1;
		} else {
			countHumanDna +=  1;
		}
		
		mutant.setCountHumanDna(countHumanDna);
		mutant.setCountMutantDna(countMutantDna);
		
		repository.save(mutant);
		
	}

	@Override
	public com.mercadolivre.xmen.domain.Mutant findStatus() throws ServiceException {
		
		com.mercadolivre.xmen.domain.Mutant domain = null;
		
		int countMutantDna = 0;
		int countHumanDna = 0;
		double ratio = 0.0;
		
		Iterable<Mutant> mutants = repository.findAll();
		if(mutants != null && mutants.iterator().hasNext()) {
			Mutant mutant = mutants.iterator().next();
			countMutantDna = mutant.getCountMutantDna();
			countHumanDna = mutant.getCountHumanDna();
			ratio = countHumanDna > 0 ? (double) (countMutantDna / countHumanDna) : 0.0;
		}
		
		domain = new com.mercadolivre.xmen.domain.Mutant(countMutantDna, countHumanDna, ratio);
		
		return domain;
	}
	
	
}
