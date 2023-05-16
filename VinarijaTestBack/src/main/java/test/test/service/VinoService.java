package test.test.service;

import org.springframework.data.domain.Page;
import org.springframework.scheduling.config.Task;

import test.test.model.Vino;

public interface VinoService {

	Vino findOne(Long id);

    Page<Vino> findAll(int pageNo);

    Vino save(Vino vino);

    Vino update(Vino vino);

    Vino delete(Long id);
    
    Page<Vino> find(  Long vinarijaId, String ime, int pageNo);
    

	Vino narucivanje(Long id, Integer kolicina);

	Vino kupovina(Long id, Integer kolicina);
}
