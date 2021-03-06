package com.geekstorming.hilosimplerunnable;

class HiloHolaMundo implements Runnable {

	Thread hilo;
	
	// Constructor
	HiloHolaMundo()
	{
		hilo = new Thread(this, "Hilo HolaMundo, con prioridad y padre: ");
		System.out.println("Creando hilo nuevo..." + hilo);
		hilo.start();
	}
	
	// Implementando join() al hilo hijo
	// Así hilo hijo acaba antes que hilo padre (main)
	public void Espera()
	{
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Instrucciones del hilo
	@Override
	public void run() {
		System.out.println("Hilo nuevo ha empezado a ejecutarse");
		System.out.println("Haciendo cosis...");
		
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		
		System.out.println("Finalizando Hilo HolaMundo");
		
	}
}

public class HolaMundo {

	public static void main(String[] args) {
		
		HiloHolaMundo hiloHijo;
		
		System.out.println("Hola desde hilo principal (main)");
		
		// Creando a Hilo HolaMundo
		hiloHijo = new HiloHolaMundo();
		hiloHijo.Espera();
		
		System.out.println("Hilo principal (main) finalizando");

	}
}
