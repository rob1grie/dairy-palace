
import gui.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rob
 */
public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try { 
					new MainFrame();
				} catch (Exception ex) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			
		});
	}
}
