package gestion.vehicule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import gestion.employe.IEmploye;

public class Vehicule extends UnicastRemoteObject implements IVehicule {
	
	
	private static final long serialVersionUID = -3455567551064548139L;
	private long id;
	private String marque;
	private String modele;
	private IEmploye locataire;
	private boolean libre = true;
	private Queue<IEmploye> listeDAttente = null;
	private Map<Long, String> notes = null;
	
	public Vehicule() throws RemoteException{}
	
	public Vehicule(long id, String marque, String modele) throws RemoteException{
		this.notes = new HashMap<>();
		this.listeDAttente = new LinkedList<>();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
	}

	@Override
	public void notifier(IEmploye emp, int m) throws RemoteException {
		emp.notification(this, m);
	}

	@Override
	public void louer(IEmploye locataire) throws RemoteException{
		if(this.libre){
			this.libre = false;
			this.locataire = locataire;
		}else{
			notifier(locataire, 2);
			this.listeDAttente.add(locataire);
		}
	}

	@Override
	public void restituer() throws RemoteException {
		if(this.locataire != null){
			this.locataire = null;
			this.libre = true;
			IEmploye locSuivant = this.listeDAttente.poll();
			if(locSuivant != null){
				this.notifier(locSuivant, 1);
				this.louer(locSuivant);
			}
		}
	}
	
	@Override
	public void restituer(String msg) throws RemoteException {
		if(this.locataire != null){
			ajouterNote(this.locataire, msg);
			this.locataire = null;
			this.libre = true;
			IEmploye locSuivant = this.listeDAttente.poll();
			if(locSuivant != null){
				this.notifier(locSuivant, 1);
				this.louer(locSuivant);
			}
		}
		
	}

	@Override
	public long getId() throws RemoteException {return this.id;}

	@Override
	public String getModele() throws RemoteException {return this.modele;}

	@Override
	public String getMarque() throws RemoteException {return this.marque;}
	
	@Override
	public boolean getDisponibilte() throws RemoteException {return this.libre;}

	
	private void ajouterNote(IEmploye emp, String note) throws RemoteException {
		
		notes.put(Long.valueOf(emp.getId()), note);
	}

	

}
