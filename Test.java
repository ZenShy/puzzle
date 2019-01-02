import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args){
    	try {
    		EventQueue.invokeAndWait(() -> {
    			PuzzleGUI puzzle = new PuzzleGUI();
    			puzzle.setVisible(true);
    		});
    	} catch (InvocationTargetException | InterruptedException e) {
    		e.printStackTrace();
    	}
    	
	PuzzleGUI puzzle = new PuzzleGUI();
	puzzle.setVisible(true);
    }
}
