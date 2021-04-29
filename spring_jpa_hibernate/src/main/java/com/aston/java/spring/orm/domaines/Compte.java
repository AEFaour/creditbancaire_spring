package com.aston.java.spring.orm.domaines;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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
public class Compte {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@NonNull
	@Column(nullable = false, length = 15)
	private String numero;
	@NonNull
	private BigDecimal solde;
	@NonNull
	private Boolean validation;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Client client; 
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	//@JoinColumn(nullable = false)
	private Agence agence;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Type type;
	

}
