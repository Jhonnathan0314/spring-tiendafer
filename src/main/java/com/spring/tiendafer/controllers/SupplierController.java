/**
 * 
 */
package com.spring.tiendafer.controllers;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.models.Supplier;
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

	//Metodos propios
	@GetMapping("")
	public List<Supplier> findAll(){
		return supplierRepository.findAll();
	}

	@GetMapping("{id}")
	public Supplier findById(@PathVariable BigInteger id) {
		Supplier supplier = supplierRepository.findById(id).orElse(null);
		if(supplier != null) {
			return supplier;
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	public Supplier create(@RequestBody Supplier supplier) {
		if(supplier != null) {
			return supplierRepository.save(supplier);
		}
		return null;
	}

	@PutMapping("{id}")
	public Supplier update(@PathVariable BigInteger id, @RequestBody Supplier newSupplier) {
		Supplier supplier = supplierRepository.findById(id).orElse(null);
		if(supplier != null && newSupplier != null) {
			supplier.setSupplierName(newSupplier.getSupplierName());
			supplier.setSellerName(newSupplier.getSellerName());
			return supplierRepository.save(supplier);
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable BigInteger id) {
		Supplier supplier = supplierRepository.findById(id).orElse(null);
		if(supplier != null) {
			supplierRepository.deleteById(id);
		}
	}
}
