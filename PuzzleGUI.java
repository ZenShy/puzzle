import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Modele.JeuPuzzle;

public class PuzzleGUI extends JFrame{
    private static JPanel zonePuzzle;
    //private static BufferedImage fond = loadImage("index.jpg");
    // (si on a le temps j'ajoute une image de fond RisinCrew
    // manque un jpanel / jlabel pour le nom des joueurs
    private JToolBar toolbar = new JToolBar();
    private JButton nouveau;
    private JLabel createur = new JLabel ("Dao Thauvin et Frederic NGO");
    private static Container cont;
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
    	try {
			img = ImageIO.read(LancerPuzzle.getFic());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	new JeuPuzzle(s,niveau,nom);
    }
    
}
    
