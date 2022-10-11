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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.models.OrderBill;
import com.spring.tiendafer.models.Supplier;
import com.spring.tiendafer.repositories.OrderBillRepository;
import com.spring.tiendafer.repositories.SupplierRepository;

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
	@Autowired
	private SupplierRepository supplierRepository;

	//Metodos propios
	@GetMapping("")
	public List<OrderBill> findAll(){
		return orderBillRepository.findAll();
	}

	@GetMapping("{id}")
	public OrderBill findById(@PathVariable int id) {
		OrderBill orderBill = orderBillRepository.findById(id).orElse(null);
		if(orderBill != null) {
			return orderBill;
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/supplier/{id_supplier}")
	public OrderBill create(@RequestBody OrderBill orderBill, @PathVariable BigInteger id_supplier) {
		Supplier supplier = supplierRepository.findById(id_supplier).orElse(null);
		if(orderBill != null && supplier != null) {
			orderBill.setSupplier(supplier);
			return orderBillRepository.save(orderBill);
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		OrderBill orderBill = orderBillRepository.findById(id).orElse(null);
		if(orderBill != null) {
			orderBillRepository.deleteById(id);
		}
	}
}
