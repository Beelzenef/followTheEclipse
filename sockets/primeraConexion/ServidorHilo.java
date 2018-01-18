package com.geekstorming.primeraconn;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorHilo extends Thread {
	
	int nCliente;
	Socket socket;
	
	String msg;
	
	public ServidorHilo(Socket s, int nCliente)
	{
		this.socket = s;
		this.nCliente = nCliente;
		msg = "¡Conectado!";
	}
	
	@Override
	public void run()
	{
		BufferedOutputStream bo;
		BufferedInputStream bip;
		BufferedReader br;
		PrintWriter pw = null;
		
		try {
			// Activamos siempre el autoFlush para asegurarnos un canal de comm limpio
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf8"), true);
			
			pw.println("¡Bienvenid@ a mi canal :)");
			
			// Espera una respuesta en forma de string desde el cliente:
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf8"));
			System.out.print("Mensaje recibido desde el cliente: " );
			System.out.println(br.readLine());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (pw != null)
		{
			// Este código debería ser innecesario, pero especificamos por si fuera útil
			// en alguna implementación futura de ejercicios similares
			pw.flush();
			pw.close();
		}
		
		// Cerrando conexión
		try {
			socket.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
