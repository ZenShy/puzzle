import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Modele.Jeu;
import Modele.JeuPuzzle;
import Modele.Plateau;
import Modele.Puzzle;

public class PuzzleGUI extends JFrame{
    private static JPanel zonePuzzle;
    //private static BufferedImage fond = loadImage("index.jpg");
    // (si on a le temps j'ajoute une image de fond RisinCrew
    // manque un jpanel / jlabel pour le nom des joueurs
    private JToolBar toolbar = new JToolBar();
    private JButton nouveau;
    private JLabel createur = new JLabel ("Dao Thauvin et Frederic NGO");
    private static Container cont;
    static JeuPuzzle jeu;
    public PuzzleGUI(){
	this.setTitle("Puzzle Game");
	this.setSize(800,600);
	this.setMinimumSize(new Dimension(800,600));
	this.setMaximumSize(new Dimension(800,600));
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	cont = this.getContentPane();
	
	

	nouveau = new JButton("Nouveau");
	nouveau.addActionListener(new nouveauListener());
	zonePuzzle = new JPanel();
	zonePuzzle.setOpaque(true);
	zonePuzzle.setBackground(Color.black);

	toolbar.add(nouveau);
	createur.setFont(new Font("Times New Roman",Font.PLAIN,20));
	createur.setForeground(Color.black);
	toolbar.add(createur);

	zonePuzzle.add(new JLabel(new ImageIcon("index.jpg")));
	cont.add(toolbar, BorderLayout.NORTH);
	cont.add(zonePuzzle, BorderLayout.CENTER);
	
    }

    //

    class nouveauListener implements ActionListener{
	@Override
		public void actionPerformed(ActionEvent e) {
			LancerPuzzle play = new LancerPuzzle();
		}	
    }
    public static void play(BufferedImage img, int niveau) {
    	String [] nom = new String[] {"Player 1"};
    	String s = LancerPuzzle.getChemin();
    	new JeuPuzzle(s,niveau,nom);
    	LinkedList<BufferedImage>inchallah = new LinkedList<BufferedImage>();
    	int h = jeu.hauteur();
    	int l = jeu.longueur();
    	Plateau p = new Plateau<Puzzle>(l,h);
    	try {
    		img = ImageIO.read(new File(s));
    		int x = img.getWidth()/l;
    		int y = img.getHeight()/h;
    		for(int i = 0; i<x; i++) {
    			for(int j = 0; j<y; j++) {
    				BufferedImage value = jeu.getE(x,y).getImage();
    				inchallah.add(value);
    			}
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
}
    
