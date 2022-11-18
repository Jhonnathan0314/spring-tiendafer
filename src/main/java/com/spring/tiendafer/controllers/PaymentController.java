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
import com.spring.tiendafer.models.Payment;
import com.spring.tiendafer.repositories.ClientRepository;
import com.spring.tiendafer.repositories.PaymentRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/payment")
@CrossOrigin("*")
public class PaymentController {
	//Declaracion de variables
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ClientRepository clientRepository;

	//Metodos propios
	/**
	 * @return all Payments existing in DB
	 */
	@GetMapping("")
	public List<Payment> findAll(){
		return paymentRepository.findAll();
	}

	
	/**
	 * @param id => Payment id that you want to find, it comes from URL
	 * @return
	 */
	@GetMapping("{id}")
	public Payment findById(@PathVariable int id) {
		Payment payment = paymentRepository.findById(id).orElse(null);
		if(payment != null) {
			return payment;
		}
		return null;
	}

	
	/**
	 * @param payment => Payment Object that contains date and cash
	 * @param id_client => Client id that you want to include in Payment, it comes from URL
	 * @return Payment created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/client/{id_client}")
	public Payment create(@RequestBody Payment payment, @PathVariable BigInteger id_client) {
		Client client = clientRepository.findById(id_client).orElse(null);
		if(payment != null && client != null) {
			payment.setClient(client);
			return paymentRepository.save(payment);
		}
		return null;
	}
	
	/**
	 * @param idPayment => Payment id that you want to update, it comes from URL
	 * @param payment => Payment Object that contains new date and cash
	 * @param id_client => Client id that you want to update in Payment, it comes from URL
	 * @return Payment updated
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("{idPayment}/client/{id_client}")
	public Payment update(@PathVariable int idPayment, @RequestBody Payment payment, @PathVariable BigInteger id_client) {
		Client client = clientRepository.findById(id_client).orElse(null);
		if(payment != null && client != null) {
			payment.setClient(client);
			return paymentRepository.save(payment);
		}
		return null;
	}

	
	/**
	 * @param id => Payment id that you want to delete, it comes from URL
	 */
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		Payment payment = paymentRepository.findById(id).orElse(null);
		if(payment != null) {
			paymentRepository.deleteById(id);
		}
	}
}
