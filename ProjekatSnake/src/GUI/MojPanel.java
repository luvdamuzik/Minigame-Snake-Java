/**
 * Package GUI se odnosi na igricu Snake koja je bila tema 
 * za projekat iz predmeta Razvoj Softvera u 2021/22 akademskoj godini
 */
package GUI;

/**
 * Ovo su importovane biblioteke za ovaj Tip
 */
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import logika.Snake;

/**
 * Klasa za panelu
 * @author Rijad Mutapcic
 * Projekat u akademskoj 2021/22
 */

public class MojPanel extends JPanel{
	/**
	 * Ovo je instanca igrice
	 */
	Snake snake;
	/**
	 * Ovo je panela koja sadrzi stanja tabele igrice
	 */
	JPanel prikazTabele;
	/**
	 * ovo je matrica dugmadi koje su unutar panela
	 */
	JButton[][] tabelaDugmadi;
	
	/**
	 * Konstruktor za polje igranja kada se igra preko GUI-a
	 * Nakon sve inicijalizacije ubacimo dimX*dimY dugmadi sto ce nam sluzit da razlikujemo objekte
	 * obojimo dugmad u skladu sa kojim je objektom:
	 * zelena:zmija
	 * crvena:jabuka
	 * bijela:prepreka
	 * crno:pozadina
	 * Takoder za svako dugme dodamo mu KeyListener.
	 * Na kraju imamo timer koji ce otkucati run(koji je definisan kasnije) svakih 200 milisekundi 
	 * i imamo uslov zaustavljanja timera kada se zavrsi igra
	 * @param dimX dimenzije polja igranja po x
	 * @param dimY dimenzije polja igranja po y
	 */
	public MojPanel(int dimX,int dimY) {
		super();
		snake = new Snake(dimX,dimY);
		//snake.vratiTrenutnoStanje();
		setLayout(new BorderLayout());
		prikazTabele = new JPanel();
		add(prikazTabele,BorderLayout.CENTER);
		prikazTabele.setLayout(new GridLayout(snake.getTabelaStanja().length,snake.getTabelaStanja()[0].length));
		tabelaDugmadi = new JButton[snake.getDimenzijaX()][snake.getDimenzijaY()];
		for(int i = 0;i<tabelaDugmadi.length;i++) {
			for(int j = 0;j<tabelaDugmadi[i].length;j++) {
				tabelaDugmadi[i][j] = new JButton();
				prikazTabele.add(tabelaDugmadi[i][j]);
				tabelaDugmadi[i][j].setBackground(getBoja(snake.getTabelaStanja()[i][j]));
				tabelaDugmadi[i][j].addKeyListener(new MojKeyListener());
			}
		}
		MojTimerTask mtt = new MojTimerTask();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(mtt,0,200);
		if(snake.krajIgre()) {
			mtt.cancel();
		}
	}

	/**
	 * Funkcija koja ce string zadani pretvorit u boju koja odgovara tom stringu
	 * zelena:zmija
	 * crvena:jabuka
	 * bijela:prepreka
	 * crno:pozadina
	 * @param boja je string koji nam kaze kojeg tip objekta koja se treba obojit
	 * @return vraca odgovarajucu boju
	 */
	private Color getBoja(String boja) {
		if(boja == "S") {
			return Color.GREEN;
		}
		if(boja == ".") {
			return Color.BLACK;
		}
		if(boja == "|") {
			return Color.WHITE;
		}
		if(boja == "X") {
			return Color.RED;
		}
		return Color.BLUE;
	}
	
	/**
	 * Funkcija koja sluzi za osvjezivanje tabela stanja
	 */
	public void osvjeziStanjeTabele() {
		for(int i = 0;i<tabelaDugmadi.length;i++) {
			for(int j = 0;j<tabelaDugmadi[i].length;j++) {
				tabelaDugmadi[i][j].setBackground(getBoja(snake.getTabelaStanja()[i][j]));
			}
		}
	}
	
	/**
	 * Klasa za kretanje zmije pomocu strelica na tastaturi
	 * @author Rijad Mutapcic
	 * Projekat u akademskoj 2021/22
	 */
	class MojKeyListener implements KeyListener{

		/**
		 * Nista ne radi
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Funkcija koja kada je pritisnuto: lijeva strelica(kod:37) zmija skrene u svoje lijevo
		 * 									 desna strelica(kod:39) zmija skrene u svoje desno
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			//lijevo 37
			if(e.getExtendedKeyCode() == 37) {
				snake.odradiPotez(1);
			}
			//desno 39
			if(e.getExtendedKeyCode() == 39) {
				snake.odradiPotez(2);
			}
			osvjeziStanjeTabele();
			
		}

		/**
		 * Nista ne radi
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * Klasa za osvjezavanje polje igre
	 * @author Rijad Mutapcic
	 * Projekat u akademskoj 2021/22
	 */
	class MojTimerTask extends TimerTask{

		/**
		 * Ovo je funkcija run koja se izvrasava svakih 200 milisekundi(gore definisano)
		 */
		@Override
		public void run() {
			snake.odradiPotez(0);
			if(!snake.krajIgre()) {
				osvjeziStanjeTabele();
			}else {
				this.cancel();
				
			}
		}
		
	}
}
