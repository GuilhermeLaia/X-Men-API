package com.mercadolivre.xmen.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.xmen.config.App;
import com.mercadolivre.xmen.entity.Mutant;
import com.mercadolivre.xmen.exception.ServiceException;
import com.mercadolivre.xmen.repository.MutantRepository;
import com.mercadolivre.xmen.service.IService;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class ServiceImplTest {
	
	@MockBean
	private MutantRepository repository;
	
	@Autowired
	private IService service;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testSaveInfoMutant() throws ServiceException {
		
		Mockito.when(
				repository.findAll()).thenReturn(getIterable());
		
		service.saveInfoMutant(true);
		
	}

	@Test
	public void testFindStatus() throws ServiceException, JsonProcessingException {
		Mockito.when(
				repository.findAll()).thenReturn(getIterable());
		
		com.mercadolivre.xmen.domain.Mutant response = service.findStatus();
		String jsonResponseExpected = getResponseMutant();
		
		assertEquals(jsonResponseExpected, mapper.writeValueAsString(response));
		
	}
	
	private Iterable<Mutant> getIterable() {
		
		List<Mutant> mutants = new ArrayList<>();
		Mutant mutant = new Mutant();
		mutant.setId(1L);
		mutant.setCountMutantDna(1);
		mutant.setCountHumanDna(0);
		mutants.add(mutant);
		
		return mutants;
		
	}
	
	private String getResponseMutant() throws JsonProcessingException {
		
		com.mercadolivre.xmen.domain.Mutant mutant = new com.mercadolivre.xmen.domain.Mutant(1, 0, 0.0);
		return mapper.writeValueAsString(mutant);
		
		
	}

}
