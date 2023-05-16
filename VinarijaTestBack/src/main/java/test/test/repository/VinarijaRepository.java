package test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.test.model.Vinarija;

@Repository
public interface VinarijaRepository extends JpaRepository<Vinarija,Long> {

	Vinarija findOneById(Long id);

}
