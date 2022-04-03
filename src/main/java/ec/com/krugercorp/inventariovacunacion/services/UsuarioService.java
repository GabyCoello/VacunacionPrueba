package ec.com.krugercorp.inventariovacunacion.services;

import ec.com.krugercorp.inventariovacunacion.datos.Usuario;
import ec.com.krugercorp.inventariovacunacion.excepcion.UsuarioException;
import ec.com.krugercorp.inventariovacunacion.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UsuarioService.java clase de servicios para usuarios del sistema
 *
 * @author gcoello
 */

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Actualiza datos del usuario. Si no existe se almacena si existe se actualiza
     * Cuando se registra un usuario nuevo se crea un usuario para el ingreso al sistema
     * 
     * @param usuario entidad usuario que se va a modificar/guardar
     * @return
     */
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Elimina usuario con primary key
     *
     * @param id primary key de usuario
     * @return retorna true si se elimino con exito, caso contrario retorna
     * false
     */
    public boolean eliminarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    /**
     * Autenticar usuario con identificacion y contraseña
     *
     * @param identificacion
     * @param contraseña
     * @return retorna true si se elimino con exito, caso contrario retorna
     * false
     * @throws ec.com.krugercorp.inventariovacunacion.excepcion.UsuarioException
     */
    public Usuario autenticar(String identificacion, String contraseña) throws UsuarioException {
        Usuario usuario = usuarioRepository.findByIdentificacion(identificacion).orElse(null);

        if (usuario == null || !usuario.getContrasena().equals(contraseña)) {
            throw new UsuarioException("Usuario/Contraseña incorrecta.");
        }
        return usuario;
    }

    /**
     * Lista todos los usuario de la tabla
     *
     * @return lista de usuarios
     */
    public ArrayList<Usuario> listarTodos() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    /**
     * Busca un usuario con el primary key id
     *
     * @param id primary key de usuario
     * @return retorna usuario en el caso que lo encuentre, caso contrario
     * retorna vacio
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Busca usuario con identificacion (cedula)
     *
     * @param identificacion identificacion del usuario
     * @return retorna la entidad UsuarioModel
     */
    public Optional<Usuario> obtenerPorIdentificacion(String identificacion) {
        return usuarioRepository.findByIdentificacion(identificacion);
    }

}
