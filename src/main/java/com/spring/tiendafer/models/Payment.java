/**
 * 
 */
package com.spring.tiendafer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Jonatan
 *
 */
@Entity
@Table(name = "payment")
public class Payment {
	//Declaracion de variables
	@Id
	private int idPayment;
	private String date;
	private int cash;
	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;

	//Metodo constructor
	public Payment(int idPayment, String date, int cash, Client client) {
		super();
		this.idPayment = idPayment;
		this.date = date;
		this.cash = cash;
		this.client = client;
	}

	public Payment() {

	}

	//Get y set
	public int getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
