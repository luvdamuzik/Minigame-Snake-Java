/**
 * Package logika se odnosi na igricu Snake koja je bila tema 
 * za projekat iz predmeta Razvoj Softvera u 2021/22 akademskoj godini
 */
package logika;

/**
 * Ovo su importovane biblioteke za ovaj Tip
 */
import java.util.Iterator;
import java.util.Vector;

/**
 * Klasa za zmiju
 * @author Rijad Mutapcic
 * Projekat u akademskoj 2021/22
 */

public class Snake {

	SnakeGlavni glavni;
	Hrana hrana;
	int dimenzijaX,dimenzijaY;
	Vector<Integer> koordinateX,koordinateY;
	Vector<Integer> preprekeX,preprekeY;
	String tabelaStanja[][];
	private Boolean kraj;
	int bodovi;
	
	/**
	 * Inicijalizacija pocinje tako sto zmiju postavimo u prvom redu sa smijerom prema gore.
	 * Sacuvamo koordinate svakog dio tijela zmije.
	 * Nasumicno generisemo 6 prepreka u polje
	 * i jos inicijaliziramo tabelu stanja sve postavimo na "."
	 * @param dimenzijaX dimenzije polja igranja po x
	 * @param dimenzijaY dimenzije polja igranja po y
	 */
	public Snake(int dimenzijaX,int dimenzijaY) {
		super();
		this.kraj = false;
		this.bodovi = 0;
		this.dimenzijaX = (dimenzijaX < 10 ? 10 : (dimenzijaX > 25 ? 25 : dimenzijaX));
		this.dimenzijaY = (dimenzijaY < 10 ? 10 : (dimenzijaY > 25 ? 25 : dimenzijaY));
		glavni = new SnakeGlavni();
		hrana = new Hrana(dimenzijaX,dimenzijaY);
		koordinateX = new Vector<Integer>();
		koordinateY = new Vector<Integer>();
		//postavimo pocetno (glava na 0,0)
		koordinateX.add(0);
		koordinateY.add(0);
		
		koordinateX.add(1);
		koordinateY.add(0);
		
		koordinateX.add(2);
		koordinateY.add(0);
		
		koordinateX.add(3);
		koordinateY.add(0);
		
		preprekeX = new Vector<Integer>();
		preprekeY = new Vector<Integer>();
		
		for(int i = 0;i<6;i++) {
			int randX = (int)(Math.random() * dimenzijaX);
			int randY = (int)(Math.random() * dimenzijaY);
			while(randY == 1) {
				randX = (int)(Math.random() * dimenzijaX);
				randY = (int)(Math.random() * dimenzijaY);
			}
			preprekeX.add(randX);
			preprekeY.add(randY);
		}
		
		
		tabelaStanja = new String[dimenzijaX][dimenzijaY];
		
		for(int i = 0;i<dimenzijaX;i++) {
			for(int j = 0;j<dimenzijaY;j++) {
				tabelaStanja[i][j] = ".";
			}
		}
		
	}
	
	/**
	 * Konstruktor koji se moze koristit u slucaju da korisnik zeli defualtne dimenzije polja
	 */
	public Snake() {
		this(10,25);
	}
	
	/**
	 * getter za dimenziju po X
	 * @return dimenziju po X
	 */
	public int getDimenzijaX() {
		return dimenzijaX;
	}

	/**
	 * setter za dimenziju po X
	 * @param dimenzijaX je dimenzija polja za igranje po x
	 */
	public void setDimenzijaX(int dimenzijaX) {
		this.dimenzijaX = dimenzijaX;
	}
	/**
	 * getter za dimenziju po Y
	 * @return dimenziju po Y
	 */
	public int getDimenzijaY() {
		return dimenzijaY;
	}
	/**
	 * setter za dimenziju po Y
	 * @param dimenzijaY je dimenzija polja za igranje po y
	 */
	public void setDimenzijaY(int dimenzijaY) {
		this.dimenzijaY = dimenzijaY;
	}

	/**
	 * getter za trenutno stanje tabele stanja
	 * @return trenutno stanje tabele stanja
	 */
	public String[][] getTabelaStanja() {
		return vratiTrenutnoStanje();
	}

	/**
	 * setter za tabelu stanja
	 * @param tabelaStanja je tabela stanja igre
	 */
	public void setTabelaStanja(String[][] tabelaStanja) {
		this.tabelaStanja = tabelaStanja;
	}
	
