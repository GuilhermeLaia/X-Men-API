package com.mercadolivre.xmen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.xmen.domain.Mutant;
import com.mercadolivre.xmen.exception.ServiceException;
import com.mercadolivre.xmen.request.Request;
import com.mercadolivre.xmen.service.IService;
import com.mercadolivre.xmen.validation.Validation;

@RestController
public class Controller {
	
	@Autowired
	private IService service;
	
	@Autowired
	private Validation validation;

	@RequestMapping(value = "/api/mutant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isMutante(@RequestBody Request request) throws ServiceException {
		
		Boolean isMutant = false;
		
		validation.validateRequest(request);
		
		int countSequence = 0;
		
		char[][] matrixDna = convertArrayToMatrixDna(request.getDna());
		countSequence += findSenquenceHorizontal(matrixDna);
		countSequence += findSenquenceVertical(matrixDna);
		countSequence += findSequenceOblique(matrixDna);
		
		if(countSequence > 1) {
			isMutant = true;
		}
		
		service.saveInfoMutant(isMutant);
		
		return new ResponseEntity<Boolean>(isMutant, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mutant> stats() throws ServiceException {
		Mutant domain = service.findStatus();
		return new ResponseEntity<Mutant>(domain, HttpStatus.OK);
	}
	
	private char[][] convertArrayToMatrixDna(String[] dna) {
		
		char[][] matrixDna = new char[dna.length][dna.length];
		int actualLine = 0;
		
		for(String line: dna) {
			for(int i = 0; i < line.length(); i++) {
				matrixDna[actualLine][i] = line.charAt(i);
			}
			actualLine++;
		}
		
		return matrixDna;
	}
	
	private int findSenquenceHorizontal(char[][] matrixDna) {
		
		int countSequenceOfFourEqualLetters = 0;
		int countFourEqualLetters = 1;
		char lastLetterEqual = 'X';
		
		for(int row = 0; row < matrixDna.length; row++) {
			for(int col = 0; col < matrixDna.length; col++) {
			
				char letter = matrixDna[row][col];
				
				if(letter == lastLetterEqual) {
					countFourEqualLetters++;
				} else {
					countFourEqualLetters = 1;
				}
				lastLetterEqual = letter;
				
				if(countFourEqualLetters == 4) {
					countSequenceOfFourEqualLetters++;
					countFourEqualLetters = 1;
				}
				
			}
		}
		
		return countSequenceOfFourEqualLetters;
	}
	
	private int findSenquenceVertical(char[][] matrixDna) {
		
		int countSequenceOfFourEqualLetters = 0;
		int countFourEqualLetters = 1;
		char lastLetterEqual = 'X';
		
		for(int row = 0; row < matrixDna.length; row++) {
			for(int col = 0; col < matrixDna.length; col++) {
				
				char letter = matrixDna[col][row];
				
				if(letter == lastLetterEqual) {
					countFourEqualLetters++;
				} else {
					countFourEqualLetters = 1;
				}
				lastLetterEqual = letter;
				
				if(countFourEqualLetters == 4) {
					countSequenceOfFourEqualLetters++;
					countFourEqualLetters = 1;
				}
				
			}
		}
		
		return countSequenceOfFourEqualLetters;
		
	}
	
	private int findSequenceOblique(char[][] matrixDna) {
		
		int countSequenceOfFourEqualLetters = 0;
		int countFourEqualLetters = 1;
		char lastLetterEqual = 'X';
		
		
		for(int row = 0; row < matrixDna.length; row++) {
				
			char letter = matrixDna[row][row];
			
			if(letter == lastLetterEqual) {
				countFourEqualLetters++;
			} else {
				countFourEqualLetters = 1;
			}
			lastLetterEqual = letter;
			
			if(countFourEqualLetters == 4) {
				countSequenceOfFourEqualLetters++;
				countFourEqualLetters = 1;
			}
				
		}
		
		return countSequenceOfFourEqualLetters;
		
	}
}
