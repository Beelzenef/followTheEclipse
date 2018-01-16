package com.geekstorming.primeraconn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	static final String HOST = "192.168.3.57";
	static final int PUERTO =  5000;
	
	private Scanner entrada;
	
	public Cliente () {
		try {
			
			Socket socketCliente = new Socket(HOST, PUERTO);
			InputStreamReader isr = new InputStreamReader(socketCliente.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			System.out.println(br.readLine());
			
			// Enviar mensaje pedido por consola al servidor:
			
			
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
