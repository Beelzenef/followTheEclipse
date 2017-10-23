package com.geekstorming.raceconditions;

import java.util.concurrent.atomic.AtomicInteger;

class Contador {
	
	//public static volatile int cuenta = 0;
	public final static AtomicInteger cuenta = new AtomicInteger(0);
	
}

class Sumador extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			//Contador.cuenta++;
			Contador.cuenta.incrementAndGet();
		}
	}
}

class Restador extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 5000; i++) {
			//Contador.cuenta--;
			Contador.cuenta.decrementAndGet();
		}
	}
}

public class HilosRaceConditions {

	public static void main(String[] args) {
		
		Sumador sum = new Sumador();
		Restador res = new Restador();
		
		sum.start();
		res.start();
		
		try {
			sum.join();
			res.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Valor final de contador " + Contador.cuenta);
	}
}


