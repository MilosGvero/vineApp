package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Tip;
import test.test.web.dto.TipDTO;

@Component
public class TipToTipDto implements Converter<Tip, TipDTO> {

	@Override
	public TipDTO convert(Tip v) {
		TipDTO dto = new TipDTO();
		
		dto.setId(v.getId());
		dto.setIme(v.getIme());
		
		return dto;
	}

	public List<TipDTO> convert(List<Tip> tipovi){
        List<TipDTO> dto = new ArrayList<>();

        for(Tip tip : tipovi) {
        	dto.add(convert(tip));
        }

        return dto;
    }
	
}
