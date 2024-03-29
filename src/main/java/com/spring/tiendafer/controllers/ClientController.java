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
import com.spring.tiendafer.repositories.ClientRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class ClientController {
	//Declaracion de variables
	@Autowired
	private ClientRepository clientRepository;

	//Metodos propios
	/**
	 * @return All Clients existing in DB
	 */
	@GetMapping("clients")
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	/**
	 * @param id => Client id that you want to find, it comes from URL
	 * @return Client with id received
	 */
	@GetMapping("client/{id}")
	public Client findById(@PathVariable BigInteger id) {
		Client client = clientRepository.findById(id).orElse(null);
		if(client != null) {
			return client;
		}
		return null;
	}
	
	/**
	 * @param client => Client Object that contains the client name
	 * @return Client created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("client")
	public Client create(@RequestBody Client client) {
		if(client != null) {
			return clientRepository.save(client);
		}
		return null;
	}
	
	
	/**
	 * @param id => Client id that you want to update, it comes from URL
	 * @param newClient => Client Object that contains the new client name
	 * @return Client updated
	 */
	@PutMapping("client/{id}")
	public Client update(@PathVariable BigInteger id, @RequestBody Client newClient) {
		Client client = clientRepository.findById(id).orElse(null);
		if(client != null && newClient != null) {
			client.setName(newClient.getName());
			client.setNumberBills(client.getNumberBills());
			client.setTotalPending(client.getTotalPending());
			return clientRepository.save(client);
		}
		return null;
	}

	/**
	 * @param id => Client id that you want to delete, it comes from URL
	 */
	@DeleteMapping("client/{id}")
	public ResponseEntity<String> delete(@PathVariable BigInteger id) {
		var headers = new HttpHeaders();
		headers.add("Responded", "ProductController");
		String body = "Cliente no encontrado!";
		Client client = clientRepository.findById(id).orElse(null);
		if(client != null) {
			clientRepository.deleteById(id);
			body = "Cliente eliminado!";
		}
		return ResponseEntity.accepted().headers(headers).body(body);
	}
}
