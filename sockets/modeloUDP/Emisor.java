package com.geekstorming.modeloudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Emisor {
	
	public static final int PUERTOEMISOR = 5555;
	public static final int PUERTORECEPTOR =  4444;
	public static final String IPRECEPTOR = "192.168.3.57";
	
	public Emisor () {
		DatagramSocket emisor;
		DatagramPacket dgp;
		
		InetAddress IPLocal;
		InetAddress IPRemota;
		
		// Construyendo contenido del mensaje a enviar
		byte[] msg = new byte[255];
		String contenidomsg = "hey there!";
		
		System.out.println("Construyendo mensaje...");
		try {
			// Localizando nodos
			IPLocal = InetAddress.getByName("0.0.0.0");
			IPRemota = InetAddress.getByName(IPRECEPTOR);
			
			msg = contenidomsg.getBytes();
			
			emisor = new DatagramSocket(PUERTOEMISOR, IPLocal);
			dgp = new DatagramPacket(msg, msg.length, IPRemota, PUERTORECEPTOR);
			
			emisor.send(dgp);
			System.out.println("Enviando datos...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		new Emisor();

	}

}
