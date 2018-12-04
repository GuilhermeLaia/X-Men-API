package com.mercadolivre.xmen.service;

import com.mercadolivre.xmen.domain.Mutant;
import com.mercadolivre.xmen.exception.ServiceException;

public interface IService {

	void saveInfoMutant(boolean isMutant) throws ServiceException;
	
	Mutant findStatus() throws ServiceException;
}
