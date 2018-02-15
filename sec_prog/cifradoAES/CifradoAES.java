package com.geekstorming.aes;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class CifradoAES {
	
	private static String CIFRADO = "AES";
	
	public static SecretKey getClaveOpaca (int longitud) throws Exception {
		KeyGenerator claveInstancia = KeyGenerator.getInstance(CIFRADO);
		
		// Por defecto es 128 de longitud
		claveInstancia.init(longitud);
		return claveInstancia.generateKey();
	}
	
	public static SecretKeySpec getClaveTransparente (String miClave) throws Exception {
		byte[] miClaveEnBytes = miClave.getBytes("utf8");
		System.out.println("El hash SHA2 de la clave es: " + DigestUtils.sha256Hex(miClaveEnBytes));
		byte[] miClaveSha256 = Arrays.copyOf(DigestUtils.sha256(miClaveEnBytes), 16);
		
		return new SecretKeySpec(miClaveSha256, CIFRADO);
	}
	
	public static String encriptar(String msg, SecretKey clave) throws Exception {
		Cipher c = Cipher.getInstance(CIFRADO);
		c.init(Cipher.ENCRYPT_MODE,  clave);
		
		byte[] msgClave = c.doFinal(msg.getBytes());
		
		byte[] criptograma = Base64.encodeBase64(msgClave);
		
		return new String(criptograma);
	}
	
	public static String desencriptar(String criptograma, SecretKey clave) throws Exception {
		Cipher c = Cipher.getInstance(CIFRADO);
		c.init(Cipher.DECRYPT_MODE,  clave);
		
		//Primero necesitamos desempaquetar
		byte[] msgDesempaquetado = Base64.decodeBase64(criptograma.getBytes("utf8"));
		
		// Luego desencriptamos
		byte[] msgDesencriptado = c.doFinal(msgDesempaquetado);
		
		return new String(msgDesencriptado);
	}
	
	public static void main(String[] args) {
		String msg = "Programo porque necesito más café y más café porque necesito programar";
		String miClave = "123;abc";
		
		try {
			SecretKey miClaveOpaca = CifradoAES.getClaveOpaca(128);
			System.out.println("Mensaje en claro " + msg);
			
			String criptograma = CifradoAES.encriptar(msg, miClaveOpaca);
			System.out.println("Encriptar " + criptograma);
			System.out.println("Desencriptar " + CifradoAES.desencriptar(criptograma, miClaveOpaca));
			
			// Realizamos otro ejercicio usando una clave transparente
			SecretKeySpec miClaveTransparente = CifradoAES.getClaveTransparente(miClave);
			
			String criptograma2 = CifradoAES.encriptar(msg, miClaveTransparente);
			System.out.println("Encriptar " + criptograma2);
			System.out.println("Desencriptar " + CifradoAES.desencriptar(criptograma2, miClaveTransparente));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
