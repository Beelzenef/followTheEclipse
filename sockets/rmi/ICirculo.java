package dam.psp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICirculo extends Remote {
	public void set_radio(double radio) throws RemoteException;
	public double area() throws RemoteException;
	public double longitud() throws RemoteException;
}
