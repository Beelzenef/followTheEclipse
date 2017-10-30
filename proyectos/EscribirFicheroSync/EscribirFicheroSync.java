package com.geekstorming.escribirficherosync;

public class EscribirFicheroSync {

	public static void main(String[] args) {
		
		ControladorFichero cF = new ControladorFichero("poema.txt");
		
		String parrafo1 = "Android es mi hogar, Android Studio mi castigo";
		String parrafo2 = "Que alguien me de otro IDE, por mi salud mental";
		
		Escritor primerEscritor = new Escritor(cF);
		Escritor segundoEscritor = new Escritor(cF);
		
		primerEscritor.addFrase(parrafo1);
		segundoEscritor.addFrase(parrafo2);
		
		// Arrancando hilos
		primerEscritor.start();
		segundoEscritor.start();
		
		try {
			primerEscritor.join();
			segundoEscritor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Cerrando ficheors
		cF.close();
		System.out.println("Datos escritos");
	}

}
