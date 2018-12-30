package Modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
	Il s'agit de l'ensemble que la vue pourra manipuler
*/
public abstract class Jeu<E extends Piece> {
	// Le plateau de jeu
	protected Plateau<E> p;
	// Les joueurs
	protected ArrayList<Joueur<E>> joueurs = new ArrayList<Joueur<E>>();

	// La pioche
	protected final ArrayList<E> pioche = new ArrayList<E>();

	// regarde si la partie est fini
	public abstract boolean stop(Joueur<E> jd);

	// regarde si un joueur peu jouer
	public boolean peuJouer(Joueur<E> jd) {
		if (jd.tailleMain() == 0)
			return false;
		for (int i = 0; i < jd.tailleMain(); i++) {
			E d = jd.get(i);
			if (joueable(d)) {
				return true;
			}

		}
		return false;
	}

	// regarde si une piece est jouable
	public boolean joueable(E d) {
		return true;
	}

	// renvoie le gagnant a la fin de la partie
	public abstract Joueur<E> gagnant();
	
	public Joueur<E> joueur(int i) {
		return joueurs.get(i);
	}
	
	//Nombres de Joueurs
	public int nbJoueurs() {
		return joueurs.size();
	}
	
	public E getE(int x,int y) {
		return p.getE(x, y);
	}
	
	public int hauteur() {
		return p.hauteur();
	}
	
	public int longueur() {
		return p.longueur();
	}
	
	//permet de voir si des coordonnees rentrent dans le plateau
	public boolean correct(int x,int y) {
		return p.correct(x, y);
	}
	
	//permet de reduire les espaces vides
	public int scale () {
		return p.scale();
	}
	
	//action de jouer
	public abstract void joue(Joueur<E> j,int ... attributs) throws NonDoableError;
	
	//si le joueur ne peut plus jouer
	public void actionSecondaire(Joueur<E> j) {
		
	}
}
