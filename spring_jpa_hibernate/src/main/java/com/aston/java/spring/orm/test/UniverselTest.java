package com.aston.java.spring.orm.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.aston.java.spring.orm.domaines.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aston.java.spring.orm.AppConfig;
import com.aston.java.spring.orm.repositories.ICompteRepository;
import com.aston.java.spring.orm.repositories.impl.CompteRepository;
import com.aston.java.spring.orm.services.ICompteService;
import com.aston.java.spring.orm.services.impl.CompteService;

public class UniverselTest {

	public static void main(String[] args) {

		try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {

			Adresse adresseJachimJenior = new Adresse();
			adresseJachimJenior.setVoie("2, Place Saint-Michel");
			adresseJachimJenior.setCodePostal("75006");
			adresseJachimJenior.setVille("Paris");
			adresseJachimJenior.setPays("France");

			Adresse adresseJachimSenior = new Adresse();
			adresseJachimSenior.setVoie("22, Place Charras");
			adresseJachimSenior.setCodePostal("92400");
			adresseJachimSenior.setVille("Courbevoie");
			adresseJachimSenior.setPays("France");

			Adresse adresseAgenceParis = new Adresse();
			adresseAgenceParis.setVoie("2, Place de la Sorbonne");
			adresseAgenceParis.setCodePostal("75005");
			adresseAgenceParis.setVille("Paris");
			adresseAgenceParis.setPays("France");

			Adresse adresseAgenceCourbevoie = new Adresse();
			adresseAgenceCourbevoie.setVoie("42, Rue Victor Hugo");
			adresseAgenceCourbevoie.setCodePostal("92400");
			adresseAgenceCourbevoie.setVille("Courbevoie");
			adresseAgenceCourbevoie.setPays("France");

			Adresse adresseSocieteGenerale = new Adresse();
			adresseSocieteGenerale.setVoie("7, Avenue Leon Blom");
			adresseSocieteGenerale.setCodePostal("69007");
			adresseSocieteGenerale.setVille("Lyon");
			adresseSocieteGenerale.setPays("France");

			Adresse adresseHSBC = new Adresse();
			adresseHSBC.setVoie("14, Down Street");
			adresseHSBC.setCodePostal("2AB");
			adresseHSBC.setVille("London");
			adresseHSBC.setPays("England");

			Client joachimSenior = new Client();
			Client joachimJenior = new Client();
			joachimSenior.setPrenom("Joachim Senior");
			joachimSenior.setNom("Zadi Java");
			joachimSenior.setEmail("java@zadi.joa");
			joachimSenior.setMdp("123");
			joachimSenior.setDdn(LocalDate.of(1967, 9, 9));
			joachimSenior.setAge(54);
			joachimSenior.setAdresse(adresseJachimSenior);

			joachimJenior.setPrenom("Joachim Jenior");
			joachimJenior.setNom("Zadi C++");
			joachimJenior.setEmail("cplusplus@zadi.joa");
			joachimJenior.setMdp("1234");
			joachimJenior.setDdn(LocalDate.of(1997, 11, 8));
			joachimJenior.setAge(24);
			joachimJenior.setAdresse(adresseJachimJenior);

			Banque societeGenerale = new Banque();
			societeGenerale.setNom("Société Générale");
			societeGenerale.setAdresse(adresseSocieteGenerale);
			societeGenerale.setCode("FR1025EUR");

			Banque HSBC = new Banque();
			HSBC.setNom("HSBC");
			HSBC.setAdresse(adresseHSBC);
			HSBC.setCode("EN2023EUR");

			Agence agenceParis = new Agence();
			agenceParis.setAdresse(adresseAgenceParis);
			agenceParis.setNom("Agence Paris Sorbonne");
			agenceParis.setCodeGuichet("11P75");
			agenceParis.setBanque(HSBC);


			Agence agenceCourbevoie = new Agence();
			agenceCourbevoie.setAdresse(adresseAgenceCourbevoie);
			agenceCourbevoie.setNom("Courbevoie Victor Hugo");
			agenceCourbevoie.setCodeGuichet("22C92");
			agenceCourbevoie.setBanque(societeGenerale);

			Type typecompteJachimSenior = new Type();
			typecompteJachimSenior.setLibelle(TypeCompte.COURANT);

			Type typecompteJachimJenior = new Type();
			typecompteJachimJenior.setLibelle(TypeCompte.EPARGNE);

			Compte compteJachimSenior = new Compte();
			Compte compteJachimJenior = new Compte();

			compteJachimSenior.setClient(joachimSenior);
			compteJachimSenior.setAgence(agenceCourbevoie);
			compteJachimSenior.setType(typecompteJachimSenior);
			compteJachimSenior.setNumero("111");
			compteJachimSenior.setSolde(BigDecimal.valueOf(1500.0));
			compteJachimSenior.setValidation(true);

			compteJachimJenior.setClient(joachimJenior);
			compteJachimJenior.setAgence(agenceParis);
			compteJachimJenior.setType(typecompteJachimJenior);
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
