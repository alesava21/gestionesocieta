package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Dipendente;
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
		societaService.rimuovi(nuovaSocieta);
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
		societaService.rimuovi(nuovaSocieta);
		System.out.println("testFindByExample........OK");
	}

	public void testRimozioneException() throws Exception {
		Societa nuovaSocieta = new Societa("Diego's", "via del corso 23",
				new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2005"));

		if (nuovaSocieta.getId() != null) {
			throw new RuntimeException("testFindByExample...failed: transient object con id valorizzato");
		}

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
			throw new RuntimeException("testFindByExample...failed: inserimento fallito");
		}

		Dipendente nuovoDipendente = new Dipendente("alessandro", "sava",
				new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2010"), 30000, nuovaSocieta);
		
		societaService.rimuovi(nuovaSocieta);
		dipendenteService.rimuovi(nuovoDipendente);

	}

	public void testInserimentoDipendeteConSocieta() throws Exception {
		Societa nuovaSocieta = new Societa("Diego's", "via del corso 23",
				new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2005"));

		if (nuovaSocieta.getId() != null) {
			throw new RuntimeException(
					"testInserimentoDipendeteConSocieta...failed: transient object con id valorizzato");
		}

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
			throw new RuntimeException("testInserimentoDipendeteConSocieta...failed: inserimento fallito");
		}

		Dipendente nuovoDipendente = new Dipendente("alessandro", "sava",
				new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2010"), 30000, nuovaSocieta);

		if (nuovoDipendente.getId() != null) {
			throw new RuntimeException(
					"testInserimentoDipendeteConSocieta...failed: transient object con id valorizzato");
		}
		dipendenteService.inserisciNuovo(nuovoDipendente);
		nuovoDipendente.setSocieta(nuovaSocieta);

		if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1) {
			throw new RuntimeException("testInserimentoDipendeteConSocieta...failed: inserimento fallito");
		}
		
		dipendenteService.rimuovi(nuovoDipendente);
		societaService.rimuovi(nuovaSocieta);

		System.out.println("testInserimentoDipendeteConSocieta........OK");
	}

	public void testModificaDipendente() throws Exception {
		
			Societa nuovaSocieta = new Societa("Diego's", "via del corso 23",
					new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2005"));

			if (nuovaSocieta.getId() != null) {
				throw new RuntimeException(
						"testInserimentoDipendeteConSocieta...failed: transient object con id valorizzato");
			}

			societaService.inserisciNuovo(nuovaSocieta);
			if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
				throw new RuntimeException("testInserimentoDipendeteConSocieta...failed: inserimento fallito");
			}
		
		Dipendente nuovoDipendente = new Dipendente("alessandro", "sava",
				new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2010"), 30000,nuovaSocieta);

		if (nuovoDipendente.getId() != null) {
			throw new RuntimeException(
					"testModificaDipendente...failed: transient object con id valorizzato");
		}
		dipendenteService.inserisciNuovo(nuovoDipendente);

		if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1) {
			throw new RuntimeException("testModificaDipendente...failed: inserimento fallito");
		}
		
		List<Dipendente> listaDipendenti = dipendenteService.listAllDipendenti();
		
		Dipendente dipendenteDaAggiornare = listaDipendenti.get(0);
		dipendenteDaAggiornare.setNome("diego");
		
		dipendenteService.aggiorna(dipendenteDaAggiornare);
		
		System.out.println("testModificaDipendente........OK");
	}

}
