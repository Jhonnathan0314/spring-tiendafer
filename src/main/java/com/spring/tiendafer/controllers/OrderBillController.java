/**
 * 
 */
package com.spring.tiendafer.controllers;

import java.math.BigInteger;
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

import com.spring.tiendafer.models.DetailOrderBill;
import com.spring.tiendafer.models.OrderBill;
import com.spring.tiendafer.models.Product;
import com.spring.tiendafer.models.Supplier;
import com.spring.tiendafer.repositories.DetailOrderBillRepository;
import com.spring.tiendafer.repositories.OrderBillRepository;
import com.spring.tiendafer.repositories.ProductRepository;
import com.spring.tiendafer.repositories.SupplierRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class OrderBillController {
	//Declaracion de variables
	@Autowired
	private OrderBillRepository orderBillRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private DetailOrderBillRepository detailOrderBillRepository;
	@Autowired
	private ProductRepository productRepository;

	//Metodos propios
	/**
	 * @return allClientBill existing in DB
	 */
	@GetMapping("orderbills")
	public List<OrderBill> findAll(){
		return orderBillRepository.findAll();
	}

	/**
	 * @param id => ClientBill id that you want to find, it comes from URL
	 * @return ClientBill with id received
	 */
	@GetMapping("orderbill/{id}")
	public OrderBill findById(@PathVariable int id) {
		OrderBill orderBill = orderBillRepository.findById(id).orElse(null);
		if(orderBill != null) {
			return orderBill;
		}
		return null;
	}

	/**
	 * @param id => ClientBill id that you want to find, it comes from URL
	 * @return ClientBill with id received
	 */
	@GetMapping("orderbills/supplier/{idSupplier}")
	public List<OrderBill> findBySupplier(@PathVariable BigInteger idSupplier) {
		return orderBillRepository.findBySupplier(idSupplier);
	}
	
	/**
	 * @param orderBill => OrderBill Object that contains total value and date
	 * @param id_supplier => Supplier id that you want to include in OrderBill, it comes from URL
	 * @return OrderBill created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("orderbill/supplier/{idSupplier}")
	public OrderBill create(@RequestBody OrderBill orderBill, @PathVariable BigInteger idSupplier) {
		Supplier supplier = supplierRepository.findById(idSupplier).orElse(null);
		if(orderBill != null && supplier != null) {
			orderBill.setSupplier(supplier);
			return orderBillRepository.save(orderBill);
		}
		return null;
	}
	
	@PutMapping("orderbill/{idOrder}/supplier/{idSupplier}")
	public OrderBill addSupplier(@PathVariable int idOrder, @PathVariable BigInteger idSupplier) {
		OrderBill order = orderBillRepository.findById(idOrder).orElse(null);
		Supplier supplier = supplierRepository.findById(idSupplier).orElse(null);
		if(order != null && supplier != null) {
			order.setSupplier(supplier);
			return orderBillRepository.save(order);
		}
		return null;
	}

	/**
	 * @param id => ClientBill id that you want to delete, it comes from URL
	 */
	@DeleteMapping("orderbill/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		var headers = new HttpHeaders();
		headers.add("Responded", "ProductController");
		String body = "Pedido no encontrado!";
		OrderBill orderBill = orderBillRepository.findById(id).orElse(null);
		if(orderBill != null) {
			checkDetailOrderBill(orderBill);
			orderBillRepository.deleteById(id);
			body = "Pedido eliminado!";
		}
		return ResponseEntity.accepted().headers(headers).body(body);
	}
	
	private void checkDetailOrderBill(OrderBill orderBill) {
		List<DetailOrderBill> details = detailOrderBillRepository.findAll();
		for(DetailOrderBill detail: details) {
			if(detail.getOrderBill().getIdOrderBill() == orderBill.getIdOrderBill()) {
				deleteOrderProduct(detail);
				detailOrderBillRepository.deleteById(detail.getIdDetailOrderBill());
			}
		}
	}
	
	/**
	 * @param detailOrderBill => DetailOrderBill object to be deleted
	 */
	public void deleteOrderProduct(DetailOrderBill detailOrderBill) {
		Product product = productRepository.findById(detailOrderBill.getProduct().getIdProduct()).orElse(null);
		product.setQuantityAvailable(product.getQuantityAvailable() - detailOrderBill.getReceivedQuantity());
		productRepository.save(product);
		
		OrderBill orderBill = orderBillRepository.findById(detailOrderBill.getOrderBill().getIdOrderBill()).orElse(null);
		orderBill.setTotalValue(orderBill.getTotalValue() - detailOrderBill.getTotalValue());
		orderBillRepository.save(orderBill);
	}
}
