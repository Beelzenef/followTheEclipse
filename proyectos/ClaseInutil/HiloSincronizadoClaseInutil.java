 
package com.geekstorming.claseinutil;

class Inutil {
	private int a;
	private int b;
	
	// Seccion critica
	// Dos metodos sincronizados
	// Necesitan estar los dos para garantizar el acceso en forma de exclusion mutua
	
	public synchronized void marcar_5()
	{
		a = 5;
		System.out.println("Marcando");
		b = 5;
	}
	
	public synchronized boolean esVerdad()
	{
		return a == 0 || b == 5;
	}
	
	// FIN seccion critica
}

class HiloA extends Thread
{
	private Inutil i;
	
	public HiloA (Inutil in)
	{
		this.i = in;
	}
	
	@Override
	public void run()
	{
		i.marcar_5();
	}
}

class HiloB extends Thread
{
	private Inutil i;
	
	public HiloB (Inutil in)
	{
		this.i = in;
	}
	
	@Override
	public void run()
	{
		if (i.esVerdad())
		{
			System.out.println("Es verdad");
		}
		else
		{
			System.out.println("Es falso");
		}
	}
}

public class HiloSincronizadoClaseInutil {

	public static void main(String[] args) {
		
		Inutil in = new Inutil();
		
		HiloA hA = new HiloA(in);
		HiloB hB = new HiloB(in);

		hA.start();
		hB.start();
		
		try {
			hA.join();
			hB.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
