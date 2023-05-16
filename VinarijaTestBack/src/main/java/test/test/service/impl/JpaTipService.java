package test.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.test.model.Tip;
import test.test.repository.TipRepository;
import test.test.service.TipService;

@Service
public class JpaTipService implements TipService {

	
	@Autowired
    private TipRepository tr;
	
	
	@Override
	public Tip findOne(Long id) {
		return tr.findOneById(id);
	}

	@Override
	public List<Tip> findAll() {
		return tr.findAll();
	}

	@Override
	public Tip update(Tip tip) {
		return tr.save(tip);
	}

}
