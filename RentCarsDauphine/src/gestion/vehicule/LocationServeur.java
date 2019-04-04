package gestion.vehicule;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class LocationServeur {

	public static void main(String[] args) {

		try {
			LocateRegistry.createRegistry(1099);
			ILocation serviceLocation = new Location();
			serviceLocation.ajouterVehicule(1, "Mercedes", "C63");
			serviceLocation.ajouterVehicule(2, "BMW", "525");
			serviceLocation.ajouterVehicule(3, "Opel", "Astra");
			Naming.rebind("rmi://localhost:1099/LocationService", serviceLocation);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
