/**
 * Package konzola se odnosi na igricu Snake koja je bila tema 
 * za projekat iz predmeta Razvoj Softvera u 2021/22 akademskoj godini
 */
package konzola;

/**
 * Ovo su importovane biblioteke za ovaj Tip
 */
import java.util.Scanner;

import logika.Snake;

/**
 * Klasa za igranje igrice u konzoli
 * @author Rijad Mutapcic
 * Projekat u akademskoj 2021/22
 */

public class IgrajSnake {

	/**
	 * Ovo je main funckija za igranje igrice Snake u konzoli
	 * priprema tabelu stanja i ispisuje u konzoli trenutno stanje
	 * @param args ovo je default
	 */
	public static void main(String[] args) {
		Snake snake = new Snake();
		System.out.println("Snake potezi su: 0-nastavi ravno,1-lijevo,2-desno");
		System.out.println(pripremiTabeluStanja(snake.vratiTrenutnoStanje()));
		while(!snake.krajIgre()) {
			String potezi = ucitajPoteze();
			odigrajPotez(potezi,snake);
			System.out.println(pripremiTabeluStanja(snake.vratiTrenutnoStanje()));
		}
		
	}

	/**
	 * Funkcija koja odigra potez. Cita sta je korisnik unio u konzolu i izvrsava svaki potez redom
	 * @param potezi string gdje svaki karakter stringa je jedan potez koji se treba izvrsit
	 * @param snake je instansa igre u kojem cemo uraditi ove poteze
	 */
	private static void odigrajPotez(String potezi,Snake snake) {
		for(int i=0;i<potezi.length();i++) {
			if(potezi.charAt(i) == '0') {
				snake.odradiPotez(0);
			}
			if(potezi.charAt(i)=='1') {
				snake.odradiPotez(1);
			}
			if(potezi.charAt(i)=='2') {
				snake.odradiPotez(2);
			}
		}
		
	}

	/**
	 * Funkcija koja sluzi za unos poteza od strane korisnika
	 * @return string koji sadrzi sve poteze koji korisnik zeli uradit
	 */
	private static String ucitajPoteze() {
		Scanner sc = new Scanner(System.in);
		String returnString = "";
		try {
			returnString = sc.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

	/**
	 * Funckija koja se koristi da bi u konzoli lijepse izgledalo polje igranja
	 * @param vratiTrenutnoStanje je trenutno stanje igre
	 * @return vraca uljepsanu verziju pogodna za konzolu
	 */
	private static String pripremiTabeluStanja(String[][] vratiTrenutnoStanje) {
		String stanje = "";
		for(int i=0;i < vratiTrenutnoStanje.length;i++) {
			for(int j=0;j < vratiTrenutnoStanje[i].length;j++) {
				stanje += (vratiTrenutnoStanje[i][j] == "." ? "." : vratiTrenutnoStanje[i][j])+ " ";
			}
			stanje += "\n";
		}
		return stanje;
	}

}
