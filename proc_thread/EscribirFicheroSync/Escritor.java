package com.geekstorming.escribirficherosync;

public class Escritor extends Thread {

	private ControladorFichero controladorFichero;
	
	private String contenido = "";
	
	public Escritor(ControladorFichero cF)
	{
		this.controladorFichero = cF;
	}
	
	public void addFrase (String cadena)
	{
		contenido += cadena;
	}
	
	@Override
	public void run() {
		controladorFichero.println(contenido);
	}
}
