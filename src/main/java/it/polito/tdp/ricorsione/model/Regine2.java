package it.polito.tdp.ricorsione.model;

import java.util.*;

public class Regine2 {
	List<Integer> soluzione; //la ricorsione finisce appena trovo la prima soluzione. Nella classe Regine invece cerco tutttele possibili soluzioni
	
	public List<Integer> cercaRegine(int N) { //N è la dimensione dela scacchiera
		this.soluzione= null;
		List<Integer> parziale = new ArrayList<Integer>(); 
		regine_ricorsiva(parziale, 0, N);	
		return this.soluzione;
	}
	
	
	private boolean regine_ricorsiva(List<Integer> parziale, int livello, int N) {
		if(livello==N){//caso terminale ovvero livello=N 
//			System.out.println(parziale);
			this.soluzione=new ArrayList<Integer>(parziale);
			return false;
		}else {//caso normale: ho già parziale[0] fino a parziale[livello-1] già decise
			   //devo decidere paziale[livello] tra tutti i valori possibili da 0 a N-1 (colonne) purchè compatibili
			for(int col=0; col<N; col++) {
				//se la colonna è compatibile con la soluzione parziale  allora faccio ricorsone: parziale.add(colonna)   regine_ricorsiva(parziale , livello+1, N); 
				if(compatibile(livello, col, parziale)) {
					parziale.add(col);
					boolean continua= regine_ricorsiva (parziale, livello+1, N);
					if(!continua)
						return false;
					parziale.remove(parziale.size()-1);// questa operazione si chiama backtracking 
				}
			}
			return true;
		}
	}
	
	
	private boolean compatibile(int livello,Integer col, List<Integer> parziale) {
		if(parziale.indexOf(col)!=-1)
			return false;
		
		for(int riga=0; riga< parziale.size(); riga++) {
			//regina alle coordinate (riga, parziale.get(riga))
			//la devo confrontare con (R,C)=(livello,col)
			if(riga+parziale.get(riga) == livello+col)
				return false;
			if(riga-parziale.get(riga) == livello-col)
				return false;
		}
		
		return true;
	}
}
