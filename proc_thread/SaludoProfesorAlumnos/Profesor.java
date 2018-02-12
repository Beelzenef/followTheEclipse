package com.geekstorming.hilosprofesoralumnos;

public class Profesor extends Thread {

	String nombre;
	
	Bienvenida saludo;
	
	public Profesor(Bienvenida b, String n)
	{
		this.saludo =  b;
		this.nombre = n;
	}
	
	@Override
	public void run()
	{
		try {
			Thread.sleep(500);
			saludo.ProfesorSaluda();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
