package test.test.service;

import java.util.List;

import test.test.model.Tip;

public interface TipService {

	Tip findOne(Long id);
	
	List<Tip> findAll();
	
	Tip update(Tip tip);
}
