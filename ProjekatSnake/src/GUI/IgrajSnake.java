/**
 * Package GUI se odnosi na igricu Snake koja je bila tema 
 * za projekat iz predmeta Razvoj Softvera u 2021/22 akademskoj godini
 */
package GUI;

/**
 * Ovo su importovane biblioteke za ovaj Tip
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Klasa za igranje igirce u GUI-u
 * @author Rijad Mutapcic
 * Projekat u akademskoj 2021/22
 */

public class IgrajSnake {

	/**
	 * Main funkcija koja se koristi da bismo pokrenuli igricu preko GUI-a
	 * @param args ovo je default
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame("Nasa snake igrica");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MojPanel mojpanel = new MojPanel(10,20);
		jf.setContentPane(mojpanel);
		jf.setVisible(true);
		jf.setSize(800,800);

	}

}
