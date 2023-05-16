package test.test.web.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import test.test.model.Vino;
import test.test.service.VinoService;
import test.test.support.VinoDtoToVino;
import test.test.support.VinoToVinoDto;
import test.test.web.dto.VinoDTO;

@RestController
@RequestMapping(value = "/api/vina", produces = MediaType.APPLICATION_JSON_VALUE)
public class VinoController {

	@Autowired
    private VinoService vinoService;

    @Autowired
    private VinoDtoToVino ToVino;

    @Autowired
    private VinoToVinoDto toVinoDto;
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<VinoDTO> getOne(@PathVariable Long id){
        Vino vino = vinoService.findOne(id);

        if(vino != null) {
            return new ResponseEntity<>(toVinoDto.convert(vino), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<VinoDTO>> getAll(
    		@RequestParam(required=false) Long vinarijaId,
    		@RequestParam(required=false) String ime,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

        Page<Vino> page;

        try{
   		 page = vinoService.find(vinarijaId, ime, pageNo);
        }catch (Exception e){
       	 page = vinoService.findAll(pageNo);
        }
   	    HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

       return new ResponseEntity<>(toVinoDto.convert(page.getContent()), headers, HttpStatus.OK);
   }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Vino vino = vinoService.delete(id);

        if(vino != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VinoDTO> create(@Valid @RequestBody VinoDTO vinoDto){
        Vino vino = ToVino.convert(vinoDto);
        vino.setBrojFlasa(0);
        Vino sacuvaj = vinoService.save(vino);

        return new ResponseEntity<>(toVinoDto.convert(sacuvaj), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VinoDTO> update(@PathVariable Long id, @Valid @RequestBody VinoDTO vinoDTO){

        if(!id.equals(vinoDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Vino vino = ToVino.convert(vinoDTO);

        

        Vino sacuvaj = vinoService.update(vino);

        return new ResponseEntity<>(toVinoDto.convert(sacuvaj),HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/naruci")
    public ResponseEntity<VinoDTO> naruci(@PathVariable Long id, @RequestParam("kolicinaParam") Integer kolicina) {
        Vino vino = vinoService.narucivanje(id, kolicina);

        if (vino != null) {
            return new ResponseEntity<>(toVinoDto.convert(vino), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/kupi")
    public ResponseEntity<VinoDTO> kupi(@PathVariable Long id, @RequestParam("kolicinaParam") Integer kolicina) {
        Vino vino = vinoService.kupovina(id, kolicina);

        if (vino != null) {
            return new ResponseEntity<>(toVinoDto.convert(vino), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
}
