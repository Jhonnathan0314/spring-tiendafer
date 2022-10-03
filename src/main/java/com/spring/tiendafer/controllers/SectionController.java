/**
 * 
 */
package com.spring.tiendafer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.repositories.SectionRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/section")
@CrossOrigin("*")
public class SectionController {
	//Declaracion de variables
	@Autowired
	private SectionRepository sectionRepository;
}
