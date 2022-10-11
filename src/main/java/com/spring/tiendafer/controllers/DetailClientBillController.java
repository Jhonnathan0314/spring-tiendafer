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

import com.spring.tiendafer.models.DetailClientBill;
import com.spring.tiendafer.models.ClientBill;
import com.spring.tiendafer.models.Product;
import com.spring.tiendafer.repositories.ClientBillRepository;
import com.spring.tiendafer.repositories.DetailClientBillRepository;
import com.spring.tiendafer.repositories.ProductRepository;

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
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientBillRepository clientBillRepository;

	//Metodos propios
	@GetMapping("")
	public List<DetailClientBill> findAll(){
		return detailClientBillRepository.findAll();
	}

	@GetMapping("{id}")
	public DetailClientBill findById(@PathVariable int id) {
		DetailClientBill detailClientBill = detailClientBillRepository.findById(id).orElse(null);
		if(detailClientBill != null) {
			return detailClientBill;
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/product/{id_product}/orderbill/{id_order_bill}")
	public DetailClientBill create(@RequestBody DetailClientBill detailClientBill, 
			@PathVariable int id_product, @PathVariable int id_order_bill) {
		Product product = productRepository.findById(id_product).orElse(null);
		ClientBill clientBill = clientBillRepository.findById(id_order_bill).orElse(null);
		if(detailClientBill != null && product != null && clientBill != null) {
			detailClientBill.setProduct(product);
			detailClientBill.setClientBill(clientBill);
			return detailClientBillRepository.save(detailClientBill);
		}
		return null;
	}

	@PutMapping("/{id}/product/{id_product}/orderbill/{id_order_bill}")
	public DetailClientBill update(@PathVariable int id, @RequestBody DetailClientBill newDetailClientBill,
			@PathVariable int id_product, @PathVariable int id_order_bill) {
		DetailClientBill detailClientBill = detailClientBillRepository.findById(id).orElse(null);
		Product product = productRepository.findById(id_product).orElse(null);
		ClientBill clientBill = clientBillRepository.findById(id_order_bill).orElse(null);
		if(newDetailClientBill != null && detailClientBill != null && product != null && clientBill != null) {
			detailClientBill.setQuantity(newDetailClientBill.getQuantity());
			detailClientBill.setUnitValue(newDetailClientBill.getUnitValue());
			detailClientBill.setTotalValue(newDetailClientBill.getTotalValue());
			detailClientBill.setProduct(product);
			detailClientBill.setClientBill(clientBill);
			return detailClientBillRepository.save(detailClientBill);
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		DetailClientBill detailClientBill = detailClientBillRepository.findById(id).orElse(null);
		if(detailClientBill != null) {
			detailClientBillRepository.deleteById(id);
		}
	}
}
