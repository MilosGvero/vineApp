package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Vinarija;
import test.test.web.dto.VinarijaDTO;

@Component
public class VinarijaToVinarijaDto implements Converter<Vinarija, VinarijaDTO> {

	@Override
	public VinarijaDTO convert(Vinarija v) {
		VinarijaDTO dto = new VinarijaDTO();
		
		dto.setId(v.getId());
		dto.setIme(v.getIme());
		dto.setGodinaOsnivanja(v.getGodinaOsnivanja());
		
		return dto;
	}
	
	public List<VinarijaDTO> convert(List<Vinarija> vinarije){
        List<VinarijaDTO> dto = new ArrayList<>();

        for(Vinarija vina : vinarije) {
            dto.add(convert(vina));
        }

        return dto;
    }

}
