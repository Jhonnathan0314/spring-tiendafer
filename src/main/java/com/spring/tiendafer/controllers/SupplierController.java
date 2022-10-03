/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.SupplierRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/supplier")
@CrossOrigin("*")
public class SupplierController {
	//Declaracion de variables
	@Autowired
	private SupplierRepository supplierRepository;
}
