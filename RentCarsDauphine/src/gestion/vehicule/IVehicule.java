package gestion.vehicule;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gestion.employe.IEmploye;

public interface IVehicule extends Remote {

	public void notifier(IEmploye emp, int m) throws RemoteException;
	public void louer(IEmploye locataire) throws RemoteException;
	public void restituer() throws RemoteException;
	public void restituer(String msg) throws RemoteException;
	public long getId() throws RemoteException;
	public String getModele() throws RemoteException;
	public String getMarque() throws RemoteException;
	public boolean getDisponibilte() throws RemoteException;
}
