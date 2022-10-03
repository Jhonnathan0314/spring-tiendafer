/**
 * 
 */
package com.spring.tiendafer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jonatan
 *
 */
@Entity
@Table(name = "section")
public class Section {
	//Declaracion de variables
	@Id
	private int idSection;
	private String name;

	//Metodo constructor
	public Section(int idSection, String name) {
		super();
		this.idSection = idSection;
		this.name = name;
	}

	public Section() {

	}

	//Get y set
	public int getIdSection() {
		return idSection;
	}

	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
