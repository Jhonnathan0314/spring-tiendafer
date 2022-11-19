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
@Table(name = "detail_client_bill")
public class DetailClientBill {
	//Declaracion de variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetailClientBill;
	private int quantity;
	private int unitValue;
	private int totalValue;
	@OneToOne
	@JoinColumn(name = "id_product")
	private Product product;
	@OneToOne
	@JoinColumn(name = "id_client_bill")
	private ClientBill clientBill;
	
	//Metodo constructor
	public DetailClientBill(int quantity, int unitValue, int totalValue, Product product,
			ClientBill clientBill) {
		super();
		this.quantity = quantity;
		this.unitValue = unitValue;
		this.totalValue = totalValue;
		this.product = product;
		this.clientBill = clientBill;
	}

	public DetailClientBill() {
		
	}

	//Get y set
	public int getIdDetailClientBill() {
		return idDetailClientBill;
	}

	public void setIdDetailClientBill(int idDetailClientBill) {
		this.idDetailClientBill = idDetailClientBill;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(int unitValue) {
		this.unitValue = unitValue;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ClientBill getClientBill() {
		return clientBill;
	}

	public void setClientBill(ClientBill clientBill) {
		this.clientBill = clientBill;
	}
}
