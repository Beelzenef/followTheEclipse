package com.geekstorming.primeraconn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	static final String HOST = "192.168.3.57";
	static final int PUERTO =  5000;
	
	private Scanner entrada;
	
	public Cliente () {
		try {
			
			Socket socketCliente = new Socket(HOST, PUERTO);
			BufferedReader br = new BufferedReader(new InputStreamReader(socketCliente.getInputStream(), "utf8"));
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in, "utf8"));

			// Para limpiar el canal con autoflush
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socketCliente.getOutputStream(), "utf8"), true);
			System.out.println(br.readLine());
			
			// Enviar mensaje pedido por consola al servidor:
			System.out.println("Mensaje para enviar?");
			String msg = teclado.readLine();
			System.out.println("Enviando el mensaje al servidor: " + msg);
			pw.println(msg);
			
			socketCliente.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		
		new Cliente();
	}
	
	private String leerMsg() {
		
		System.out.println("Introduce el mensaje a enviar");
		String msg = entrada.nextLine();
		
		return msg;
		
	}

}
