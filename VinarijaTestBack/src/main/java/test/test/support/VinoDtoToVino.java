package test.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Vino;
import test.test.service.TipService;
import test.test.service.VinarijaService;
import test.test.service.VinoService;
import test.test.web.dto.VinoDTO;

@Component
public class VinoDtoToVino implements Converter<VinoDTO, Vino>{

	@Autowired
	private VinoService vinoService;
	
	@Autowired
	private TipService tipService;
	
	@Autowired
	private VinarijaService vinarijaService;
	
	
	@Override
	public Vino convert(VinoDTO dto) {
		Vino v;
		
		if(dto.getId() == null){
			v = new Vino();
        }else{
            v = vinoService.findOne(dto.getId());
        }
		
		if (v != null) {
			v.setId(dto.getId());
			v.setIme(dto.getIme());
			v.setOpis(dto.getOpis());
			v.setGodinaProizvodnje(dto.getGodinaProizvodnje());		
			v.setCenaFlase(dto.getCenaFlase());		
			v.setBrojFlasa(dto.getBrojFlasa());		
			v.setTip(tipService.findOne(dto.getTipId()));
			v.setVinarija(vinarijaService.findOne(dto.getVinarijaId()));
		}
		return v;
	}

	
	
	
}
