package com.aston.java.spring.orm.repositories;

import java.util.List;

import com.aston.java.spring.orm.domaines.Compte;

public interface ICompteRepository {
	
	void delete(Integer num);
	List<Compte> find(Boolean validation);
	Compte find(Integer num);
	List<Compte> find(List<Integer> nums);
	List<Compte> find(String str);
	void insert(Compte compte);
	void update(Compte compte);
	void update(List<Compte> comptes);
	
	
}
