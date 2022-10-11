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
@RequestMapping(value = "/client")
@CrossOrigin("*")
public class ClientController {
	//Declaracion de variables
	@Autowired
	private ClientRepository clientRepository;

	//Metodos propios
	@GetMapping("")
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Client findById(@PathVariable BigInteger id) {
		Client client = clientRepository.findById(id).orElse(null);
		if(client != null) {
			return client;
		}
		return null;
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	public Client create(@RequestBody Client client) {
		if(client != null) {
			return clientRepository.save(client);
		}
		return null;
	}
	
	@PutMapping("{id}")
	public Client update(@PathVariable BigInteger id, @RequestBody Client newClient) {
		Client client = clientRepository.findById(id).orElse(null);
		if(client != null && newClient != null) {
			client.setName(newClient.getName());
			return clientRepository.save(client);
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable BigInteger id) {
		Client client = clientRepository.findById(id).orElse(null);
		if(client != null) {
			clientRepository.deleteById(id);
		}
	}
}
