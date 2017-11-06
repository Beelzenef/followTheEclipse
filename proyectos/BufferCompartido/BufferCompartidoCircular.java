package com.geekstorming.compartiendobuffer;

public class BufferCompartidoCircular implements Buffer {

	private int[] buffer = {-1, -1, -1};
	private int contadorOcupado = 0;
	private int posLectura = 0;
	private int posEscritura = 0;
	
	@Override
	public synchronized int leer() {
		
		String hiloLlamador = Thread.currentThread().getName();
		
		while (contadorOcupado == 0)
		{
			
			System.out.println(hiloLlamador + " intenta leer");
			mostrarEstado("Bufer vacío, " + hiloLlamador + " debe esperar");
		
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		int valorLeido = buffer[posLectura];
		contadorOcupado--;
		posLectura = (posLectura + 1) % buffer.length;
		mostrarEstado(hiloLlamador + " consigue leer " + valorLeido);
		mostrarSalida();
		
		notify();
		return valorLeido;
	}

	@Override
	public synchronized void escribir(int valor) {

		String hiloLlamador = Thread.currentThread().getName();
		
		while (contadorOcupado == buffer.length)
		{
			
			System.out.println(hiloLlamador + " intenta escribir");
			mostrarEstado("Bufer lleno, " + hiloLlamador + " debe esperar");
		
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		buffer[posEscritura] = valor;
		contadorOcupado++;
		posEscritura = (posEscritura + 1) % buffer.length;
		mostrarEstado(hiloLlamador + " consigue escribir " + valor);
		mostrarSalida();
		
		notify();

	}

	@Override
	public void mostrarEstado(String estado) {
		StringBuffer linea = new StringBuffer(estado);
		linea.setLength(80);
		linea.append(buffer + " " + contadorOcupado);
		System.out.println(linea);
		System.out.println();
	}
	
	public String mostrarSalida()
	{
		String salida = "(huecos ocupados : " + contadorOcupado + ")\nhuecos    ";
		for (int i = 0; i < buffer.length; i++)
		{
			salida += " " + buffer[i] + " "; 
		}
		salida += "\n     ";
		for (int i = 0; i < buffer.length; i++)
		{
			if (i == posEscritura && posEscritura == posLectura)
				salida = "EL ";
			else if (i == posEscritura)
				salida += "  E  ";
			else if (i == posLectura)
				salida += "  L   ";
			else
				salida += "      ";
		}
		
		salida += "\n    ";
		System.out.println(salida);
		
		return salida;
	}

}
 
