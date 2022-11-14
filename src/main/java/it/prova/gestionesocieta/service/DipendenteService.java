package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Dipendente;


public interface DipendenteService {
	
	public List<Dipendente> listAllDipendenti();

	public Dipendente caricaSingoloAbitante(Long id);

	public void aggiorna(Dipendente dipendentiInstance);

	public void inserisciNuovo(Dipendente dipendentiInstance);

	public void rimuovi(Dipendente dipendentiInstance);

}
