package com.geekstorming.modeloudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Receptor {
	
	public static final int PUERTO = 4444;
	public static final String IPEMISOR = "0.0.0.0";
	
	DatagramSocket datagramSocket;
	
	public Receptor () {
		try {
			
			datagramSocket = new DatagramSocket(PUERTO, InetAddress.getByName(IPEMISOR));
			
			System.out.println("Emisor conectado al socket: " + datagramSocket.getLocalAddress());
			
			while (true) {
				// Limpiando buffer
				DatagramPacket dato = new DatagramPacket(new byte[255], 255);
				
				// Se recibe un dato y se escribe por pantalla
				datagramSocket.receive(dato);
				System.out.println("Dato recibido de : " + dato.getAddress().getHostName());
				byte[] contenido = dato.getData();
				
				// Deserializando contenido a String
				System.out.print(new String(contenido));
			}
		
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (datagramSocket != null)
				datagramSocket.close();
		}
	}

}
