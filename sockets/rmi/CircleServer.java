package dam.psp;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CircleServer implements ICirculo {
	
	private final double PI = Math.PI;
	
	private double radio;

	@Override
	public void set_radio(double radio) throws RemoteException {
		this.radio = radio;
	}

	@Override
	public double area() throws RemoteException {
		return PI * this.radio * this.radio;
	}

	@Override
	public double longitud() throws RemoteException {
		return 2 * PI * this.radio;
	}
	
	public CircleServer(Registry registro) {
		System.out.println("Construyendo servidor... generando registro...");
		try {
			registro.bind("Circulo", (ICirculo) UnicastRemoteObject.exportObject(this, 0));
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws RemoteException {
		final int puerto = 8888;
		
		System.setProperty("java.rmi.server.hostname", "192.168.3.57");
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		Registry registro = LocateRegistry.createRegistry(puerto);
		new CircleServer(registro);
	}

}
