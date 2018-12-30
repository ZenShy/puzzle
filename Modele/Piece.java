package Modele;
/*
	Les pieces
 */
public interface Piece {
	// Regarde si une place est placeable aux coordonnees voulues dans le plateau p
	default boolean placeable(Plateau p, int x, int y) {
		return p.correct(x, y) && p.getE(x, y) == null;
	}
	
	// Regarde si la piece a la place x1,y1 et compatible a celle de la place x2,y2
	default boolean estCompatible(int x1,int y1,int x2,int y2,Plateau p) {
		return true;
	}

}
