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
@Table(name = "order_bill")
public class OrderBill {
	//Declaracion de variables
	@Id
	private int idOrderBill;
	private String date;
	private int totalValue;
	@OneToOne
	@JoinColumn(name = "id_supplier")
	private Supplier supplier;
	
	//Metodo constructor
	public OrderBill(int idOrderBill, String date, int totalValue) {
		super();
		this.idOrderBill = idOrderBill;
		this.date = date;
		this.totalValue = totalValue;
	}

	public OrderBill() {
		
	}

	//Get y set
	public int getIdOrderBill() {
		return idOrderBill;
	}

	public void setIdOrderBill(int idOrderBill) {
		this.idOrderBill = idOrderBill;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
