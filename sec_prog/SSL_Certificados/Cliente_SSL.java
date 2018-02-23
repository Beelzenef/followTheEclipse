package com.geekstorming.certsssl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Cliente_SSL {
	
	private static final String DESTINO = "localhost";
	private static final int PUERTO = 5555;
	
	private void mostrarCifrados(SSLSocket socket) {
		
		String[] protocolosHabilitados = socket.getEnabledProtocols();
		System.out.println("Protocolos habilitados en socket: " );
		for (String item : protocolosHabilitados) {
			System.out.println("Protocolo habilitado: " + item);
		}
		
		String[] protocolosSoportados = socket.getSupportedProtocols();
		System.out.println("Protocolos soportados en socket: " );
		for (String item : protocolosSoportados) {
			System.out.println("Protocolo soportado: " + item);
		}
		
		String[] protocolosDeseados = new String[1];
		protocolosDeseados[0] = "TLSv1.2";
		socket.setEnabledProtocols(protocolosDeseados);
		
		protocolosHabilitados = socket.getEnabledProtocols();
		System.out.println("Protocolos activos: ");
		for (String item : protocolosHabilitados) {
			System.out.println("Protocolo habilitado: " + item);
		}
	}
	
	public Cliente_SSL(String msg) {
		System.out.println("Obteniendo factory del socket cliente");
		SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		try {
			System.out.println("Creando socket cliente");
			SSLSocket socketCliente = (SSLSocket) socketFactory.createSocket(DESTINO, PUERTO);
			
			mostrarCifrados(socketCliente);
			
			PrintWriter pw = new PrintWriter(new BufferedOutputStream(socketCliente.getOutputStream()), true);
			
			pw.println(msg);
			pw.flush();
			
			System.out.println("Mensaje enviado... " + msg);
			
			System.out.println("Esperando respuesta cifrada con hash desde servidor para exponer...");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			
			System.out.println("Mensaje cifrado recibido..." + br.readLine());
			System.out.println("Cerrando conexión...");
			pw.close();
			socketCliente.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		System.setProperty("javax.net.ssl.keyStore", "./certs/almacenCliente");
		System.setProperty("javax.net.ssl.keyStorePassword", "87654321");
		System.setProperty("javax.net.ssl.trustStore", "./certs/almacenCliente");
		System.setProperty("javax.net.ssl.trustStorePassword", "87654321");
		
		new Cliente_SSL("Hola mundo");
	}

}
