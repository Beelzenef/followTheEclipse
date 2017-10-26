import java.util.concurrent.Semaphore;

class Acumulador {
	public static int acumulador = 0;
}

class Sumador extends Thread {

	// Variables
	private int cuenta;
	private Semaphore semaforo;

	// Constructor
	Sumador (int hasta, int id, Semaphore sem) {
		this.cuenta = hasta;
		this.sem = semaforo;
	}

	// Metodos

	// Seccion crítica {
	public void sumar()
	{
		Acumulador.acumulador++;
	}

	// }

	// La ejecución consiste en solicitar recursos, intentar sumar al Acumulador
	// y liberar recursos si lo han conseguido.
	@Override
	public void run()
	{
		for (int i = 0; i < cuenta; i++)
		{
			try {
				semaforo.acquire();
				sumar();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			semaforo.release();
		}
	}
}

public class SeccionCriticaSemaforos {

	private static Sumador sumadores[];
	private static Semaphore semaforo = new Semaphore(1);

	public static void main (String[] args) {

		int n_sum = Integer.parseInt(args[0]);

		sumadores = new Sumador[n_sum];

		for (int i = 0; i < n_sum; i++)
		{
			sumadores[i] = new Sumador (100000, i, semaforo);
			sumadores[i].start();
		}

		for (int i = 0; i < n_sum; i++)
		{
			try {
				sumadores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println ("Acumulador vale : " + Acumulador.acumulador);
	}
}

