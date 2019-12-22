package mutante.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mutante.modelo.ADN;

@Repository
public interface RepositorioDeADN extends JpaRepository<ADN, Long> {

	ADN findByCadenaADN(String cadenaADN);
	
	@Query("select mutante from ADN")
	List<Boolean> findAllEvaluaciones();
}
