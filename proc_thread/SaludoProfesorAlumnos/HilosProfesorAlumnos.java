package com.geekstorming.hilosprofesoralumnos;

public class HilosProfesorAlumnos {

	public static void main(String[] args) {
	
		Bienvenida b = new Bienvenida();
		
		int nAlumnos = 10;
		
		Alumno[] listaAlumnos = new Alumno[nAlumnos];
		
		for (int i = 0; i < nAlumnos; i++)
		{
			listaAlumnos[i] = new Alumno(b, "Alumno " + i);
			listaAlumnos[i].start();
		}
		
		
		Profesor profe = new Profesor(b, "Eliseo");
		profe.start();
		
		try {
			profe.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

