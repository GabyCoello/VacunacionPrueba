package ec.com.krugercorp.inventariovacunacion.enumeration;

import java.util.Arrays;
import java.util.List;

/**
 *  clase enumeracion para clasificar los tipos de vacunas aplicadas en la compañia
 
 * @author gcoello
 */
public enum TipoVacuna {

    SPUTNIK("Sputnik"),
    ASTRAZENECA("AstraZeneca"),
    PFIZER("Pfizer"),
    JHONSON_JHONSON("Jhonson&Jhonson"),
	SINOVAC("Sinovac");
	
    private final String descripcion;
    public static final List<TipoVacuna> ENUMS = Arrays.asList(TipoVacuna.values());

    private TipoVacuna(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
