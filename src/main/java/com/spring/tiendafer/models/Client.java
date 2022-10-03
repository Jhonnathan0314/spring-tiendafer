/**
 * 
 */
package com.spring.tiendafer.models;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jonatan
 *
 */
@Entity
@Table(name = "client")
public class Client {
	//Declaracion de variables
	@Id
	private BigInteger idClient;
	private String name;
	
	//Metodos constructores
	public Client(BigInteger idClient, String name) {
		super();
		this.idClient = idClient;
		this.name = name;
	}

	public Client() {
		
	}

	//Get y Set
	public BigInteger getIdClient() {
		return idClient;
	}

	public void setIdClient(BigInteger idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
