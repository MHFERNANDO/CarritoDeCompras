package ec.edu.ups.vista;

import ec.edu.ups.MiExcepcion;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainException {
    public static void main(String [] args){
        int numerador  = 0;
        int denominador = 0;
        int cociente = 0;
        boolean bandera = false;

        Scanner leer = new Scanner(System.in);

        try {
            System.out.println("Ingrese numerador");
            numerador = leer.nextInt();
            System.out.println("Ingrese denominador");
            denominador= leer.nextInt();

            cociente=obtieneCociente(numerador,denominador);
            System.out.println("Resultado "+cociente);
            bandera=true;

        }catch (MiExcepcion e){
            System.err.println("--------------------------------------------------------");
            System.err.println("Error " + e);
            System.err.println("--------------------------------------------------------");
        }catch (InputMismatchException inputMismatchException){
            System.err.println("--------------------------------------------------------");
            System.err.println("Ingrese correctamente");
            System.err.println("--------------------------------------------------------");
        }finally {
            System.out.println("Echo por Fernando Martinez");
        }
    }

    public static int obtieneCociente (int numerador, int denominador) throws MiExcepcion/*El throws es opcional para entender*/{

            int resultado = 0;

            if (denominador==0){
                throw new MiExcepcion("El denominador tiene que ser distinto de cero");
            }

            resultado = numerador / denominador;
            return resultado;
    }
}
