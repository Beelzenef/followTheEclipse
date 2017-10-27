package com.geekstorming.seccioncriticasync;

class DatoCompartido {
	
	public static int cont1 = 0;
	public static int cont2 = 0;
	
	static Object mutex = new Object();
	static Object mutex2 = new Object();
	
	public static void increaseCont1()
	{
		synchronized(mutex) { cont1++; }
	}
	
	public static void increaseCont2()
	{
		synchronized(mutex2) { cont2++; }
	}
}

class HilosMutex extends Thread {
	
	@Override
	public void run() {
		
		DatoCompartido.increaseCont1();
		DatoCompartido.increaseCont2();
	}
}

public class HiloSeccionCriticaSincronizadas {

	public static void main(String[] args) {
	
		int nHilos = 10000;//Integer.parseInt(args[0]);
		
		System.out.println("Creando " + nHilos + " hilos");
		
		HilosMutex hilos[] = new HilosMutex[nHilos];
		for (int i = 0; i < nHilos; i++) {
			hilos[i] = new HilosMutex();
			hilos[i].start();
		}
		
		for (int i = 0; i < nHilos; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Contador 1 vale " + DatoCompartido.cont1 + " y contador 2 vale " + DatoCompartido.cont2);
	}

}
