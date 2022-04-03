package ec.com.krugercorp.inventariovacunacion.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.com.krugercorp.inventariovacunacion.datos.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    public abstract Optional<Usuario> findByIdentificacion(String identificacion);

}
