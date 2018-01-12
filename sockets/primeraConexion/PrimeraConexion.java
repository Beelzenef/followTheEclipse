package com.geekstorming.primeraconn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeraConexion {

	static final int PUERTO = 5000;
	static int nClientes = 0;
	
	public PrimeraConexion()
	{
		ServerSocket socketServer;
		
		try {
			socketServer = new ServerSocket(PUERTO);
			
			System.out.println("Servidor escuchando en : "
					+ socketServer.getLocalSocketAddress().toString());
			
			while (true) {
				Socket socketAtendiendo = socketServer.accept();
				nClientes++;
				System.out.println("Atendiendo al cliente número " + nClientes);
				
				// Creamos un hilo para atender al cliente y liberar al socket principal
				new ServidorHilo(socketAtendiendo, nClientes);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
		
		new PrimeraConexion();
	}

}
