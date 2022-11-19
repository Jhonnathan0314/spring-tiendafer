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
	/**
	 * @return allDetailOrderBill existing in DB
	 */
	@GetMapping("")
	public List<DetailOrderBill> findAll(){
		return detailOrderBillRepository.findAll();
	}

	/**
	 * @param id => detailOrderBill id that you want to find, it comes from URL
	 * @return DetailOrderBill with id received
	 */
	@GetMapping("{id}")
	public DetailOrderBill findById(@PathVariable int id) {
		DetailOrderBill detailOrderBill = detailOrderBillRepository.findById(id).orElse(null);
		if(detailOrderBill != null) {
			return detailOrderBill;
		}
		return null;
	}

	/**
	 * @param detailOrderBill => detailOrderBill Object that contains quantity asked, quantity received, unit value and total value (quantity received * unit value)
	 * @param idProduct => Product id that you want to include in detailOrderBill, it comes from URL
	 * @param idOrderBill => OrderBill id that you want to include in detailOrderBill, it comes from URL
	 * @return DetailOrderBill created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/product/{idProduct}/orderbill/{idOrderBill}")
	public DetailOrderBill create(@RequestBody DetailOrderBill detailOrderBill, 
			@PathVariable int idProduct, @PathVariable int idOrderBill) {
		Product product = productRepository.findById(idProduct).orElse(null);
		OrderBill orderBill = orderBillRepository.findById(idOrderBill).orElse(null);
		if(detailOrderBill != null && product != null && orderBill != null) {
			detailOrderBill.setProduct(product);
			detailOrderBill.setOrderBill(orderBill);
			return createDetailOrderBill(detailOrderBill, product, orderBill);
		}
		return null;
	}

	/**
	 * @param id => DetailOrderBill id that you want to update, it comes from URL
	 * @param newDetailOrderBill => detailOrderBill Object that contains the new quantity, unit value and total value (quantity * unit value)
	 * @param idProduct => Product id that you want to update in detailOrderBill, it comes from URL
	 * @param idOrderBill => OrderBill id that you want to update in detailOrderBill, it comes from URL
	 * @return DetailOrderBill updated
	 */
	@PutMapping("/{id}/product/{idProduct}/orderbill/{idOrderBill}")
	public DetailOrderBill update(@PathVariable int id, @RequestBody DetailOrderBill newDetailOrderBill,
			@PathVariable int idProduct, @PathVariable int idOrderBill) {
		DetailOrderBill detailOrderBill = detailOrderBillRepository.findById(id).orElse(null);
		Product product = productRepository.findById(idProduct).orElse(null);
		OrderBill orderBill = orderBillRepository.findById(idOrderBill).orElse(null);
		if(newDetailOrderBill != null && detailOrderBill != null && product != null && orderBill != null) {
			return updateDetailOrdertBill(detailOrderBill, newDetailOrderBill, product, orderBill);
		}
		return null;
	}

	/**
	 * @param id => detailOrderBill id that you want to delete, it comes from URL
	 */
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public boolean delete(@PathVariable int id) {
		DetailOrderBill detailOrderBill = detailOrderBillRepository.findById(id).orElse(null);
		if(detailOrderBill != null) {
			deleteOrderProduct(detailOrderBill);
			detailOrderBillRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	/**
	 * @param detailOrderBill => DetailOrderBill object that comes from create method
	 * @param product => Product object that comes from create method
	 * @param orderBill => OrderBill object that comes from create method
	 * @return DetailOrderBill updated
	 */
	private DetailOrderBill createDetailOrderBill(DetailOrderBill detailOrderBill, Product product, OrderBill orderBill) {
		product.setQuantityAvailable(product.getQuantityAvailable() + detailOrderBill.getReceivedQuantity());
		Product answerProduct = productRepository.save(product);
		orderBill.setTotalValue(orderBill.getTotalValue() + detailOrderBill.getTotalValue());
		OrderBill orderBillAnswer = orderBillRepository.save(orderBill);
		detailOrderBill.setProduct(answerProduct);
		detailOrderBill.setOrderBill(orderBillAnswer);
		return detailOrderBillRepository.save(detailOrderBill);
	}
	
	/**
	 * @param detailOrderBill => DetailClientBill object that comes from create method
	 * @param newDetailOrderBill => newDetailClientBill object that comes from create method
	 * @param product => Product object that comes from create method
	 * @param orderBill => ClientBill object that comes from create method
	 * @return DetailClientBill updated
	 */
	private DetailOrderBill updateDetailOrdertBill(DetailOrderBill detailOrderBill, DetailOrderBill newDetailOrderBill, Product product, OrderBill orderBill) {
		product.setQuantityAvailable(product.getQuantityAvailable() - detailOrderBill.getReceivedQuantity() + newDetailOrderBill.getReceivedQuantity());
		Product answerProduct = productRepository.save(product);
		orderBill.setTotalValue(orderBill.getTotalValue() + newDetailOrderBill.getTotalValue() - detailOrderBill.getTotalValue());
		OrderBill orderBillAnswer = orderBillRepository.save(orderBill);
		detailOrderBill.setProduct(answerProduct);
		detailOrderBill.setOrderBill(orderBillAnswer);
		detailOrderBill.setOrderedQuantity(newDetailOrderBill.getOrderedQuantity());
		detailOrderBill.setReceivedQuantity(newDetailOrderBill.getReceivedQuantity());
		detailOrderBill.setUnitValue(newDetailOrderBill.getUnitValue());
		detailOrderBill.setTotalValue(newDetailOrderBill.getTotalValue());
		return detailOrderBillRepository.save(detailOrderBill);
	}
	
	/**
	 * @param detailOrderBill => DetailOrderBill object to be deleted
	 */
	private void deleteOrderProduct(DetailOrderBill detailOrderBill) {
		Product product = productRepository.findById(detailOrderBill.getProduct().getIdProduct()).orElse(null);
		product.setQuantityAvailable(product.getQuantityAvailable() - detailOrderBill.getReceivedQuantity());
		productRepository.save(product);
		OrderBill orderBill = orderBillRepository.findById(detailOrderBill.getOrderBill().getIdOrderBill()).orElse(null);
		orderBill.setTotalValue(orderBill.getTotalValue() - detailOrderBill.getTotalValue());
		orderBillRepository.save(orderBill);
	}
}
