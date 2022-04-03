package ec.com.krugercorp.inventariovacunacion.services;

import ec.com.krugercorp.inventariovacunacion.datos.Empleado;
import ec.com.krugercorp.inventariovacunacion.datos.Usuario;
import ec.com.krugercorp.inventariovacunacion.enumeration.TipoVacuna;
import ec.com.krugercorp.inventariovacunacion.excepcion.EmpleadoException;
import ec.com.krugercorp.inventariovacunacion.repositories.EmpleadoRepository;
import ec.com.krugercorp.inventariovacunacion.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UsuarioService.java clase de servicios para empleados de la compañia
 *
 * @author gcoello
 */
@Service
public class EmpleadoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    /**
     * Actualiza datos del empleado. Si no existe se almacena si existe se actualiza
     * Cuando se registra un empleado nuevo se crea un usuario para el ingreso al sistema
     *
     * @param empleado
     * @return
     */
    public Empleado guardarUsuario(Empleado empleado) {
        Empleado empleadoNuevo = empleadoRepository.save(empleado);
        Usuario usuario = usuarioRepository
                .findByIdentificacion(empleadoNuevo.getIdentificacion()).orElse(new Usuario());
        System.out.println("usuario: " + usuario.toString());
        if (usuario.getId() == null) {
            Usuario usuarioNuevo = new Usuario(empleadoNuevo);
            usuarioRepository.save(usuarioNuevo);
        }
        return empleadoNuevo;
    }

    /**
     * Actualiza la información del empleado
     *
     * @param empleado entidad empleado que va a modificar
     * @return
     * @throws ec.com.krugercorp.inventariovacunacion.excepcion.EmpleadoException
     */
    public Empleado ActualizarInformacion(Empleado empleado) throws EmpleadoException {
        Empleado empleadoOld = empleadoRepository.findById(empleado.getId()).orElse(null);

        if (empleadoOld == null) {
            throw new EmpleadoException("Empleado no existe para actualización");
        }
        empleadoOld.setFechaNacimiento(empleado.getFechaNacimiento());
        empleadoOld.setDireccionDomicilio(empleado.getDireccionDomicilio());
        empleadoOld.setTelefono(empleado.getTelefono());
        empleadoOld.setVacunado(empleado.getVacunado());
        empleadoOld.setTipoVacuna(empleado.getTipoVacuna());
        empleadoOld.setFechaVacunacion(empleado.getFechaVacunacion());
        empleadoOld.setNumeroDosis(empleado.getNumeroDosis());
        return empleadoRepository.save(empleadoOld);
    }

    /**
     * Elimina empleado por primary key
     *
     * @param id primary key del empleado
     * @return retorna true si se elimina con exito, caso contrario retorna
     * false
     */
    public boolean eliminarEmpelado(Long id) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado != null) {
            Usuario usuario = usuarioRepository
                    .findByIdentificacion(empleado.getIdentificacion()).orElse(null);
            empleadoRepository.deleteById(id);
            usuarioRepository.deleteById(usuario.getId());

        }
        return true;
    }

/**
 * Busca un empleado por primary key id
 *
 * @param id primary key del empleado
 * @return retorna empleado en el caso que lo encuentre, caso contrario retorna
 * vacio
 */
public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    /**
     * Busca empleado con identificacion 
     *
     * @param identificacion identificacion del empleado
     * @return retorna la entidad EmpleadoModel
     */
    public Optional<Empleado> obtenerPorIdentificacion(String identificacion) {
        return empleadoRepository.findByIdentificacion(identificacion);
    }

    /**
     * Lista todos los empleados de la tabla
     *
     * @return lista de empleados
     */
    public ArrayList<Empleado> listarTodos() {
        return (ArrayList<Empleado>) empleadoRepository.findAll();
    }

    /**
     * Lista los empleados con los siguiente criterios de busqueda:
     * <li>Filtrar por rango de fecha de vacunación.
     *
     * @param fechaDesde Fecha desde
     * @param fechaHasta Fecha hasta
     * @return lista de empleados
     */
    public List<Empleado> listarPorFechaVacunacio(Date fechaDesde, Date fechaHasta) {

        return empleadoRepository.findByFechaVacunacionBetween(fechaDesde, fechaHasta);
    }

    /**
     * Lista los empleados con los siguiente criterios de busqueda:
     * <li>Filtrar por estado de vacunacion
     *
     * @param vacunado bandera 1 si esta vacunado / 0 no esta vacunado
     * @return lista de empleados
     */
    public List<Empleado> listarPorVacunacion(Integer vacunado) {
        return empleadoRepository.findByVacunado(vacunado);
    }

    /**
     * Lista los empleados con los siguiente criterios de busqueda:
     * <li>Filtrar por tipo de vacunacion
     *
     * @param tipoVacuna tipo de vacuna
     * @return lista de empleados
     */
    public List<Empleado> listarPorTipoVacuna(TipoVacuna tipoVacuna) {
        return empleadoRepository.findByTipoVacuna(tipoVacuna);
    }
}
