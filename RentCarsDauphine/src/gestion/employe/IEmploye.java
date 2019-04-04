package gestion.employe;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gestion.vehicule.IVehicule;

public interface IEmploye extends Remote {
	public void notification(IVehicule vehicule, int m) throws RemoteException;
	public long getId() throws RemoteException;
	public String getNom() throws RemoteException;
	public String getPrenom() throws RemoteException;
}