package dam.psp;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalcuServer implements ICalculadora {

	@Override
	public float suma(float op1, float op2) throws RemoteException {
		return op1 + op2;
	}

	@Override
	public float resta(float op1, float op2) throws RemoteException {
		return op1 - op2;
	}

	@Override
	public float producto(float op1, float op2) throws RemoteException {
		return op1 * op2;
	}

	@Override
	public float division(float op1, float op2) throws RemoteException {
		if (op2 == 0)
			throw new RemoteException("División por 0 LOLNOPE");
		return op1 / op2;
	}
	
	public CalcuServer(Registry registro) {
		System.out.println("Construyendo servidor... generando registro...");
		try {
			registro.bind("Calculadora", (ICalculadora) UnicastRemoteObject.exportObject(this, 0));
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
