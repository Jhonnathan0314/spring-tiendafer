/**
 * 
 */
package com.spring.tiendafer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
			return createDetailClientBill(detailClientBill, product, clientBill);
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
			return updateDetailClientBill(detailClientBill, newDetailClientBill, product, clientBill);
		}
		return null;
	}

	/**
	 * @param id => detailClientBill id that you want to delete, it comes from URL
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		var headers = new HttpHeaders();
		headers.add("Responded", "ProductController");
		String body = "Detalle de factura no encontrado!";
		DetailClientBill detailClientBill = detailClientBillRepository.findById(id).orElse(null);
		if(detailClientBill != null) {
			deleteBillProduct(detailClientBill);
			detailClientBillRepository.deleteById(id);
			body = "Detalle de factura eliminado!";
		}
		return ResponseEntity.accepted().headers(headers).body(body);
	}
	
	/**
	 * @param detailClientBill => DetailClientBill object that comes from create method
	 * @param product => Product object that comes from create method
	 * @param clientBill => ClientBill object that comes from create method
	 * @return DetailClientBill updated
	 */
	private DetailClientBill createDetailClientBill(DetailClientBill detailClientBill, Product product, ClientBill clientBill) {
		product.setQuantityAvailable(product.getQuantityAvailable() - detailClientBill.getQuantity());
		Product answerProduct = productRepository.save(product);
		clientBill.setTotalValue(clientBill.getTotalValue() + detailClientBill.getTotalValue());
		ClientBill clientBillAnswer = clientBillRepository.save(clientBill);
		detailClientBill.setProduct(answerProduct);
		detailClientBill.setClientBill(clientBillAnswer);
		return detailClientBillRepository.save(detailClientBill);
	}
	
	/**
	 * @param detailClientBill => DetailClientBill object that comes from create method
	 * @param product => Product object that comes from create method
	 * @param clientBill => ClientBill object that comes from create method
	 * @return DetailClientBill updated
	 */
	private DetailClientBill updateDetailClientBill(DetailClientBill detailClientBill, DetailClientBill newDetailClientBill, Product product, ClientBill clientBill) {
		product.setQuantityAvailable(product.getQuantityAvailable() + detailClientBill.getQuantity() - newDetailClientBill.getQuantity());
		Product answerProduct = productRepository.save(product);
		clientBill.setTotalValue(clientBill.getTotalValue() + newDetailClientBill.getTotalValue() - detailClientBill.getTotalValue());
		ClientBill clientBillAnswer = clientBillRepository.save(clientBill);
		detailClientBill.setProduct(answerProduct);
		detailClientBill.setClientBill(clientBillAnswer);
		detailClientBill.setQuantity(newDetailClientBill.getQuantity());
		detailClientBill.setUnitValue(newDetailClientBill.getUnitValue());
		detailClientBill.setTotalValue(newDetailClientBill.getTotalValue());
		return detailClientBillRepository.save(detailClientBill);
	}
	
	/**
	 * @param detailClientBill => DetailClientBill object to be deleted
	 */
	public void deleteBillProduct(DetailClientBill detailClientBill) {
		Product product = productRepository.findById(detailClientBill.getProduct().getIdProduct()).orElse(null);
		product.setQuantityAvailable(product.getQuantityAvailable() + detailClientBill.getQuantity());
		productRepository.save(product);
		ClientBill clientBill = clientBillRepository.findById(detailClientBill.getClientBill().getIdClientBill()).orElse(null);
		clientBill.setTotalValue(clientBill.getTotalValue() - detailClientBill.getTotalValue());
		clientBillRepository.save(clientBill);
	}
}
