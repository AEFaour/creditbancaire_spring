package com.aston.java.spring.orm.services.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aston.java.spring.orm.domaines.Compte;
import com.aston.java.spring.orm.repositories.ICompteRepository;
import com.aston.java.spring.orm.services.ICompteService;

@Service
@Transactional
public class CompteService implements ICompteService{
	
	@Autowired
	private ICompteRepository repository;

	@Override
	public void crediter(Integer num, Double solde) {
		Compte compte = repository.find(num);
		BigDecimal bdSolde = BigDecimal.valueOf(solde);
		compte.setSolde(compte.getSolde().add(bdSolde));
		repository.update(compte);
		
	}

	@Override
	public Compte getCompte(Integer num) {
		// TODO Auto-generated method stub
		return repository.find(num);
	}

	@Override
	public void transfert(Integer source, Integer destination, Double solde) {
		Compte compteSource = repository.find(source);
		Compte compteDestination = repository.find(destination);
		BigDecimal bdSolde = BigDecimal.valueOf(solde);
		compteSource.setSolde(compteSource.getSolde().subtract(bdSolde));
		compteDestination.setSolde(compteDestination.getSolde().add(bdSolde));
		repository.update(compteSource);
		repository.update(compteDestination);
		
	}


}
