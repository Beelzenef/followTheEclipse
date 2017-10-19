package com.geekstorming.hilosinterrumpibles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sun.net.TelnetInputStream;

class HiloInterrumpible implements Runnable {

	private Thread miHilo;
	
	private volatile boolean hiloVivo;
	
	HiloInterrumpible()
	{
		this.miHilo = new Thread(this, "Mi hilo interrumpible");
		this.hiloVivo = true;
		
		miHilo.start();
	}
	
	public boolean hiloVive()
	{
		return this.hiloVivo;
	}
	
	public void interrumpirHilo()
	{
		// Comprobar siempre
		// Si queremos interrumpir, es posible que esté muerto

		if (this.miHilo != null) {
			this.miHilo.interrupt();
			System.out.println("Hilo interrumpido");
		}

		// No vale comprobar boolean hiloVive, puede estar muerto y la propiedad ya no exista
	}
	
	public void detenerHilo()
	{
		System.out.println("Hilo detenido");
		this.hiloVivo = false;
	}
	
	public void esperarAHilo() throws InterruptedException
	{
		this.miHilo.join();
	}
	
	@Override
	public void run() {
		
		System.out.println("Corriendo el nuevo hilo...");
		while (this.hiloVivo)
		{
			System.out.println("Nuevo hilo durmiendo...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				System.out.println("Despertando al nuevo hilo");
			}
		}
		System.out.println("Hilo finalizando");
	}
	
}

public class HilosInterrumpibles {

	public static void main(String[] args) {
		
		// Instanciando nuevo hilo
		HiloInterrumpible nuevoHilo = new HiloInterrumpible();
		
		// Para manejar desde consola:
		BufferedReader entradaConsola = new BufferedReader(new InputStreamReader(System.in));
		String tecla;
		
		System.out.println("Iniciando hilo principal...");
		
		while (nuevoHilo.hiloVive())
		{
			System.out.println("Elige: [I]nterrumpir || [K]ill");
			
			try {
				tecla = entradaConsola.readLine();
				if (tecla.equals("i"))
					nuevoHilo.interrumpirHilo();
				if (tecla.equals("k"))
					nuevoHilo.detenerHilo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			nuevoHilo.esperarAHilo();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}

}
