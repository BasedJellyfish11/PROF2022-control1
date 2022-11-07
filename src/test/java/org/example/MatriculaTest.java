package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Vector;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatriculaTest {

    private final double COSTE = 2;
    private final double NUMERO_ASIGNATURAS = 4;


    @Test
    public void testMatriculaAsignaturasNull() {

        Matricula matricula = new Matricula(null);

        try {
            matricula.getImporte();
            Assert.fail("Expected an exception for null vector, but it was never thrown!");
        }
        catch (Exception e) {
            return;
        }

    }

    @Test
    public void testMatriculaAsignaturasSum() {

        Vector<Asignatura> vectorAsignaturas = new Vector<Asignatura>();
        for(int i = 0; i<NUMERO_ASIGNATURAS; ++i){

            Asignatura asignatura = Mockito.mock(Asignatura.class);
            when(asignatura.getImporte()).thenReturn(COSTE);
            vectorAsignaturas.add(asignatura);
        }

        Matricula matricula = new Matricula(vectorAsignaturas);

        try {
            // A delta of 0.000001 is given to avoid floating point errors
            Assert.assertEquals(COSTE*NUMERO_ASIGNATURAS, matricula.getImporte(), 0.000001);
        }
        catch (Exception e) {
            Assert.fail("An unexpected exception was thrown!");
        }

    }


    @Test
    public void testMatriculaAsignaturasVerify() {

        Vector<Asignatura> vectorAsignaturas = new Vector<Asignatura>();
        for(int i = 0; i<NUMERO_ASIGNATURAS; ++i){
            Asignatura asignatura = Mockito.mock(Asignatura.class);
            when(asignatura.getImporte()).thenReturn(COSTE);
            vectorAsignaturas.add(asignatura);
        }

        Matricula matricula = new Matricula(vectorAsignaturas);

        try {

            matricula.getImporte();

        }
        catch (Exception e) {
            Assert.fail("An unexpected exception was thrown!");
        }

        for (Asignatura asignatura: vectorAsignaturas) {
            // Checks that every subject's cost is called
            verify(asignatura).getImporte();
        }

    }
}