/**
 * 
 */
package com.spring.tiendafer.controllers;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tiendafer.models.Client;
import com.spring.tiendafer.models.ClientBill;
import com.spring.tiendafer.models.Payment;
import com.spring.tiendafer.repositories.ClientBillRepository;
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
	@Autowired
	private ClientBillRepository clientBillRepository;

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
	 * @param idClient => Client id that you want to include in Payment, it comes from URL
	 * @return Payment created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/client/{idClient}")
	public Payment create(@RequestBody Payment payment, @PathVariable BigInteger idClient) {
		Client client = clientRepository.findById(idClient).orElse(null);
		if(payment != null && client != null) {
			payment.setClient(client);
			payment = paymentRepository.save(payment);
			List<ClientBill> clientBills = clientBillRepository.findByClient(idClient);
			refreshBills(clientBills, payment);
			return payment;
		}
		return null;
	}
	
	/**
	 * @param clientBills
	 * @param payment
	 */
	private void refreshBills(List<ClientBill> clientBills, Payment payment) {
		int cash = payment.getCash();
		for(ClientBill clientBill: clientBills) {
			if(cash > 0) {
				if(clientBill.getTotalValue() <= cash) {
					cash -= clientBill.getTotalValue();
					clientBill.setTotalValue(0);					
				}else {
					clientBill.setTotalValue(clientBill.getTotalValue() - cash);
					cash = 0;
				}
				clientBillRepository.save(clientBill);
			}
		}
	}
}
