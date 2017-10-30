package com.geekstorming.hilosprofesoralumnos;

public class Alumno extends Thread {

	String nombre;
	
	Bienvenida saludo;
	
	public Alumno(Bienvenida b, String n)
	{
		this.saludo =  b;
		this.nombre = n;
	}
	
	@Override
	public void run()
	{
		try {
			Thread.sleep(100);
			// Saludará cuando pueda
			// No es su misión comprobar, su misión es solo saludar
			saludo.SaludarAlProfesor(nombre);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
