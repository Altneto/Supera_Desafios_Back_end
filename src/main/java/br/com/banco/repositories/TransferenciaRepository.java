package br.com.banco.repositories;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.banco.models.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{
	
	@Query("Select t from Transferencia " + 
	"t where t.dataTransferencia between :initialDate and :finalDate " + 
			"and t.nomeOperadorTransacao like %:name%")
	Page<Transferencia> search(
            @Param("initialDate") LocalDateTime initialDate,
            @Param("finalDate") LocalDateTime finalDate,
            @Param("name") String name,
            Pageable pageable);
	
	@Query("Select t from Transferencia " + 
			"t where t.dataTransferencia between :initialDate and :finalDate")
	Page<Transferencia> search1(
			@Param("initialDate") LocalDateTime initialDate,
		    @Param("finalDate") LocalDateTime finalDate,
		    Pageable pageable);
	
	@Query("Select t from Transferencia " + 
			"t where t.nomeOperadorTransacao like %:name%")
	Page<Transferencia> search2(
			@Param("name") String name,
			Pageable pageable);
	
	@Query("Select t from Transferencia t")
	Page<Transferencia> search3(Pageable pageable);
}
