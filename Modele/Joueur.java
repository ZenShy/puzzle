package Modele;

import java.util.ArrayList;

/*
	Les joueurs
 */
public class Joueur<E> {
	// nom du joueur
	protected final String nom;
	// main du joueur
	protected final ArrayList<E> main = new ArrayList<E>();

	Joueur(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public int tailleMain() {
		return main.size();
	}

	public E get(int i) {
		return main.get(i);
	}

	void remove(int i) {
		main.remove(i);
	}

	void add(E ele) {
		main.add(ele);
	}

	public boolean isEmpty() {
		return main.isEmpty();
	}
}
