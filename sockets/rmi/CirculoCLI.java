package dam.psp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CirculoCLI {

	public static final String SERVER = "192.168.3.57";
	public static final int PUERTO = 8888;
	
	public static void main(String[] args) throws RemoteException, NotBoundException  {
		
		ICirculo circulo = null;
		
		System.out.println("Localizando el objeto remoto...");

		Registry registro = LocateRegistry.getRegistry(SERVER, PUERTO);
		
		System.out.println("Obteniendo el falso objeto <stub> del remoto");
		circulo = (ICirculo) registro.lookup("Circulo");
		
		if (circulo != null) {
			circulo.set_radio(2);
			System.out.println("Longitud de circunferencia " + circulo.longitud());
			System.out.println("Área del círculo " + circulo.area());
		}
	}

}
