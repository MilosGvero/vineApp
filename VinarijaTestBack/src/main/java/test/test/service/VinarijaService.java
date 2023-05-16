package test.test.service;

import java.util.List;

import test.test.model.Vinarija;

public interface VinarijaService {

	Vinarija findOne(Long id);
	
	List<Vinarija> findAll();
	
	Vinarija update(Vinarija vinarija);
}
