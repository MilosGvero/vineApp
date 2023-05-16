package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Vino;
import test.test.web.dto.VinoDTO;

@Component
public class VinoToVinoDto implements Converter<Vino, VinoDTO>{

	@Override
	public VinoDTO convert(Vino v) {
		VinoDTO dto = new VinoDTO();
		
		dto.setId(v.getId());
		dto.setIme(v.getIme());
		dto.setOpis(v.getOpis());
		dto.setGodinaProizvodnje(v.getGodinaProizvodnje());
		dto.setCenaFlase(v.getCenaFlase());
		dto.setBrojFlasa(v.getBrojFlasa());
		dto.setTipId(v.getTip().getId());
		dto.setTipIme(v.getTip().getIme());
		dto.setVinarijaId(v.getVinarija().getId());
		dto.setVinarijaIme(v.getVinarija().getIme());
		
		return dto;
	}
	
	public List<VinoDTO> convert(List<Vino> vina){
        List<VinoDTO> dto = new ArrayList<>();

        for(Vino v : vina) {
            dto.add(convert(v));
        }

        return dto;
    }

}
