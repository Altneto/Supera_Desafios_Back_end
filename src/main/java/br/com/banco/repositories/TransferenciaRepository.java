package br.com.banco.repositories;

import java.time.LocalDateTime;
import java.util.List;

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
	List<Transferencia> search(
            @Param("initialDate") LocalDateTime initialDate,
            @Param("finalDate") LocalDateTime finalDate,
            @Param("name") String name);
	
	@Query("Select t from Transferencia " + 
			"t where t.dataTransferencia between :initialDate and :finalDate")
	List<Transferencia> search1(
			@Param("initialDate") LocalDateTime initialDate,
		    @Param("finalDate") LocalDateTime finalDate);
	
	@Query("Select t from Transferencia " + 
			"t where t.nomeOperadorTransacao like %:name%")
	List<Transferencia> search2(
			@Param("name") String name);
	
	@Query("Select t from Transferencia t")
	List<Transferencia> search3();
}
