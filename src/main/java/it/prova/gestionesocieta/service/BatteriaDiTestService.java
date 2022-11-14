package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.exception.SocietaConDipendentiAssociatiException;
import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private DipendenteService dipendenteService;
	@Autowired
	private SocietaService societaService;

	public void testInserisciNuovaSocieta() throws Exception {
		System.out.println("testInserisciNuovaSocieta INIZIO");

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
		System.out.println("testFindByExample INIZIO");

		Societa nuovaSocieta = new Societa("Find Airlines", "via mosca 52",
				new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2005"));

		if (nuovaSocieta.getId() != null) {
			throw new RuntimeException("testFindByExample...failed: transient object con id valorizzato");
		}

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
			throw new RuntimeException("testFindByExample...failed: inserimento fallito");
		}

		List<Societa> listaSocieta = societaService.findByExample(nuovaSocieta);

		if (listaSocieta.size() != 1) {
			throw new RuntimeException("testFindByExample...failed: inserimento fallito");
		}
		System.out.println("testFindByExample........OK");
	}

	public void testInserimentoDipendeteConSocieta() throws Exception {
		System.out.println("testInserimentoDipendeteConSocieta INIZIO");

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

		System.out.println("testInserimentoDipendeteConSocieta........FINEEE");
	}

	public void testModificaDipendente() throws Exception {
		System.out.println("testModificaDipendente INIZIO");

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
			throw new RuntimeException("testModificaDipendente...failed: transient object con id valorizzato");
		}
		dipendenteService.inserisciNuovo(nuovoDipendente);

		if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1) {
			throw new RuntimeException("testModificaDipendente...failed: inserimento fallito");
		}

		List<Dipendente> listaDipendenti = dipendenteService.listAllDipendenti();

		Dipendente dipendenteDaAggiornare = listaDipendenti.get(0);
		dipendenteDaAggiornare.setNome("diego");

		dipendenteService.aggiorna(dipendenteDaAggiornare);

		System.out.println("testModificaDipendente........FINEEEEE");
	}
	
	public void testRimozioneException() throws Exception {
		System.out.println("----------testRimozioneException START------------");
		
		Societa nuovaSocieta = new Societa("Eclips" , "via della bella villa", new Date());
		
		Societa nuovaSocieta2 = new Societa("Visual" , "via delle marmotte", new Date());

		societaService.inserisciNuovo(nuovaSocieta);
		societaService.inserisciNuovo(nuovaSocieta2);
		
		Dipendente dipendente1=new Dipendente("mario","verdi",new Date(),30000,nuovaSocieta);
		dipendenteService.inserisciNuovo(dipendente1);
		nuovaSocieta.getDipendenti().add(dipendente1);
		societaService.aggiorna(nuovaSocieta);
		
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1 || nuovaSocieta2.getId() == null
				|| nuovaSocieta2.getId() < 1)
			throw new RuntimeException("testRemoveConEccezioneVaInRollback...failed: inserimento fallito");

		try {
			societaService.rimuovi(nuovaSocieta);
			societaService.rimuovi(nuovaSocieta2);
			
		} catch (SocietaConDipendentiAssociatiException e) {
			e.printStackTrace();
			
			
		}

		if (nuovaSocieta == null || nuovaSocieta.getId() == null)
			throw new RuntimeException(
					"testRemoveConEccezioneVaInRollback...failed: cancellazione avvenuta senza rollback");

		System.out.println("testRemoveConEccezioneVaInRollback........OK");

	}
	
	public void testRal() {
		System.out.println("----------testRal START------------");
		Societa nuovaSocieta1 = new Societa("Sava's", "viale marianna 34", new Date());
		societaService.inserisciNuovo(nuovaSocieta1);
		Dipendente dipendente1 = new Dipendente("Bianco", "Nero", new Date(), 10000, nuovaSocieta1);
		dipendenteService.inserisciNuovo(dipendente1);
		Societa nuovaSocieta2 = new Societa("Belino", "via bianchi", new Date());
		societaService.inserisciNuovo(nuovaSocieta1);
		Dipendente dipendente2 = new Dipendente("Carlo", "verdi", new Date(), 50000, nuovaSocieta1);
		dipendenteService.inserisciNuovo(dipendente2);

		if (societaService.cercaAllDistinctByDipendenti_reditoAnnuoLordoGreaterThan(30000).size() != 1) {
			throw new RuntimeException(
					"testRal failed! Numero record inatteso.");
		}
		System.out.println("testRal PASSED!");

		
	}

}
