package com.geekstorming.compartiendobuffer;

public class CompartiendoBuffer {

	public static void main(String[] args) {
		
		Buffer bCompartido = new BufferCompartido();
		
		Productor productor = new Productor(bCompartido, 10);
		Consumidor consumidor1 = new Consumidor(bCompartido, 6);
		Consumidor consumidor2 = new Consumidor(bCompartido, 4);

		StringBuffer encabezado = new StringBuffer("Operacion");
		encabezado.setLength(40);
		
		bCompartido.mostrarEstado("Iniciando hilos");
		
		productor.start();
		consumidor1.start();
		consumidor2.start();
		
		try {
			productor.join();
			consumidor1.join();
			consumidor2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin de hilo principal");
		
	}
}

interface Buffer {
	
	public int leer();
	public void escribir (int valor);
	public void mostrarEstado(String estado);
}

class BufferCompartido implements Buffer {
	private int buffer = -1;
	private int contadorConValorLegible = 0;

	@Override
	public synchronized int leer() {
		
		// Debe avanzar cuando hay valor legible
		while (contadorConValorLegible == 0)
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Mostrando estado
		mostrarEstado(Thread.currentThread().getName() + " consigue leer " + buffer);
		// Cambia la guarda para productores
		contadorConValorLegible = 0;
		
		notify();
		return buffer;
	}

	@Override
	public synchronized void escribir(int valor) {
		
		// Debe avanzar cuando hay disponible escritura
		while (contadorConValorLegible == 1)
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Mostrando estado
		mostrarEstado(Thread.currentThread().getName() + " trata de escribir");
		// Cargando buffer con el nuevo valor
		buffer = valor;
		// Cambiando la guarda para lectores
		contadorConValorLegible = 1;
		
		notify();
	}

	@Override
	public void mostrarEstado(String estado) {
		StringBuffer linea = new StringBuffer(estado);
		linea.setLength(80);
		linea.append(buffer + " " + contadorConValorLegible);
		System.out.println(linea);
		System.out.println();
	}
	
}

class Productor extends Thread {
	private Buffer compartido;
	private int nProducciones;
	
	public Productor(Buffer b, int nProducciones)
	{
		this.compartido = b;
		this.nProducciones = nProducciones;
	}
	
	@Override
	public void run()
	{
		for (int i = 0; i < nProducciones; i++)
		{
			try {
				Thread.sleep((int) Math.random() * 3001);
				compartido.escribir(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + " ha terminado de producir datos.");
	}
}

class Consumidor extends Thread {
	private Buffer compartido;
	private int nConsumiciones;
	
	private int sumaConsumiciones;
	
	public Consumidor(Buffer b, int nConsumiciones)
	{
		this.compartido = b;
		this.nConsumiciones = nConsumiciones;
	}
	
	@Override
	public void run()
	{
		for (int i = 0; i < nConsumiciones; i++)
		{
			try {
				Thread.sleep((int) Math.random() * 3001);
				sumaConsumiciones += compartido.leer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
		System.out.println(getName() + " ha terminado de consumir datos, " + sumaConsumiciones + " en total");
	}
}
