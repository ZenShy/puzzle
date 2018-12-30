package Modele;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/*
	Le jeu de Puzzle
 */
public class JeuPuzzle extends Jeu<Puzzle> {

	public JeuPuzzle(String img, int i,String[] noms) {
		int scale = i * 3;
		p = new Plateau<Puzzle>(scale, scale);
		Joueur<Puzzle> j = new Joueur<Puzzle>(noms[0]);
		joueurs.add(j);
		try {
			BufferedImage image = ImageIO.read(new File(img));
			int cellWidth = image.getWidth(null) / (scale);
			int cellHeight = image.getHeight(null) / (scale);
			Boolean[][] cases = new Boolean[scale][scale];
			for (int u = 0; u < cases.length; u++) {
				for (int v = 0; v < cases[i].length; v++) {
					cases[u][v] = false;
				}
			}
			for (int x = 0; x < (scale); x++) {
				for (int y = 0; y < (scale); y++) {
					int h = y * cellHeight;
					int w = x * cellWidth;
					BufferedImage sousimage = image.getSubimage(w, h, cellWidth, cellHeight);
					Puzzle p = new Puzzle(x + y * (scale), sousimage);
					int xcase = 0;
					int ycase = 0;
					do {
						xcase = (int) (Math.random() * scale);
						ycase = (int) (Math.random() * scale);
					} while (cases[xcase][ycase]);
					cases[xcase][ycase] = true;
					this.p.setE(p, xcase, ycase);
					
					

				}
			}
		} catch (Exception e) {
			// erreur
			System.out.println(e);

		}
	}

	//arguments : 0 -> x1 ; 1 -> y1 ; 2 -> x2 ; 3 -> y2 
	@Override
	public boolean joue(Joueur<Puzzle> j,int[] arguments) {
		Puzzle p1 = p.TakeE(arguments[0], arguments[1]);
		Puzzle p2 = p.TakeE(arguments[2], arguments[3]);
		if (!this.p.setE(p1, arguments[2], arguments[3])) {
			return false;
		}
		if (this.p.setE(p2, arguments[0], arguments[1])) {
			return true;
		}
		this.p.TakeE(arguments[2], arguments[3]);
		return false;
	}
	
	@Override
	public boolean stop(Joueur<Puzzle> jd) {
		for (int i = 0; i < p.longueur(); i++) {
			for (int j = 0; j < p.hauteur(); j++) {
				if (!((Puzzle) p.getE(i, j)).bonneplace(i, j, p)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean peuJouer(Joueur<Puzzle> jd) {
		return true;
	}
	
	@Override
	public Joueur gagnant() {
		return (Joueur) joueurs.get(0);
	}

}
