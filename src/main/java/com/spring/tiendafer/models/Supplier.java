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
@Table(name = "supplier")
public class Supplier {
	//Declaracion de variables
	@Id
	private BigInteger idSupplier;
	private String supplierName;
	private String sellerName;
	
	//Metodo constructor
	public Supplier(BigInteger idSupplier, String supplierName, String sellerName) {
		super();
		this.idSupplier = idSupplier;
		this.supplierName = supplierName;
		this.sellerName = sellerName;
	}

	public Supplier() {
		
	}

	//Get y set
	public BigInteger getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(BigInteger idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}
