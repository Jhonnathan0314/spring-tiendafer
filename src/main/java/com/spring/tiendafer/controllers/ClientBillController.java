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

import com.spring.tiendafer.models.Client;
import com.spring.tiendafer.models.ClientBill;
import com.spring.tiendafer.models.DetailClientBill;
import com.spring.tiendafer.models.Product;
import com.spring.tiendafer.repositories.ClientBillRepository;
import com.spring.tiendafer.repositories.ClientRepository;
import com.spring.tiendafer.repositories.DetailClientBillRepository;
import com.spring.tiendafer.repositories.ProductRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class ClientBillController {
	//Declaracion de variables
	@Autowired
	private ClientBillRepository clientBillRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private DetailClientBillRepository detailClientBillRepository;
	@Autowired
	private ProductRepository productRepository;

	//Metodos propios
	/**
	 * @return allClientBill existing in DB
	 */
	@GetMapping("clientbills")
	public List<ClientBill> findAll(){
		return clientBillRepository.findAll();
	}
	
	/**
	 * @param id => ClientBill id that you want to find, it comes from URL
	 * @return ClientBill with id received
	 */
	@GetMapping("clientbill/{id}")
	public ClientBill findById(@PathVariable int id) {
		ClientBill clientBill = clientBillRepository.findById(id).orElse(null);
		if(clientBill != null) {
			return clientBill;
		}
		return null;
	}
	
	/**
	 * @param id => ClientBill id that you want to find, it comes from URL
	 * @return ClientBill with id received
	 */
	@GetMapping("clientbills/client/{idClient}")
	public List<ClientBill> findByClient(@PathVariable BigInteger idClient) {
		return clientBillRepository.findByClient(idClient);
	}

	/**
	 * @param clientBill => ClientBill Object that contains total value and date
	 * @param idClient => Client id that you want to include in ClientBill, it comes from URL
	 * @return ClientBill created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("clientbill/client/{idClient}")
	public ClientBill create(@RequestBody ClientBill clientBill, @PathVariable BigInteger idClient) {
		Client client = clientRepository.findById(idClient).orElse(null);
		if(clientBill != null && client != null) {
			if(client.getIdClient() != new BigInteger(0+"")) {
				client.setNumberBills(client.getNumberBills() + 1);
			}
			clientRepository.save(client);
			clientBill.setClient(client);
			return clientBillRepository.save(clientBill);
		}
		return null;
	}
	
	@PutMapping("clientbill/{idClientBill}/client/{idClient}")
	public ClientBill addClient(@PathVariable int idClientBill, @PathVariable BigInteger idClient) {
		ClientBill clientBill = clientBillRepository.findById(idClientBill).orElse(null);
		Client client = clientRepository.findById(idClient).orElse(null);
		if(clientBill != null && client != null) {
			clientBill.setClient(client);
			return clientBillRepository.save(clientBill);
		}
		return null;
	}
	
	/**
	 * @param id => ClientBill id that you want to delete, it comes from URL
	 */
	@DeleteMapping("clientbill/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		var headers = new HttpHeaders();
		headers.add("Responded", "ProductController");
		String body = "Factura no encontrada!";
		ClientBill clientBill = clientBillRepository.findById(id).orElse(null);
		Client client = clientRepository.findById(clientBill.getClient().getIdClient()).orElse(null);
		if(clientBill != null) {
			checkDetailClientBill(clientBill);
			client.setNumberBills(client.getNumberBills() - 1);
			client.setTotalPending(client.getTotalPending() - clientBill.getPendingValue());
			clientRepository.save(client);
			clientBillRepository.deleteById(id);
			body = "Factura eliminada!";
		}
		return ResponseEntity.accepted().headers(headers).body(body);
	}

	private void checkDetailClientBill(ClientBill clientBill) {
		List<DetailClientBill> details = detailClientBillRepository.findAll();
		for(DetailClientBill detail: details) {
			if(detail.getClientBill().getIdClientBill() == clientBill.getIdClientBill()) {
				deleteClientBillProduct(detail);
			}
			detailClientBillRepository.deleteById(detail.getIdDetailClientBill());
		}
	}
	
	/**
	 * @param detailClientBill => DetailClientBill object to be deleted
	 */
	public void deleteClientBillProduct(DetailClientBill detailClientBill) {
		Product product = productRepository.findById(detailClientBill.getProduct().getIdProduct()).orElse(null);
		product.setQuantityAvailable(product.getQuantityAvailable() + detailClientBill.getQuantity());
		productRepository.save(product);
		
		ClientBill clientBill = clientBillRepository.findById(detailClientBill.getClientBill().getIdClientBill()).orElse(null);
		clientBill.setTotalValue(clientBill.getTotalValue() - detailClientBill.getTotalValue());
		clientBillRepository.save(clientBill);
	}
}
