/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.ClientBillRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/clientbill")
@CrossOrigin("*")
public class ClientBillController {
	//Declaracion de variables
	@Autowired
	private ClientBillRepository clientBillRepository;
}
