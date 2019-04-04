package gestion.vehicule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Location extends UnicastRemoteObject implements ILocation {
	
	
	private static final long serialVersionUID = 929867797072212935L;
	private Map<Long, IVehicule> vehicules;

	protected Location() throws RemoteException {
		super();
		vehicules = new HashMap<>();
	}

	@Override
	public void ajouterVehicule(long id, String marque, String modele) throws RemoteException {
		this.vehicules.put(Long.valueOf(id), new Vehicule(id, marque, modele));
	}

	@Override
	public void supprimerVehicule(long id) throws RemoteException {
		this.vehicules.remove(Long.valueOf(id));
	}

	@Override
	public Map<Long, IVehicule> vehiculesDispo() throws RemoteException {
		Map<Long, IVehicule> tmp = new HashMap<>();
		for(IVehicule v : this.vehicules.values()){
			if(v.getDisponibilte()) {
				tmp.put(Long.valueOf(v.getId()), v);
			}
		}
		return tmp;
	}

	@Override
	public Map<Long, IVehicule> vehiculesNonDispo() throws RemoteException {
		Map<Long, IVehicule> tmp = new HashMap<>();
		for(IVehicule v : this.vehicules.values()){
			if(!v.getDisponibilte()) {
				tmp.put(Long.valueOf(v.getId()), v);
			}
		}
		return tmp;
	}

	@Override
	public String afficherVehiculesDisp() throws RemoteException {
		Map<Long, IVehicule> tmp = vehiculesDispo();
		StringBuilder str = new StringBuilder();
		str.append("\n*************** VEHICULES DISPONIBLES A LA LOCATION ******************\n");
		for(IVehicule v : tmp.values()){
			str.append("id : " + v.getId() + "| marque :" + v.getMarque() + "| modèle : " + v.getModele() + "\n");
		}
		str.append("************************************************************************\n");
		return str.toString();
	}

	@Override
	public String afficherVehiculesNonDisp() throws RemoteException {
		Map<Long, IVehicule> tmp = vehiculesNonDispo();
		StringBuilder str = new StringBuilder();
		str.append("\n*************** VEHICULES DEJA EN LOCATION ***************************\n");
		for(IVehicule v : tmp.values()){
			str.append("id : " + v.getId() + "| marque :" + v.getMarque() + "| modèle : " + v.getModele() + "\n");
		}
		str.append("************************************************************************\n");
		return str.toString();
	}

}
