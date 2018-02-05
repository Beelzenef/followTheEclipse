package dam.psp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote {

	public float suma(float op1, float op2) throws RemoteException;
	public float resta(float op1, float op2) throws RemoteException;
	public float producto(float op1, float op2) throws RemoteException;
	public float division(float op1, float op2) throws RemoteException;
	
}
