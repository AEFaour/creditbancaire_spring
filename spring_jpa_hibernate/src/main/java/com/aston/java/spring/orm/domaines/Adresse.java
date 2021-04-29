package com.aston.java.spring.orm.domaines;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
public class Adresse {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@NonNull
	private String voie;
	@NonNull
	private String codePostal;
	@NonNull
	private String ville;
	@NonNull
	private String pays;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL)
	private List<Client> clients = new ArrayList<>();
	
	@OneToOne(mappedBy = "adresse")
	private Agence agence; 
	
	@OneToOne(mappedBy = "adresse")
	private Banque banque; 

}
