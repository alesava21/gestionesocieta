package it.prova.gestionesocieta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionesocieta.service.BatteriaDiTestService;

@SpringBootApplication
public class GestionesocietaApplication implements CommandLineRunner{
	
	@Autowired
	private BatteriaDiTestService batteriaDiTestService;

	public static void main(String[] args) {
		SpringApplication.run(GestionesocietaApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("************ INIZIO BATTERIA DI TEST ************");
		batteriaDiTestService.testInserisciNuovaSocieta();
		batteriaDiTestService.testFindByExample();
		batteriaDiTestService.testRimozioneException();
		batteriaDiTestService.testInserimentoDipendeteConSocieta();
		batteriaDiTestService.testModificaDipendente();
		batteriaDiTestService.testRal();
		System.out.println("************ fine BATTERIA DI TEST ************");
		
	}

}
