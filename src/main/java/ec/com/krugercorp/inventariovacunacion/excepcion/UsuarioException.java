package ec.com.krugercorp.inventariovacunacion.excepcion;

/**
 *  Clase de Exception para errores de la logica de usuario
 *
 * @author gcoello
 */
public class UsuarioException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioException() {
        super();
    }

    public UsuarioException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
