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

import com.spring.tiendafer.models.Client;
import com.spring.tiendafer.models.ClientBill;
import com.spring.tiendafer.repositories.ClientBillRepository;
import com.spring.tiendafer.repositories.ClientRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/clientbill")
@CrossOrigin("*")
public class ClientBillController {
	//Declaracion de variables
	@Autowired
	private ClientBillRepository clientBillRepository;
	@Autowired
	private ClientRepository clientRepository;

	//Metodos propios
	/**
	 * @return allClientBill existing in DB
	 */
	@GetMapping("")
	public List<ClientBill> findAll(){
		return clientBillRepository.findAll();
	}
	
	/**
	 * @param id => ClientBill id that you want to find, it comes from URL
	 * @return ClientBill with id received
	 */
	@GetMapping("{id}")
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
	@GetMapping("client/{idClient}")
	public List<ClientBill> findByClient(@PathVariable BigInteger idClient) {
		return clientBillRepository.findByClient(idClient);
	}

	/**
	 * @param clientBill => ClientBill Object that contains total value and date
	 * @param idClient => Client id that you want to include in ClientBill, it comes from URL
	 * @return ClientBill created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/client/{idClient}")
	public ClientBill create(@RequestBody ClientBill clientBill, @PathVariable BigInteger idClient) {
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
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public boolean delete(@PathVariable int id) {
		ClientBill clientBill = clientBillRepository.findById(id).orElse(null);
		if(clientBill != null) {
			clientBillRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
