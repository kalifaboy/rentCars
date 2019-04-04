package gestion.vehicule;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ILocation extends Remote {

	public void ajouterVehicule(long id, String marque, String modele) throws RemoteException;
	public void supprimerVehicule(long id) throws RemoteException;
	public Map<Long, IVehicule> vehiculesDispo() throws RemoteException;
	public Map<Long, IVehicule> vehiculesNonDispo() throws RemoteException;
	public String afficherVehiculesDisp() throws RemoteException;
	public String afficherVehiculesNonDisp() throws RemoteException;
}
