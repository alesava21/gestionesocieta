package it.prova.gestionesocieta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService{
	@Autowired
	private DipendenteRepository dipendenteRepository;

	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Dipendente caricaSingoloAbitante(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Dipendente dipendentiInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.save(dipendentiInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Dipendente dipendentiInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.save(dipendentiInstance);
		
	}

	@Transactional
	public void rimuovi(Dipendente dipendentiInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.delete(dipendentiInstance);
	}

}
