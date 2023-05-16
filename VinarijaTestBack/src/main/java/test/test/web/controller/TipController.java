package test.test.web.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.test.model.Tip;
import test.test.service.TipService;
import test.test.support.TipToTipDto;
import test.test.web.dto.TipDTO;

@RestController
@RequestMapping(value = "/api/tipovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipController {
	
	@Autowired
    private TipService tipService;

    @Autowired
    private TipToTipDto toTipDto;
    
    @GetMapping("/{id}")
    public ResponseEntity<TipDTO> getOne(@PathVariable Long id){
        Tip tip = tipService.findOne(id);

        if(tip != null) {
            return new ResponseEntity<>(toTipDto.convert(tip), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<TipDTO>> getAll(){

        List<Tip> tipovi = tipService.findAll();

        return new ResponseEntity<>(toTipDto.convert(tipovi), HttpStatus.OK);
    }

}
