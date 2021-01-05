package mx.fciencias.distribuida.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import mx.fciencias.distribuida.model.Usuario;

public interface UsuarioRep extends PagingAndSortingRepository<Usuario, Integer> {
	@Query("SELECT c FROM Usuario c where c.correo = :correo")
	Usuario findByCorreo(@Param("correo") String correo);
	
	boolean existsByCorreo(String correo);
}
