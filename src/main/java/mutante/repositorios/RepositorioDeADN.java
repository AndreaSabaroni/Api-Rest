package mutante.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mutante.modelo.ADN;

@Repository
public interface RepositorioDeADN extends JpaRepository<ADN, Long> {

	ADN findByCadenaADN(String cadenaADN);

}
