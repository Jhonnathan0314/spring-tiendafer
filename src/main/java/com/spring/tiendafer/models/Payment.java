/**
 * 
 */
package com.spring.tiendafer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPayment;
	private String date;
	private int cash;
	private int changeMoney;
	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;

	//Metodo constructor
	public Payment(String date, int cash, int changeMoney, Client client) {
		super();
		this.date = date;
		this.cash = cash;
		this.changeMoney = changeMoney;
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

	public int getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(int changeMoney) {
		this.changeMoney = changeMoney;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
