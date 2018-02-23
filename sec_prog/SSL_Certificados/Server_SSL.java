package com.geekstorming.certsssl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server_SSL {
	
	private static int PUERTO = 5555;

	public Server_SSL ( ) {
		System.out.println("Obteniendo factory del socket para server");
		SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		
		try {
			System.out.println("Creando socket...");
			SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(PUERTO);
			
			while (true) {
				System.out.println("Aceptando conexiones...");
				SSLSocket socketAtendiendo = (SSLSocket) serverSocket.accept();
				System.out.println("Atendiendo nueva conexión con hilo dedicado...");
				
				new Server_SSL_Hilo(socketAtendiendo).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setProperty("javax.net.ssl.keyStore", "./certs/almacenServer");
		System.setProperty("javax.net.ssl.keyStorePassword", "12345678");
		System.setProperty("javax.net.ssl.trustStore", "./certs/almacenServer");
		System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
		
		new Server_SSL();
	}

}

class Server_SSL_Hilo extends Thread {
	
	SSLSocket socket;
	
	public Server_SSL_Hilo (SSLSocket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			InputStreamReader isr = new InputStreamReader(socket.getInputStream(), "utf8");
			BufferedReader br = new BufferedReader(isr);
			
			String msgRecibido = br.readLine();
			
			System.out.println("Mensaje recibido desde cliente: " + msgRecibido);
			
			PrintWriter pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
			
			byte[] msgEnBytes = msgRecibido.getBytes("utf8");
			
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			
			pw.print(sha.digest(msgEnBytes));
			
			pw.flush();
			pw.close();
			
			System.out.println("Cerrando conexión, fin del mensaje recibido");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
