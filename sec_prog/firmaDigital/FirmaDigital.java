package com.geekstorming.firmadigital;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;

public class FirmaDigital {

	private static final String ALG1 = "DSA";
	private static final String ALG2 = "RSA";
	
	private static final String PROTOCOLO1 = "DSA";
	private static final String PROTOCOLO2 = "SHA256WithRSA";
	
	// DSA es más rápido para la firma, RSA es mejor para verificación
	// DSA solo puede usar para firma y verificación
	// Mientras que RSA también se puede usar para encriptar/desencriptar
	
	
	private static String firmar (String msg, KeyPair clave) throws Exception {
		
		System.out.println("Creando objeto de tipo Signature...");
		//Signature contenedorFirma = Signature.getInstance(PROTOCOLO1);
		Signature contenedorFirma = Signature.getInstance(PROTOCOLO2);
		System.out.println("Firmando mensaje... usando parte PRIVADA de clave asimétrica...");
		contenedorFirma.initSign(clave.getPrivate());
		contenedorFirma.update(msg.getBytes("utf8"));
		
		byte[] firma = contenedorFirma.sign();
		
		return new String(Base64.getEncoder().encodeToString(firma));
	}
	
	private static boolean esFirmaValida (String msgEnClaro, String firma, KeyPair clave) throws Exception {
		
		System.out.println("Creando objeto de tipo Signature");
		//Signature contenedorFirma = Signature.getInstance(PROTOCOLO1);
		Signature contenedorFirma = Signature.getInstance(PROTOCOLO2);
		
		// Deshaciendo padding de 64b
		byte[] msgEnBytes = Base64.getDecoder().decode(firma.getBytes("utf8"));
		
		System.out.println("Verificando firma... usando parte PÚBLICA de clave asimétrica...");
		contenedorFirma.initVerify(clave.getPublic());
		
		contenedorFirma.update(msgEnClaro.getBytes("utf8"));
		
		return contenedorFirma.verify(msgEnBytes);
	}
	
	public static void main(String[] args) throws Exception {
		
		String msg = "Programo porque necesito más café y más café porque necesito programar";
		
		System.out.println();
		//System.out.println("Obtenienddo el generador de claves: " + ALG1);
		System.out.println("Obtenienddo el generador de claves: " + ALG2);
		
		//KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALG1);
		KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALG2);
		KeyPair clave = keygen.generateKeyPair();
		
		System.out.println("Firmando mensaje...");
		System.out.println("Mensaje a firmar... '" + msg + "'");
		
		String firma = firmar(msg, clave);
		
		System.out.println("Resultado de la firma: " + firma);
		System.out.println("Validando firma...");

		if (esFirmaValida(msg, firma, clave)) {
			System.out.println("Mensaje validado, verificación correcta");
		}
		else {
			System.out.println("Firma no válida");
		}
		
		System.out.println();
		System.out.println("Alterando mensaje...");
		String msgAlterado = msg + ":D";
		
		if (esFirmaValida(msgAlterado, firma, clave)) {
			System.out.println("Mensaje validado, verificación correcta");
		}
		else {
			System.out.println("Firma no válida");
		}
		
	}

}
