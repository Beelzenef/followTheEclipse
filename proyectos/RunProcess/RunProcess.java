package com.geekstorming.runprocess;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;

public class RunProcess {

	public static void main(String[] args) {
		// Clase que recibe como parámetro el nombre del proceso a lanzar desde MV
		
		if (args.length <= 0)
		{	
			System.err.println("Falta el nombre del ejecutable");
			System.exit(-1);
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		
		String pid;
		pid = ManagementFactory.getRuntimeMXBean().getName();
		
		System.out.print("El PID del padre es " + pid);
		
		System.out.print("Iniciando proceso:");
		
		try {
			Process proceso = pb.start();
			int retorno = proceso.waitFor();
			System.out.println("La ejecucion de " + Arrays.toString(args) + " devuelve " + retorno);
		}
		catch (IOException | InterruptedException e) {
			System.err.println("Excepcion I/O o proceso interrumpido");
		}

	}

}
