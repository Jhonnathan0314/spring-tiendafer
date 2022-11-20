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
	private float pendingValue;
	private String date;
	private boolean isPending;
	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;
	
	//Metodo constructor
	public ClientBill(float totalValue, float pendingValue, String date, boolean isPending, Client client) {
		super();
		this.totalValue = totalValue;
		this.pendingValue = pendingValue;
		this.date = date;
		this.isPending = isPending;
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

	public float getPendingValue() {
		return pendingValue;
	}

	public void setPendingValue(float pendingValue) {
		this.pendingValue = pendingValue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
