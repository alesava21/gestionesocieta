package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private DipendenteService dipendenteService;
	@Autowired
	private SocietaService societaService;

	public void testInserisciNuovaSocieta() throws Exception {

		Societa nuovaSocieta = new Societa("Sava's Airlines", "via mosca 52",
				new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2002"));

		if (nuovaSocieta.getId() != null) {
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		}

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");
		}
		System.out.println("testInserisciNuovoMunicipio........OK");

	}

	public void testFindByExample() throws Exception {
		Societa nuovaSocieta = new Societa("Find Airlines", "via mosca 52",
				new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2005"));

		if (nuovaSocieta.getId() != null) {
			throw new RuntimeException("testFindByExample...failed: transient object con id valorizzato");
		}

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
			throw new RuntimeException("testFindByExample...failed: inserimento fallito");
		}
		
		List<Societa> listaSocieta= societaService.findByExample(nuovaSocieta);
		
		if (listaSocieta.size() != 1) {
			throw new RuntimeException("testFindByExample...failed: inserimento fallito");
		}
		System.out.println("testFindByExample........OK");


	}

}
