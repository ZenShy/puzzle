package Modele;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
/*
	Piece de puzzle
 */
public class Puzzle implements Piece {
	//place de la piece
	private final int value;
	//image de la piece
	private BufferedImage img=null;
	Puzzle(int v,BufferedImage img) {
		this.value=v;
		this.img=img;
	}
	public BufferedImage getImage() {
		return img;
	}
	
	//remarque : deux pieces identiques ne peuvent pas etre echange
	public boolean bonneplace(int x,int y,Plateau p) {
		return x+y*p.longueur()==value;
	}
}
