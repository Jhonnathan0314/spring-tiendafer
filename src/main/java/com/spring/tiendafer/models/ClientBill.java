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
@Table(name = "client_bill")
public class ClientBill {
	//Declaracion de variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClientBill;
	private float totalValue;
	private String date;
	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;
	
	//Metodo constructor
	public ClientBill(float totalValue, String date, Client client) {
		super();
		this.totalValue = totalValue;
		this.date = date;
		this.client = client;
	}

	public ClientBill() {
		
	}
	
	//Get y set
	public int getIdClientBill() {
		return idClientBill;
	}

	public void setIdClientBill(int idClientBill) {
		this.idClientBill = idClientBill;
	}

	public float getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
