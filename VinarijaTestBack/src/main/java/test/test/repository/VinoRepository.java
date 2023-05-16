package test.test.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.test.model.Vino;

@Repository
public interface VinoRepository extends JpaRepository<Vino,Long> {

	Vino findOneById(Long id);
	
	

	@Query("SELECT v FROM Vino v WHERE " +
	        "(:ime IS NULL OR v.ime LIKE %:ime%) AND " +
	        "(:vinarijaId IS NULL OR v.vinarija.id = :vinarijaId)")
	Page<Vino> search(@Param("vinarijaId") Long vinarijaId, @Param("ime") String ime, Pageable pageable);

}
