package com.geekstorming.listaaarrobas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ListaAArrobas {

	public static void main(String[] args) {
		
		// Objetivo, conectar procesos con pipes
		ProcessBuilder pbA = new ProcessBuilder("/bin/ls", "-l", "/home/usuario");
		ProcessBuilder pbB = new ProcessBuilder("/usr/bin/tr", "'a'", "'@'");
		
		// Creando procesos
		Process pA;
		Process pB;
		
		try {
			
		// Construcción de procesos
		pA = pbA.start();
		pB = pbB.start();
		
		// Leyendo salida de A
		BufferedReader salidaA = new BufferedReader(
				new InputStreamReader(pA.getInputStream(), "utf-8"));
		
		// BufferedWriter entradaB;
		BufferedWriter entradaB = new BufferedWriter(
				new OutputStreamWriter(pB.getOutputStream()));
		
		// BufferedReader salidaB;
		BufferedReader salidaB = new BufferedReader(
				new InputStreamReader(pB.getInputStream(), "utf-8"));
		
		// Bucle de lectura/escritura
		String resultadoA;
		
		while ((resultadoA = salidaA.readLine()) != null)
		{
			entradaB.write(resultadoA);
			entradaB.newLine();
		}
		
		salidaA.close();
		entradaB.close();
		
		// Salida pB por consola
		String resultadoB;
		while ((resultadoB = salidaB.readLine()) != null)
		{
			System.out.println(resultadoB);
		}
		
		// Salidas de los dos procesos
		int finA = pA.waitFor();
		int finB = pB.waitFor();
		
		System.out.println("Proceso A finaliza: " + finA + " y proceso B: " + finB);

		}
		catch ( IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
