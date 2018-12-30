package Modele;
/*
	La classe PLateau represente la table ou l'on joue
	Elle est unique et commune a chaque jeu,
	ainsi cette classe realise les actions communent a tout les types de plateaux
 */
class Plateau<E extends Piece> {
	// grille du plateau
	private final Case[][] cases;

	Plateau(int nbligne, int nbcolonne) {
		cases = new Case[nbligne][nbcolonne];
		for (int i = 0; i < nbligne; i++) {
			for (int j = 0; j < nbligne; j++) {
				cases[i][j] = new Case<E>();
			}
		}
	}

	// hauteur du plateau
	public int hauteur() {
		return cases.length;
	}

	// longueur du plateau
	public int longueur() {
		return cases[0].length;
	}

	// ajoute l' element si possible -> verifie si la piece est placeable avec la
	// methode de la classe Piece
	boolean setE(E element, int x, int y) {
		if (correct(x, y) && element.placeable(this, x, y)) {
			cases[x][y].contenu = element;
			return true;
		}
		return false;
	}

	// recupere l'element a la case (x,y) s'il existe
	public E getE(int x, int y) {
		if (correct(x, y) && !cases[x][y].isEmpty()) {
			return (E) cases[x][y].contenu;
		}
		return null;
	}

	// enleve et recupere l'element a la case (x,y) s'il existe
	E TakeE(int x, int y) {
		if (correct(x, y) && !cases[x][y].isEmpty()) {
			E element = (E) cases[x][y].contenu;
			cases[x][y].contenu = null;
			return element;
		}
		return null;
	}

	// verifie si les coordonnees donnees rentrent dans la grille du plateau
	public boolean correct(int x, int y) {
		return (x >= 0 && y >= 0 && x < cases.length && y < cases[0].length);
	}

	// permet de savoir le zoom a realiser
	public int scale() {
		int nb = 0;
		while (nb < Math.min(this.hauteur(), this.longueur()) / 2) {
			for (int i = nb; i < Math.max(this.hauteur(), this.longueur()) - nb; i++) {
				if (this.correct(i, nb)) {

					if (this.getE(i, nb) != null) {
						if (nb - 1 < 0)
							return 0;
						return nb - 1;
					}
				}
				if (this.correct(nb, i)) {
					if (this.getE(nb, i) != null) {
						if (nb - 1 < 0)
							return 0;
						return nb - 1;
					}
				}
				if (this.correct(i, this.longueur() - nb - 1)) {
					if (this.getE(i, this.longueur() - nb - 1) != null) {
						if (nb - 1 < 0)
							return 0;
						return nb - 1;
					}
				}
				if (this.correct(this.hauteur() - nb - 1, i)) {
					if (this.getE(this.hauteur() - nb - 1, i) != null) {
						if (nb - 1 < 0)
							return 0;
						return nb - 1;
					}
				}
			}
			nb++;
		}
		if (nb - 1 < 0)
			return 0;
		return nb - 1;
	}

	// classe correspondant a une case de la grille du plateau
	private static class Case<E extends Piece> {
		E contenu;

		Case(E element) {
			this.contenu = element;
		}

		Case() {
		}

		boolean isEmpty() {
			return this.contenu == null;
		}

	}

}