	/**
	 * Funkcija koja vraca trenutno stanje polja za igranje
	 * Napravi se pomocno stanje koja se inicijalizira na tabelu stanja
	 * koristeci iterator prolazimo kroz vekotre za zmiju i prepreke i u polje stavljamo
	 * S - za zmiju
	 * | - za prepreku
	 * Nakon tog treba da se generise jabuka ako se slucajno generisala na polje koja nije "."
	 * onda samo generisemo nove koordinate
	 * @return treutno stanje
	 */
	public String[][] vratiTrenutnoStanje(){
		String[][] stanje = new String[dimenzijaX][dimenzijaY];
		for(int i = 0;i<dimenzijaX;i++) {
			for(int j = 0;j<dimenzijaY;j++) {
				stanje[i][j] = this.tabelaStanja[i][j];
			}
		}

		Iterator<Integer> itrX=koordinateX.iterator();
		Iterator<Integer> itrY=koordinateY.iterator();
		
		while(itrX.hasNext() && itrY.hasNext()) {
			int pomocniX = itrX.next();
			int pomocniY = itrY.next();
			stanje[pomocniX][pomocniY] = "S";
		}
			
		Iterator<Integer> itrPreprekeX = preprekeX.iterator();
		Iterator<Integer> itrPreprekeY = preprekeY.iterator();
		
		while(itrPreprekeX.hasNext() && itrPreprekeY.hasNext()) {
			int pomocniX = itrPreprekeX.next();
			int pomocniY = itrPreprekeY.next();
			stanje[pomocniX][pomocniY] = "|";
		}
		
		Boolean imaLiHrane = true;
		while(imaLiHrane) {
			if(stanje[hrana.X][hrana.Y] == ".") {
				stanje[hrana.X][hrana.Y] = "X";
				imaLiHrane = false;
			}else {
				hrana.GenerisiNovi(dimenzijaX, dimenzijaY);
			}
		}
		return stanje;
	}
	
	/**
	 * Funckija koja izvrsava poteze postavimo pomocne koordinate x i y na glavu zmije
	 * pozovemo funkciju za pomjeranje smjera(promjenimo smijer u skladu sa potezom ukoliko je to potrebno)
	 * onda nad pomocnim koordinatima izvrsavamo potencijnalni pomak
	 * za smijer: 1 - Y+1
	 * 			  2 - X-1
	 *   		  3 - Y-1
	 *   		  4 - X+1
	 *  Zatim provjeravamo da li je glava zmije uradila u prepreku ili u svoje tijelo
	 *  ako jest dozvoljeno cemo postavit na false
	 *  Sljedece provjeravamo da li zmija pojela jabuku ako jest povecamo zmiju za jedno polje i povecamo broj bodova
	 *  ako je dozvoljeno ostalo true dakle mozemo se pomjerit na to polje onda 
	 *  dodamo na pocetak vektora te pomocne koordinate i oduzmemo zadnje koordinate u vektorima
	 *  ukoliko je dozvoljeno false onda kraj postavljamo na true i zaustavljamo igru i ispisujemo broj bodova u konzoli
	 * @param potez:
	 * 0 - nastavi pravo
	 * 1 - skreni lijevo
	 * 2 - skreni desno
	 * @return vracamo da li je dovoljeno da se tu pomaknemo
	 */

	public Boolean odradiPotez(int potez) {
		int pomocniX = koordinateX.firstElement();
		int pomocniY = koordinateY.firstElement();
		
		glavni.Pomjeri(potez);
		
		//uradimo potencijonalni pomak koji cemo kasnije provjerit
		if(glavni.getSmijer() == 1) {
			pomocniY += 1;
			if(pomocniY >= dimenzijaY) {
				pomocniY = 0;
			}
		}else if(glavni.getSmijer() == 2) {
			pomocniX -= 1;
			if(pomocniX < 0) {
				pomocniX = dimenzijaX-1;
			}
		}else if(glavni.getSmijer() == 3) {
			pomocniY -= 1;
			if(pomocniY < 0) {
				pomocniY = dimenzijaY - 1;
			}
		}else {
			pomocniX += 1;
			if(pomocniX >= dimenzijaX) {
				pomocniX = 0;
			}
		}
		
		Boolean dozvoljeno = true;
		
		Iterator<Integer> itrX=koordinateX.iterator();
		Iterator<Integer> itrY=koordinateY.iterator();
		

		while(itrX.hasNext() && itrY.hasNext()) {
			int TijeloX = itrX.next();
			int TijeloY = itrY.next();
			if(TijeloX == pomocniX && TijeloY == pomocniY) {
				dozvoljeno = false;
			}
		}
		
		Iterator<Integer> itrPreprekeX = preprekeX.iterator();
		Iterator<Integer> itrPreprekeY = preprekeY.iterator();
		
		while(itrPreprekeX.hasNext() && itrPreprekeY.hasNext()) {
			int preprekaX = itrPreprekeX.next();
			int preprekaY = itrPreprekeY.next();
			if(preprekaX == pomocniX && preprekaY==pomocniY) {
				dozvoljeno = false;
			}
		}
		
		//povjeroavamo da li je hrana
			if(pomocniX == hrana.X && pomocniY == hrana.Y) {
				koordinateX.add(0,pomocniX);
				koordinateY.add(0,pomocniY);
				bodovi++;
			}
		
		if(dozvoljeno) {
			koordinateX.add(0, pomocniX);
			koordinateY.add(0, pomocniY);
			koordinateX.removeElementAt(koordinateX.size()-1);
			koordinateY.removeElementAt(koordinateY.size()-1);
		}else {
			kraj = true;
			System.out.println("Broj bodova: " + bodovi);
		}
		return dozvoljeno;
	}

	/**
	 * getter za kraj
	 * @return bool koji govori da li je gotova igra ili ne
	 */
	public boolean krajIgre() {
		return kraj;
	}
}
