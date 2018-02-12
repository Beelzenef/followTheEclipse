package com.geekstorming.procesosconectados;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;

public class RunProcessConectados {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length < 0)
		{
			System.err.println("Se necesita al menos un proceso como argumento para ejecutar");
			System.exit(-1);
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true);
		
		try	{
			// Creando proceso hijo
			Process proceso = pb.start();
			MostrarSalidaProceso(proceso);
			System.exit(0);
		}
		catch (IOException e)
		{
			System.err.println("Error IO");
			System.exit(-1);
		}
		
	}
	
	private static void MostrarSalidaProceso(Process p)
	{
		try {
			// En esta línea, en ejecución o finalizado, imposible de saber
			int retorno = p.waitFor();
			System.out.println("Proceso hijo devuelve " + retorno);
			
			// Aquí el proceso hijo ya ha finalizado (NO MUERTO)
			// Por tanto, sigue pudiendo dar información:
			
			// Conectamos la salida del proceso en una codificación adecuada para poder leer
			InputStreamReader lectorInfo = new InputStreamReader(p.getInputStream(), "utf-8");
			BufferedReader lectorBuffer = new BufferedReader(lectorInfo);
			
			String linea;
			
			while ((linea = lectorBuffer.readLine()) != null)
			{
				System.out.println(linea);
			}
		}
		catch (InterruptedException | IOException e)
		{
			e.printStackTrace();
		}
	}

}
