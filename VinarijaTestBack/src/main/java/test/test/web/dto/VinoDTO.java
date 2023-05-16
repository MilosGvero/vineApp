package test.test.web.dto;

import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class VinoDTO {

	 private Long id;
	 
	 private String ime;
	 
	 @Length(max = 120)
	 private String opis;
	 
	 @Positive(message = "godina nije pozitivan broj.")
	 private Integer godinaProizvodnje;
	 
	 @Positive(message = "cena nije pozitivan broj.")
	 private Double cenaFlase;
	 
	 private Integer brojFlasa;
	 
	 private Long tipId;
	 
	 private String tipIme;
	 
	 private Long vinarijaId;
	 
	 private String vinarijaIme;

	public VinoDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Integer getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(Integer godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public Double getCenaFlase() {
		return cenaFlase;
	}

	public void setCenaFlase(Double cenaFlase) {
		this.cenaFlase = cenaFlase;
	}

	public Integer getBrojFlasa() {
		return brojFlasa;
	}

	public void setBrojFlasa(Integer brojFlasa) {
		this.brojFlasa = brojFlasa;
	}

	public Long getTipId() {
		return tipId;
	}

	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}

	public String getTipIme() {
		return tipIme;
	}

	public void setTipIme(String tipIme) {
		this.tipIme = tipIme;
	}

	public Long getVinarijaId() {
		return vinarijaId;
	}

	public void setVinarijaId(Long vinarijaId) {
		this.vinarijaId = vinarijaId;
	}

	public String getVinarijaIme() {
		return vinarijaIme;
	}

	public void setVinarijaIme(String vinarijaIme) {
		this.vinarijaIme = vinarijaIme;
	}
	 
	 
	 
	
}
