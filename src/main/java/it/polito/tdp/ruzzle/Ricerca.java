package it.polito.tdp.ruzzle;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ruzzle.model.Board;
import it.polito.tdp.ruzzle.model.Pos;

public class Ricerca {
	// Classe per metodi ricorsivi
	
	public List<Pos>trovaParola(String parola, Board board) {
		for(Pos p:board.getPositions())
			if(board.getCellValueProperty(p).get().charAt(0)==parola.charAt(0)) {
				//Posso fare partire la ricorsione
				List<Pos> parziale=new ArrayList<>();
				parziale.add(p);
				if(cerca(parola, parziale, 1, board));
					return parziale;
			}
		
		return null;	
	}

	private boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
		
		//caso terminale
		if(livello==parola.length())
			return true;
		Pos ultima=percorso.get(percorso.size()-1);
		List<Pos> adiacenti=board.getAdjacencies(ultima);
		for(Pos a: adiacenti) {
			if(!percorso.contains(a) && 
			board.getCellValueProperty(a).get().charAt(0)==parola.charAt(livello)) {
				
				//Posso continuare il mio percorso facendo andare avanti la ricorsione
				percorso.add(a);
				if(cerca(parola,percorso,livello+1,board)) //Uscita rapida dalla ricorsione
					return true;
				percorso.remove(percorso.size()-1);
				
			}
		}
		return false;
	}

}
