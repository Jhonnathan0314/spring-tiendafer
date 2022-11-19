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
@Table(name = "product")
public class Product {
	//Declaracion de variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	private String name;
	private int quantityAvailable;
	private int saleValue;
	@OneToOne
	@JoinColumn(name = "id_section")
	private Section section;
	
	//Metodo constructor
	public Product(String name, int quantityAvailable, int saleValue, Section section) {
		super();
		this.name = name;
		this.quantityAvailable = quantityAvailable;
		this.saleValue = saleValue;
		this.section = section;
	}

	public Product() {
		
	}

	//Get y set
	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(int saleValue) {
		this.saleValue = saleValue;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
}
