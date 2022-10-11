/**
 * 
 */
package com.spring.tiendafer.controllers;

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

import com.spring.tiendafer.models.DetailOrderBill;
import com.spring.tiendafer.models.OrderBill;
import com.spring.tiendafer.models.Product;
import com.spring.tiendafer.repositories.DetailOrderBillRepository;
import com.spring.tiendafer.repositories.OrderBillRepository;
import com.spring.tiendafer.repositories.ProductRepository;

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
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderBillRepository orderBillRepository;

	//Metodos propios
	@GetMapping("")
	public List<DetailOrderBill> findAll(){
		return detailOrderBillRepository.findAll();
	}

	@GetMapping("{id}")
	public DetailOrderBill findById(@PathVariable int id) {
		DetailOrderBill detailOrderBill = detailOrderBillRepository.findById(id).orElse(null);
		if(detailOrderBill != null) {
			return detailOrderBill;
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/product/{id_product}/orderbill/{id_order_bill}")
	public DetailOrderBill create(@RequestBody DetailOrderBill detailOrderBill, 
			@PathVariable int id_product, @PathVariable int id_order_bill) {
		Product product = productRepository.findById(id_product).orElse(null);
		OrderBill orderBill = orderBillRepository.findById(id_order_bill).orElse(null);
		if(detailOrderBill != null && product != null && orderBill != null) {
			detailOrderBill.setProduct(product);
			detailOrderBill.setOrderBill(orderBill);
			return detailOrderBillRepository.save(detailOrderBill);
		}
		return null;
	}

	@PutMapping("/{id}/product/{id_product}/orderbill/{id_order_bill}")
	public DetailOrderBill update(@PathVariable int id, @RequestBody DetailOrderBill newDetailOrderBill,
			@PathVariable int id_product, @PathVariable int id_order_bill) {
		DetailOrderBill detailOrderBill = detailOrderBillRepository.findById(id).orElse(null);
		Product product = productRepository.findById(id_product).orElse(null);
		OrderBill orderBill = orderBillRepository.findById(id_order_bill).orElse(null);
		if(newDetailOrderBill != null && detailOrderBill != null && product != null && orderBill != null) {
			detailOrderBill.setOrderedQuantity(newDetailOrderBill.getOrderedQuantity());
			detailOrderBill.setReceivedQuantity(newDetailOrderBill.getReceivedQuantity());
			detailOrderBill.setUnitValue(newDetailOrderBill.getUnitValue());
			detailOrderBill.setTotalValue(newDetailOrderBill.getTotalValue());
			detailOrderBill.setProduct(product);
			detailOrderBill.setOrderBill(orderBill);
			return detailOrderBillRepository.save(detailOrderBill);
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		DetailOrderBill detailOrderBill = detailOrderBillRepository.findById(id).orElse(null);
		if(detailOrderBill != null) {
			detailOrderBillRepository.deleteById(id);
		}
	}
}
