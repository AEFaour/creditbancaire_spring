package com.aston.java.spring.orm.domaines;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.aston.java.spring.orm.methods.Methods;

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
public class Client {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@NonNull
	@Column(nullable = false, length = 25)
	private String prenom;
	@NonNull
	@Column(nullable = false, length = 30)
	private String nom;
	@NonNull
	@Column(unique = true, nullable = false, length = 100)
	private String email;
	@NonNull
	private String mdp;
	@NonNull
	private LocalDate ddn; 
	
	@NonNull
	private Integer age;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Compte> comptes = new ArrayList<>();
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Adresse adresse;
	
	@ManyToMany
	private List<Agence> agences;
	
	
	@PrePersist
	@PreUpdate
	protected void avPMerge() {
		prenom = Methods.capitalizeFirstCase(prenom);
		email = email.trim().toLowerCase();
		nom = nom.trim().toUpperCase();
	}
	
	
	@PostLoad
	protected void apPMerge() {
		age = Methods.calculeAgeNow(ddn);
	}
	

}
