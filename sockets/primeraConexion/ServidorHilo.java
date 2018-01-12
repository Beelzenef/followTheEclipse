package com.geekstorming.primeraconn;

import java.io.BufferedOutputStream;
import java.io.IOException;
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
		PrintWriter pw = null;
		
		try {
			bo = new BufferedOutputStream(socket.getOutputStream());
			// Activamos siempre el autoFlush para asegurarnos un canal de comm limpio
			pw = new PrintWriter(bo, true);
			
			pw.println("¡Bienvenid@ a mi canal");
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
	}

}
