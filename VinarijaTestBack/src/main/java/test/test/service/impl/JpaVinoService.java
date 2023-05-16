package test.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import test.test.model.Tip;
import test.test.model.Vinarija;
import test.test.model.Vino;
import test.test.repository.VinoRepository;
import test.test.service.VinoService;

@Service
public class JpaVinoService implements VinoService {
	
	@Autowired
    private VinoRepository vr;

	@Override
	public Vino findOne(Long id) {
		return vr.findOneById(id);
	}

	@Override
	public Page<Vino> findAll(int pageNo) {
		return vr.findAll( PageRequest.of(pageNo, 4));
	}

	@Override
	public Vino save(Vino vino) {
		return vr.save(vino);
	}

	@Override
	public Vino update(Vino vino) {
		return vr.save(vino);
	}

	@Override
	public Vino delete(Long id) {
		Vino vino  = findOne(id);
		if (vino != null) {
			Vinarija vinarija = vino.getVinarija();
			vinarija.obrisiVino(vino.getId());
			Tip tip = vino.getTip();
			tip.obrisiVino(vino.getId());
			
			vr.delete(vino);
			return vino;
		}
		return null;
	}

	@Override
	public Page<Vino> find(Long vinarijaId, String ime, int pageNo) {
		return vr.search(vinarijaId, ime, PageRequest.of(pageNo, 4));
	}

	@Override
	public Vino narucivanje(Long id, Integer kolicina) {
	    Vino vino = vr.getOne(id);

	    if (vino != null) {
	        int brojFlasa = vino.getBrojFlasa();
	        vino.setBrojFlasa(brojFlasa + kolicina);
	        
	        return vr.save(vino);
	    } 
	    
	    return null;
	}
	
	@Override
	public Vino kupovina(Long id, Integer kolicina) {
	    Vino vino = vr.getOne(id);

	    if (vino != null) {
	        int brojFlasa = vino.getBrojFlasa();
	        vino.setBrojFlasa(brojFlasa - kolicina);
	        
	        return vr.save(vino);
	    } 
	    
	    return null;
	}
}
