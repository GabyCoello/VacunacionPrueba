/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.krugercorp.inventariovacunacion;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.com.krugercorp.inventariovacunacion.controlador.EmpleadoControlador;
import ec.com.krugercorp.inventariovacunacion.datos.Empleado;
import ec.com.krugercorp.inventariovacunacion.services.EmpleadoService;

import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author mcoello
 */
@WebMvcTest(EmpleadoControlador.class)
public class EmpleadoControladorTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    EmpleadoService empleadoService;

    Empleado RECORD_1 = new Empleado(null,
            "1234567890", "Cristian", "Vaca", "cvaca@gmail.com",
            null, null, null, 0, null, null, 0);
    Empleado RECORD_2 = new Empleado(1L,
            "0123456789", "Johana", "Altamirano", "jaltamirano@gmail.com",
            null, null, null, 0, null, null, 0);

    @Test
    public void testListarTodos() throws Exception {
        ArrayList<Empleado> records = new ArrayList<>(Arrays.asList(RECORD_1));

        Mockito.when(empleadoService.listarTodos()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/empleados")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGuardar() throws Exception {

        Mockito.when(empleadoService.guardarUsuario(RECORD_1)).thenReturn(RECORD_2);

        MockHttpServletRequestBuilder mockRequest
                = MockMvcRequestBuilders.post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(RECORD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

}
