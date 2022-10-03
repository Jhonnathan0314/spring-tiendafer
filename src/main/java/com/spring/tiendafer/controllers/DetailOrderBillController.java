/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.DetailOrderBillRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/detailorderbill")
@CrossOrigin("*")
public class DetailOrderBillController {
	//Declaracion de variables
	@Autowired
	private DetailOrderBillRepository detailOrderBillRepository;
}
