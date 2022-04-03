package ec.com.krugercorp.inventariovacunacion.repositories;

import ec.com.krugercorp.inventariovacunacion.datos.Empleado;
import ec.com.krugercorp.inventariovacunacion.enumeration.TipoVacuna;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.repository.Temporal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

    public abstract Optional<Empleado> findByIdentificacion(String identificacion);

    public abstract List<Empleado> findByVacunado(Integer vacunado);

    public abstract List<Empleado> findByTipoVacuna(TipoVacuna tipo);

    public abstract List<Empleado> findByFechaVacunacionBetween(@Temporal(TemporalType.DATE) @Param("startDate") Date startDate,@Temporal(TemporalType.DATE) @Param("endDate")Date endDate);
}
