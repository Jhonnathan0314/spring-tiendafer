/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.ClientRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/client")
@CrossOrigin("*")
public class ClientController {
	//Declaracion de variables
	@Autowired
	private ClientRepository clientRepository;

	
}
