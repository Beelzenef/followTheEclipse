package com.geekstorming.aes;

import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CifradoAES {
	
	private static String CIFRADO = "AES";
	
	public static SecretKey getClaveOpaca (int longitud) throws Exception {
		KeyGenerator claveInstancia = KeyGenerator.getInstance(CIFRADO);
		
		// Por defecto es 128 de longitud
		claveInstancia.init(longitud);
		return claveInstancia.generateKey();
	}
	
	public static SecretKeySpec getClaveTransparente (String miClave) throws Exception {
		byte[] miClaveEnBytes = miClave.getBytes("utf-8");
		System.out.println("El hash SHA2 de la clave es: " + DigestUtils.sha259Hex(miClaveEnBytes));
		byte[] miClaveSha256 = Arrays.copyOf(DigestUtils.sha256(miClaveEnBytes), 16);
		
		return new SecretKeySpec(miClaveSha256, CIFRADO);
	}
	
	public static void main(String[] args) {
		
	}

}
