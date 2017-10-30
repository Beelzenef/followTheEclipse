package com.geekstorming.hilosprofesoralumnos;

public class Bienvenida {
	
	private boolean comienzaClase;
	
	public Bienvenida()
	{
		this.comienzaClase = false;
	}
	
	public synchronized void SaludarAlProfesor(String nAlumno)
	{
		System.out.println("¡Soy " + nAlumno + " y quiero saludar!");
		
		while (!comienzaClase) {
			try {
				this.wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Buenos días, soy " + nAlumno);
		
	}
	
	public synchronized void ProfesorSaluda()
	{
		System.out.println("Buenos días, alumn@s!");
		this.comienzaClase = true;
		this.notifyAll();
		
		/* Jijiijijijij
		this.notify();
		this.notify();
		this.notify();
		this.notify();
		this.notify();
		*/
	}

}
