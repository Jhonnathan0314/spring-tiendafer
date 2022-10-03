/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.ClientBillHasPaymentRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/clientbillhaspayment")
@CrossOrigin("*")
public class ClientBillHasPaymentController {
	//Declaracion de variables
	@Autowired
	private ClientBillHasPaymentRepository clientBillHasPaymentRepository;
}
