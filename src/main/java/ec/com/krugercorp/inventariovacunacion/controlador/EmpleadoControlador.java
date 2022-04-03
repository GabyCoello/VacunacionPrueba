package ec.com.krugercorp.inventariovacunacion.controlador;

import ec.com.krugercorp.inventariovacunacion.datos.Empleado;
import ec.com.krugercorp.inventariovacunacion.enumeration.TipoVacuna;
import ec.com.krugercorp.inventariovacunacion.excepcion.EmpleadoException;
import ec.com.krugercorp.inventariovacunacion.services.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase rest para API de empleados 
 *
 * @author gcoello
 */
@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {

    @Autowired
    EmpleadoService empleadoService;

    @Operation(summary = "Guardar/Modificar empleado")
    @PostMapping()
    public ResponseEntity<Empleado> guardarEmpleado(@Valid @RequestBody Empleado empleado) {
        return ResponseEntity.ok(this.empleadoService.guardarUsuario(empleado));
    }

    @Operation(summary = "Eliminar empleado por id")
    @DeleteMapping(path = "/{id}")
    public boolean eliminarPorId(@PathVariable("id") Long id) {
        return this.empleadoService.eliminarEmpelado(id);
    }

    @Operation(summary = "Actualizar infromación de vacunación del empleado")
    @PutMapping()
    public boolean ActualizarEmpleado(@RequestBody Empleado empleado) {
        try {
            this.empleadoService.ActualizarInformacion(empleado);
        } catch (EmpleadoException ex) {
            return false;
        }
        return true;
    }

    @Operation(summary = "Listar todos los empleados")
    @GetMapping()
    public ArrayList<Empleado> listarTodos() {
        return empleadoService.listarTodos();
    }

    @Operation(summary = "Buscar empleado por id")
    @GetMapping(path = "/{id}")
    public Optional<Empleado> obtenerEmpleadoPorId(@PathVariable("id") Long id) {
        return this.empleadoService.obtenerPorId(id);
    }

    @Operation(summary = "Buscar empleado por identificacion")
    @GetMapping("/buscarPor")
    public Optional<Empleado> obtenerPorIdentificacion(@RequestParam("identificacion") String identificacion) {
        return this.empleadoService.obtenerPorIdentificacion(identificacion);
    }

    @Operation(summary = "Buscar empleados vacunados o sin vacunar (1/0)")
    @GetMapping("/listarPorVacunacion")
    public List<Empleado> listarPorVacunacion(
            @RequestParam(name = "vacunado") Integer vacunado) {
        return this.empleadoService.listarPorVacunacion(vacunado);
    }

    @Operation(summary = "Buscar empleados por tipo de vacunacion")
    @GetMapping("/listarPorTipoVacuna")
    public List<Empleado> listarPorTipoVacuna(
            @RequestParam(name = "vacunado") TipoVacuna tipoVacuna) {
        return this.empleadoService.listarPorTipoVacuna(tipoVacuna);
    }

    @Operation(summary = "Buscar empleados por fecha de vacunacion")
    @GetMapping("/listarPorFechaVacunacio")
    public List<Empleado> listarPorFechaVacunacio(
            @RequestParam(name = "fechaDesde") Date fechaDesde,
            @RequestParam(name = "fechaHasta") Date fechaHasta) {
        return this.empleadoService.listarPorFechaVacunacio(fechaDesde, fechaHasta);
    }
}
