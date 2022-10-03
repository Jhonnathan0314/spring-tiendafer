/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.PaymentRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/payment")
@CrossOrigin("*")
public class PaymentController {
	//Declaracion de variables
	@Autowired
	private PaymentRepository paymentRepository;
}
