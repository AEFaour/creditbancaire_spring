package com.aston.java.spring.orm.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aston.java.spring.orm.AppConfig;
import com.aston.java.spring.orm.domaines.Client;
import com.aston.java.spring.orm.domaines.Compte;
import com.aston.java.spring.orm.repositories.ICompteRepository;
import com.aston.java.spring.orm.repositories.impl.CompteRepository;
import com.aston.java.spring.orm.services.ICompteService;
import com.aston.java.spring.orm.services.impl.CompteService;

public class UniverselTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {

			Client joachimSenior = new Client();
			Client joachimJenior = new Client();
			joachimSenior.setPrenom("Joachim Senior");
			joachimSenior.setNom("Zadi Java");
			joachimSenior.setEmail("java@zadi.joa");
			joachimSenior.setMdp("123");
			joachimSenior.setDdn(LocalDate.of(1967, 9, 9));
			joachimSenior.setAge(54);

			joachimJenior.setPrenom("Joachim Jenior");
			joachimJenior.setNom("Zadi C++");
			joachimJenior.setEmail("cplusplus@zadi.joa");
			joachimJenior.setMdp("1234");
			joachimJenior.setDdn(LocalDate.of(1997, 11, 8));
			joachimJenior.setAge(24);

			Compte compteJachimSenior = new Compte();
			Compte compteJachimJenior = new Compte();

			compteJachimSenior.setClient(joachimSenior);
			compteJachimSenior.setNumero("111");
			compteJachimSenior.setSolde(BigDecimal.valueOf(1500.0));
			compteJachimSenior.setValidation(true);

			compteJachimJenior.setClient(joachimJenior);
			compteJachimJenior.setNumero("112");
			compteJachimJenior.setSolde(BigDecimal.valueOf(1700.0));
			compteJachimJenior.setValidation(true);

			ICompteRepository repository = context.getBean(CompteRepository.class);
			repository.insert(compteJachimJenior);
			repository.insert(compteJachimSenior);

			ICompteService service = context.getBean(CompteService.class);

			service.crediter(1, 150.);
			service.crediter(2, 250.);
			service.transfert(1, 2, 50.);
		}

	}

}
