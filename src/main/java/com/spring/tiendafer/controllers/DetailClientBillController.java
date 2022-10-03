/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.DetailClientBillRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/detailclientbill")
@CrossOrigin("*")
public class DetailClientBillController {
	//Declaracion de variables
	@Autowired
	private DetailClientBillRepository detailClientBillRepository;
}
