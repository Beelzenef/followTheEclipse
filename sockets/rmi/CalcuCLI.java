package dam.psp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalcuCLI {

	public static final String SERVER = "192.168.3.57";
	public static final int PUERTO = 8889;
	
	public static void main(String[] args) throws RemoteException, NotBoundException  {
		
		ICalculadora calculadora = null;
		
		System.out.println("Localizando el objeto remoto...");

		Registry registro = LocateRegistry.getRegistry(SERVER, PUERTO);
		
		System.out.println("Obteniendo el falso objeto <stub> del remoto");
		calculadora = (ICalculadora) registro.lookup("Calculadora");
		
		if (calculadora != null) {
			System.out.println("Sumando 1 + 1: " + calculadora.suma(1, 1));
			System.out.println("Restando 4 - 2: " + calculadora.resta(4, 2));
			System.out.println("Multiplicando 7 * 3: " + calculadora.producto(7, 3));
			System.out.println("Dividiendo 10 / 2: " + calculadora.division(10, 2));
		}
	}

}