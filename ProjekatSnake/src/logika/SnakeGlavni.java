/**
 * Package logika se odnosi na igricu Snake koja je bila tema 
 * za projekat iz predmeta Razvoj Softvera u 2021/22 akademskoj godini
 */
package logika;

/**
 * Klasa za tijelo zmije
 * @author Rijad Mutapcic
 * Projekat u akademskoj 2021/22
 */

public class SnakeGlavni {

	private int duzina;

	private int smijer;
	
	/**
	 * Konstruktor koji postavlja parametre smijer i duzina 
	 * na svoje defaultne vrijednosti(smijer = 2 i duzina = 4)
	 */
	public SnakeGlavni() {
		smijer = 2;
		duzina = 4;
	}
	/**
	 * getter za duzinu
	 * @return duzina
	 */
	public int getDuzina() {
		return duzina;
	}

	/**
	 * Ovo je setter za duzinu
	 * @param duzina je koja je duzina zmije
	 * 
	 */
	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}
	
	/**
	 * getter za smijer
	 * @return smijer
	 */
	public int getSmijer() {
		return smijer;
	}

	/**
	 * Ovo je setter za smijer
	 * @param smijer:
	 * ako je 1 onda je glava zmije okrenuta na desno
	 * ako je 2 onda je glava zmije okrenuta prema gore
	 * ako je 3 onda je glava zmije okrenuta na lijevo
	 * ako je 4 onda je glava zmije okrenuta prema dolje
	 */
	public void setSmijer(int smijer) {
		this.smijer = smijer;
	}

	/**
	 * Ova funckija mjenja smijer zmije nakon uradjenog poteza
	 * @param direkcija:
	 * 0 - ne mjenja se smijer
	 * 1 - smijer se pomjeri za jedno u pozitivnom smijeru
	 * 2 - smijer se pomejeri za jedno u negativnom smijeru 
	 */
	public void Pomjeri(int direkcija) {
		int smijer = this.smijer;
		if(direkcija == 1) {
			if(smijer == 4) {
				smijer = 1;
			}else {
				smijer = this.smijer + 1;
			}
		}else if(direkcija == 2) {
			if(smijer == 1) {
				smijer = 4;
			}else {
				smijer = this.smijer - 1;
			}
		}
		this.smijer = smijer;
	}

	
}
