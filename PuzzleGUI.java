import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
    public void play(BufferedImage img, int niveau) {
    	String [] nom = new String[] {"Player 1"};
    	String s = LancerPuzzle.getChemin();
    	jeu = new JeuPuzzle(s,niveau,nom);
    	int h = jeu.hauteur();
    	int l = jeu.longueur();
    	try {
    		img = ImageIO.read(new File(s));
    		for(int i = 0; i<h; i++) {
    			for(int j = 0; j<l; j++) {
    				if(jeu.getE(i,j) != null) {
    				BufferedImage value = jeu.getE(i,j).getImage();
    				cont.add(new ImagePane(value));
    				}
    			}
    		}
    				
    	}catch (Exception e) {
    		System.out.print(e.getMessage());
    		e.printStackTrace();
    	}
    }
    private static class ImagePane extends JPanel {
    	Image img;
    	public ImagePane(Image m) {
    		img = m;
    	}
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		g.drawImage(img,0,0,getWidth(),getHeight(),this);
    	}
    }

}
    
