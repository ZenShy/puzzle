import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class LancerPuzzle extends JFrame implements ActionListener{
    private JPanel pan = new JPanel();
    private JButton ouvrir;
    private JButton commencer;
    private JLabel puzzleImg = new JLabel("Choisissez une image et le niveau qui vous convient");
    private JFileChooser fc;
    private JRadioButton facile,normal,difficile;
    private BufferedImage image = null;
    private int niveau = 0;
    private static String chemin;
    private static File fic;

    

    public LancerPuzzle(){
	this.setTitle("Nouveau Puzzle");
	this.setSize(600,600);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	// EXIT_ON_CLOSE risque de terminer les 2 JFRAME (a  verifier)
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	//pan.setLayout(new FlowLayout(FlowLayout.CENTER));

	facile = new JRadioButton("Facile");
	normal = new JRadioButton("Normal");
	difficile = new JRadioButton("Difficile");

	// Les boutons au sein de ButtonGroup seront unique donc on ne pourra faire qu'un seul choix
	ButtonGroup regroupe = new ButtonGroup();
	regroupe.add(facile);
	regroupe.add(normal);
	regroupe.add(difficile);

	ouvrir = new JButton("Ouvrir");
	ouvrir.addActionListener(this);
	commencer = new JButton("Commencer");
	commencer.addActionListener(this);

	//Ajoutons les elements au JPanel pan

	pan.add(puzzleImg);
	pan.add(ouvrir);
	pan.add(facile);
	pan.add(normal);
	pan.add(difficile);
	pan.add(commencer,BorderLayout.SOUTH);
	this.add(pan);
	this.setVisible(true);
	facile.doClick();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
	//	if(e.getSource() instanceof JButton){ 
	JButton btn = (JButton) e.getSource();
	if(btn.getText().equals("Ouvrir")){
	    fc = new JFileChooser();
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
		fic = fc.getSelectedFile();
		chemin = fic.getPath();
		try{
		    image = ImageIO.read(fic);
		} catch (Exception e1) {
		    System.out.print("Vous devez choisir une image");
		}
	    }
    
	}
	else if (btn.getText().equals("Commencer")){
	    if(facile.isSelected()){
	        niveau = 1;
	    }else if(normal.isSelected()){
		niveau = 2;
	    }else if(difficile.isSelected()){
		niveau = 3;
	    } else {
	    	return;
	    }
	    try {
	    	image = ImageIO.read(new File(chemin));
	    	System.out.println(chemin);
		    image = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
		    BufferedImage puzzleImage = resizeImage(image, 400, 400);
			PuzzleGUI pg = new PuzzleGUI();
	    	this.dispose();
	    	pg.play(puzzleImage,niveau);
	    } catch (Exception ee) {
	    	ee.printStackTrace();
	    }
	}
        
	}
    
    public  static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        int type = 0;
        if(image.getType() == 0)
        	type = BufferedImage.TYPE_INT_ARGB;
        else type = image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height,type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
     }
    
    public static String getChemin() {
    	return chemin;
    }
    
    public static File getFic() {
    	return fic;
    }
}
		
	    
	
