package com.aston.java.spring.orm.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aston.java.spring.orm.domaines.Compte;
import com.aston.java.spring.orm.repositories.ICompteRepository;

@Repository
@Transactional
public class CompteRepository implements ICompteRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(Integer num) {
		
		if(em.find(Compte.class, num) != null) {
			em.remove(em.find(Compte.class, num));
			
		}

	}

	@Override
	public List<Compte> find(Boolean validation) {
		TypedQuery<Compte> requete = em.createQuery("select c from Compte c where c.validation = validation", Compte.class);

		return requete.setParameter("validation", validation).getResultList();
	}

	@Override
	public Compte find(Integer num) {
	
		return em.find(Compte.class, num);
	}

	@Override
	public List<Compte> find(List<Integer> nums) {

		List<Compte> comptes = new ArrayList<>();
		for (Integer num : nums) {
			if(find(num) != null) {
				comptes.add(find(num));
			}
		}
		return comptes;
	}

	@Override
	public List<Compte> find(String str) {
		TypedQuery<Compte> requete = em.createQuery("select c from Compte c where c.numero = numero", Compte.class);

		return requete.setParameter("numero", str).getResultList();
	}

	@Override
	public void insert(Compte compte) {
		
		em.persist(compte);

	}

	@Override
	public void update(Compte compte) {
		TypedQuery<Compte> requete = em.createQuery("select c from Compte c", Compte.class);
		List<Compte> comptes = requete.getResultList();
		if (comptes.contains(compte)) {
			em.merge(compte);
		}
	}

	@Override
	public void update(List<Compte> comptes) {
		TypedQuery<Compte> requete = em.createQuery("select c from Compte c", Compte.class);
		comptes = requete.getResultList();
		for(Compte compte : comptes) {
			update(compte);
		}

	}

}
