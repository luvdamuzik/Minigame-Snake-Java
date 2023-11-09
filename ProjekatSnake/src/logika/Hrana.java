/**
 * Package logika se odnosi na igricu Snake koja je bila tema 
 * za projekat iz predmeta Razvoj Softvera u 2021/22 akademskoj godini
 */
package logika;

/**
 * Ovo su importovane biblioteke za ovaj Tip
 */
import java.util.Random;

/**
 * Klasa za jabuku
 * @author Rijad Mutapcic
 * Projekat u akademskoj 2021/22
 */
public class Hrana {

	int X,Y;
	int powerUp;
	
	/**
	 * Ovo je konstruktor koji nasumicno generise x i y koordinate za jabuku.
	 * Takoder generise power-up(sto je nasumicni broj izmedju 0 i 3) koji kada
	 * pojedemo jabuku uradi jedno od 4 stvari:
	 * da zmiju poveca više od jednog clanka
	 * da smanji zmiju
	 * da zamijeni lijevo – desno kontrole ili
	 * nista specijalno
	 * @param dimenzijaX je dimenzija polja igrice po X
	 * @param dimenzijaY je dimenzija polja igrice po Y
	 */
	public Hrana(int dimenzijaX,int dimenzijaY) {
		this.X = (int)(Math.random() * dimenzijaX);
		this.Y = (int)(Math.random() * dimenzijaY);

		this.powerUp = (int)(Math.random() * 4);
	}
	/**
	 * Ovo funkcija generise nove koordinate za jabuku kada je pojedena prethodna
	 * @param dimenzijaX je dimenzija polja igrice po X
	 * @param dimenzijaY je dimenzija polja igrice po Y
	 */
	public void GenerisiNovi(int dimenzijaX,int dimenzijaY) {
		this.X = (int)(Math.random() * dimenzijaX);
		this.Y = (int)(Math.random() * dimenzijaY);

		this.powerUp = (int)(Math.random() * 4);
	}
}
