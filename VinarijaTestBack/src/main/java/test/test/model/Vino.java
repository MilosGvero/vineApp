package test.test.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "vino")
public class Vino {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column( nullable = false)
	 private String ime;
	 
	 private String opis;
	 
	 @Column( nullable = false)
	 private int godinaProizvodnje;
	 
	 private double cenaFlase;
	 
	 private int brojFlasa;
	 
	 @ManyToOne
	 private Tip tip;
	 
	 @ManyToOne
	 private Vinarija vinarija;

	public Vino() {
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

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public double getCenaFlase() {
		return cenaFlase;
	}

	public void setCenaFlase(double cenaFlase) {
		this.cenaFlase = cenaFlase;
	}

	public int getBrojFlasa() {
		return brojFlasa;
	}

	public void setBrojFlasa(int brojFlasa) {
		this.brojFlasa = brojFlasa;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public Vinarija getVinarija() {
		return vinarija;
	}

	public void setVinarija(Vinarija vinarija) {
		this.vinarija = vinarija;
	}

	@Override
	public String toString() {
		return "Vino [id=" + id + ", ime=" + ime + ", opis=" + opis + ", godinaProizvodnje=" + godinaProizvodnje
				+ ", cenaFlase=" + cenaFlase + ", brojFlasa=" + brojFlasa + ", tip=" + tip.getIme() + ", vinarija=" + vinarija.getIme()
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vino other = (Vino) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
