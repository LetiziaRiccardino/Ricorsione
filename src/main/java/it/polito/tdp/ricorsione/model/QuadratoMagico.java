package it.polito.tdp.ricorsione.model;

import java.util.*;

public class QuadratoMagico {
	
	private int sommaCorretta;
	private int N;
	private int N2;
	
	public void risolviQuadrato(int N) {
		
		this.N=N;
		this.N2=N*N;
		this.sommaCorretta=N*(N2+1)/2;
		
		List<Integer> parziale= new ArrayList<Integer>();
		
		cerca(parziale,0);
		
	}
	
	
	
	private void cerca(List<Integer> parziale, int livello){//int N
		
		if(livello==this.N2 ) { //N*N lo sostituisco con N2
			//caso terminale, ho già messo N^2 numeri
			if(controllaSomme(parziale))
				System.out.println(parziale);
		}else {
			if(livello%this.N==0 && livello!=0) {
				if(controllaRiga(parziale, livello/this.N-1))
					return;
			}
			//ogni volta aggiungo un numero tra quelli non ancora usati
			for(int i=1; i<this.N2; i++) {
				if(!parziale.contains(i)) { //allora provo ad aggiungere 'i' alla cella 'livello'
					parziale.add(i);
				cerca(parziale, livello+1);
				parziale.remove(parziale.size()-1);
				}
			}
		}
			
		
	}

	private boolean controllaRiga(List<Integer> parziale, int riga) {
		int s=0;
		for(int col=0;col<this.N; col++)
			s=s+parziale.get(riga*this.N+col);
		if(s!=this.sommaCorretta)
			return false;
		
		return false;
	}



	private boolean controllaSomme(List<Integer> parziale) {
		//controlla somma per riga
		for(int riga=0; riga<this.N; riga ++) {
			int s=0;
			for(int col=0; col<this.N; col++)
				s=s+parziale.get(riga*this.N+col);
			if(s!=this.sommaCorretta)//se ne trovo una sbagliata esco
				return false;
		}
		//controlla somma per colonna 
		for(int col=0; col<this.N; col ++) {
			int s=0;
			for(int riga=0; riga<this.N; riga++)
				s=s+parziale.get(riga*this.N+riga);
			if(s!=this.sommaCorretta)
				return false;
		}
		//controllo somma per diagonali
		//diagonale 1
		int s=0;
		for(int riga=0; riga<this.N; riga++)
			s=s+parziale.get(riga*this.N+riga);
		if(s!=this.sommaCorretta)
			return false;
		//diagonale 2
		s=0;
		for(int riga=0; riga<this.N; riga++)
			s=s+parziale.get(riga*this.N+(this.N-1-riga));
		if(s!=this.sommaCorretta)
			return false;
		
		
		return true;
	}

}
