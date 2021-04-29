package com.aston.java.spring.orm.services;

import com.aston.java.spring.orm.domaines.Compte;

public interface ICompteService {
	void crediter(Integer num, Double solde);
	Compte getCompte(Integer num);
	void transfert(Integer source, Integer destination, Double solde);

}
