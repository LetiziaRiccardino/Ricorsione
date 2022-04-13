package it.polito.tdp.ricorsione.model;

import java.util.*;

public class Regine {
	private List<List<Integer>> tutte;
	
	public List<List<Integer>> cercaRegine(int N) { //N è la dimensione dela scacchiera
		this.tutte= new ArrayList<List<Integer>>();
		List<Integer> parziale = new ArrayList<Integer>(); 
		regine_ricorsiva(parziale, 0, N);	
		return this.tutte;
	}
	
	
	private void regine_ricorsiva(List<Integer> parziale, int livello, int N) {
		if(livello==N){//caso terminale ovvero livello=N 
			System.out.println(parziale);
			this.tutte.add(new ArrayList<Integer>(parziale));
		}else {//caso normale: ho già parziale[0] fino a parziale[livello-1] già decise
			   //devo decidere paziale[livello] tra tutti i valori possibili da 0 a N-1 (colonne) purchè compatibili
			for(int col=0; col<N; col++) {
				//se la colonna è compatibile con la soluzione parziale  allora faccio ricorsone: parziale.add(colonna)   regine_ricorsiva(parziale , livello+1, N); 
				if(compatibile(livello, col, parziale)) {
					parziale.add(col);
					regine_ricorsiva(parziale , livello+1, N);
					parziale.remove(parziale.size()-1);// questa operazione si chiama backtracking 
				}
			}
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