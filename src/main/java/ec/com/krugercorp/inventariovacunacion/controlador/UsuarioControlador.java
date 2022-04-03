package ec.com.krugercorp.inventariovacunacion.controlador;

import ec.com.krugercorp.inventariovacunacion.datos.Usuario;
import ec.com.krugercorp.inventariovacunacion.excepcion.UsuarioException;
import ec.com.krugercorp.inventariovacunacion.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Clase rest para API para usuarios del sistema
 *
 * @author gcoello
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Autenticacion con usuario y contraseña")
    @GetMapping("/autenticar")
    public boolean autenticar(@RequestParam("identificacion") String identificacion,
            @RequestParam("contrasena") String contrasena) {
        try {
            this.usuarioService.autenticar(identificacion, contrasena);
        } catch (UsuarioException ex) {
            return false;
        }
        return true;
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping()
    public ArrayList<Usuario> obtenerUsuarios() {
        return usuarioService.listarTodos();
    }

    @Operation(summary = "Buscar usuario por id")
    @GetMapping(path = "/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @Operation(summary = "Buscar usuario por identificacion")
    @GetMapping("/buscarPor")
    public Optional<Usuario> obtenerPorIdentificacion(@RequestParam("identificacion") String identificacion) {
        return this.usuarioService.obtenerPorIdentificacion(identificacion);
    }
}
