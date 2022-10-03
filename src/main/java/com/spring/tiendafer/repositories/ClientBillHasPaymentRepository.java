/**
 * 
 */
package com.spring.tiendafer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tiendafer.models.ClientBillHasPayment;

/**
 * @author Jonatan
 *
 */
public interface ClientBillHasPaymentRepository extends JpaRepository<ClientBillHasPayment, Integer> {

}
