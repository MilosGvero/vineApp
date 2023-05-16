package test.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.test.model.Vinarija;
import test.test.repository.VinarijaRepository;
import test.test.service.VinarijaService;

@Service
public class JpaVinarijaService implements VinarijaService {

	@Autowired
    private VinarijaRepository vr;
	
	@Override
	public Vinarija findOne(Long id) {
		return vr.findOneById(id);
	}

	@Override
	public List<Vinarija> findAll() {
		return vr.findAll();
	}

	@Override
	public Vinarija update(Vinarija vinarija) {
		return vr.save(vinarija);
	}

}
