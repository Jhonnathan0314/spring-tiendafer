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
	/**
	 * @return allDetailClientBill existing in DB
	 */
	@GetMapping("")
	public List<DetailClientBill> findAll(){
		return detailClientBillRepository.findAll();
	}

	/**
	 * @param id => detailClientBill id that you want to find, it comes from URL
	 * @return DetailClientBill with id received
	 */
	@GetMapping("{id}")
	public DetailClientBill findById(@PathVariable int id) {
		DetailClientBill detailClientBill = detailClientBillRepository.findById(id).orElse(null);
		if(detailClientBill != null) {
			return detailClientBill;
		}
		return null;
	}

	/**
	 * @param detailClientBill => detailClientBill Object that contains quantity, unit value and total value (quantity * unit value)
	 * @param idProduct => Product id that you want to include in detailClientBill, it comes from URL
	 * @param idClientBill => ClientBill id that you want to include in detailClientBill, it comes from URL
	 * @return DetailClientBill created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/product/{idProduct}/clientbill/{idClientBill}")
	public DetailClientBill create(@RequestBody DetailClientBill detailClientBill, 
			@PathVariable int idProduct, @PathVariable int idClientBill) {
		Product product = productRepository.findById(idProduct).orElse(null);
		ClientBill clientBill = clientBillRepository.findById(idClientBill).orElse(null);
		if(detailClientBill != null && product != null && clientBill != null) {
			detailClientBill.setProduct(product);
			detailClientBill.setClientBill(clientBill);
			return updateDetailClientBill(detailClientBill, product, clientBill);
		}
		return null;
	}

	/**
	 * @param id => DetailClientBill id that you want to update, it comes from URL
	 * @param newDetailClientBill => detailClientBill Object that contains the new quantity, unit value and total value (quantity * unit value)
	 * @param idProduct => Product id that you want to update in detailClientBill, it comes from URL
	 * @param idClientBill => ClientBill id that you want to update in detailClientBill, it comes from URL
	 * @return DetailClientBill updated
	 */
	@PutMapping("/{id}/product/{idProduct}/clientbill/{idClientBill}")
	public DetailClientBill update(@PathVariable int id, @RequestBody DetailClientBill newDetailClientBill,
			@PathVariable int idProduct, @PathVariable int idClientBill) {
		DetailClientBill detailClientBill = detailClientBillRepository.findById(id).orElse(null);
		Product product = productRepository.findById(idProduct).orElse(null);
		ClientBill clientBill = clientBillRepository.findById(idClientBill).orElse(null);
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

	/**
	 * @param id => detailClientBill id that you want to delete, it comes from URL
	 */
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		DetailClientBill detailClientBill = detailClientBillRepository.findById(id).orElse(null);
		if(detailClientBill != null) {
			detailClientBillRepository.deleteById(id);
		}
	}
	
	/**
	 * @param detailClientBill => DetailClientBill object that comes from create method
	 * @param product => Product object that comes from create method
	 * @param clientBill => ClientBill object that comes from create method
	 * @return DetailClientBill updated
	 */
	private DetailClientBill updateDetailClientBill(DetailClientBill detailClientBill, Product product, ClientBill clientBill) {
		product.setQuantityAvailable(product.getQuantityAvailable() - detailClientBill.getQuantity());
		Product answerProduct = productRepository.save(product);
		clientBill.setTotalValue(clientBill.getTotalValue() + detailClientBill.getTotalValue());
		ClientBill clientBillAnswer = clientBillRepository.save(clientBill);
		detailClientBill.setProduct(answerProduct);
		detailClientBill.setClientBill(clientBillAnswer);
		return detailClientBillRepository.save(detailClientBill);
	}
}
