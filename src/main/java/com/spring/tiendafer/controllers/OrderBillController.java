/**
 * 
 */
package com.spring.tiendafer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.models.DetailOrderBill;
import com.spring.tiendafer.repositories.OrderBillRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping("orderbill")
@CrossOrigin("*")
public class OrderBillController {
	//Declaracion de variables
	@Autowired
	private OrderBillRepository orderBillRepository;
}
