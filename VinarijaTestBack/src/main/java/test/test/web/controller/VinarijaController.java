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

import test.test.model.Vinarija;
import test.test.service.VinarijaService;
import test.test.support.VinarijaToVinarijaDto;
import test.test.web.dto.VinarijaDTO;

@RestController
@RequestMapping(value = "/api/vinarije", produces = MediaType.APPLICATION_JSON_VALUE)
public class VinarijaController {

	@Autowired
    private VinarijaService vinarijaService;

    @Autowired
    private VinarijaToVinarijaDto toVinarijaDto;
	
	
    @GetMapping("/{id}")
    public ResponseEntity<VinarijaDTO> getOne(@PathVariable Long id){
        Vinarija vinarija = vinarijaService.findOne(id);

        if(vinarija != null) {
            return new ResponseEntity<>(toVinarijaDto.convert(vinarija), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<VinarijaDTO>> getAll(){

        List<Vinarija> stanja = vinarijaService.findAll();

        return new ResponseEntity<>(toVinarijaDto.convert(stanja), HttpStatus.OK);
    }
    
    
    
}
