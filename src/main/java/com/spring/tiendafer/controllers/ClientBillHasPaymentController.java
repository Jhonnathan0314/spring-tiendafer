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

import com.spring.tiendafer.models.ClientBill;
import com.spring.tiendafer.models.Payment;
import com.spring.tiendafer.models.ClientBillHasPayment;
import com.spring.tiendafer.repositories.ClientBillHasPaymentRepository;
import com.spring.tiendafer.repositories.ClientBillRepository;
import com.spring.tiendafer.repositories.PaymentRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/clientbillhaspayment")
@CrossOrigin("*")
public class ClientBillHasPaymentController {
	//Declaracion de variables
	@Autowired
	private ClientBillHasPaymentRepository clientBillHasPaymentRepository;
	@Autowired
	private ClientBillRepository clientBillRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	
	/**
	 * @return all ClientBillHasPayment existing in DB
	 */
	@GetMapping("")
	public List<ClientBillHasPayment> findAll(){
		return clientBillHasPaymentRepository.findAll();
	}

	/**
	 * @param id => ClientBillHasPayment id that you want to find, it comes from URL
	 * @return ClientBillHasPayment with id received
	 */
	@GetMapping("{id}")
	public ClientBillHasPayment findById(@PathVariable int id) {
		ClientBillHasPayment clientBillHasPayment = clientBillHasPaymentRepository.findById(id).orElse(null);
		if(clientBillHasPayment != null) {
			return clientBillHasPayment;
		}
		return null;
	}

	/**
	 * @param clientBillHasPayment => clientBillHasPayment Object that contains totalOutstandingBalance, balanceCanceled, newOutstandingBalance, changeCash
	 * @param idPayment => Payment id that you want to include in clientBillHasPayment, it comes from URL
	 * @param idClientBill => ClientBill id that you want to include in clientBillHasPayment, it comes from URL
	 * @return ClientBillHasPayment created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/payment/{idPayment}/clientbill/{idClientBill}")
	public ClientBillHasPayment create(@RequestBody ClientBillHasPayment clientBillHasPayment, 
			@PathVariable int idPayment, @PathVariable int idClientBill) {
		Payment payment = paymentRepository.findById(idPayment).orElse(null);
		ClientBill clientBill = clientBillRepository.findById(idClientBill).orElse(null);
		if(clientBillHasPayment != null && payment != null && clientBill != null) {
			clientBillHasPayment.setPayment(payment);
			clientBillHasPayment.setClientBill(clientBill);
			return clientBillHasPaymentRepository.save(clientBillHasPayment);
		}
		return null;
	}

	/**
	 * @param id => ClientBillHasPayment id that you want to update, it comes from URL
	 * @param newClientBillHasPayment => ClientBillHasPayment Object that contains the new quantity, unit value and total value (quantity * unit value)
	 * @param idPayment => Product id that you want to update in clientBillHasPayment, it comes from URL
	 * @param idClientBill => ClientBill id that you want to update in clientBillHasPayment, it comes from URL
	 * @return ClientBillHasPayment updated
	 */
	@PutMapping("/{id}/payment/{idPayment}/clientbill/{idClientBill}")
	public ClientBillHasPayment update(@PathVariable int id, @RequestBody ClientBillHasPayment newClientBillHasPayment,
			@PathVariable int idPayment, @PathVariable int idClientBill) {
		ClientBillHasPayment clientBillHasPayment = clientBillHasPaymentRepository.findById(id).orElse(null);
		Payment payment = paymentRepository.findById(idPayment).orElse(null);
		ClientBill clientBill = clientBillRepository.findById(idClientBill).orElse(null);
		if(newClientBillHasPayment != null && clientBillHasPayment != null && payment != null && clientBill != null) {
			clientBillHasPayment.setTotalOutstandingBalance(newClientBillHasPayment.getTotalOutstandingBalance());
			clientBillHasPayment.setBalanceCanceled(newClientBillHasPayment.getBalanceCanceled());
			clientBillHasPayment.setNewOutstandingBalance(newClientBillHasPayment.getNewOutstandingBalance());
			clientBillHasPayment.setChangeCash(newClientBillHasPayment.getChangeCash());
			clientBillHasPayment.setPayment(payment);
			clientBillHasPayment.setClientBill(clientBill);
			return clientBillHasPaymentRepository.save(clientBillHasPayment);
		}
		return null;
	}

	/**
	 * @param id => clientBillHasPayment id that you want to delete, it comes from URL
	 */
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		ClientBillHasPayment clientBillHasPayment = clientBillHasPaymentRepository.findById(id).orElse(null);
		if(clientBillHasPayment != null) {
			clientBillHasPaymentRepository.deleteById(id);
		}
	}
}
