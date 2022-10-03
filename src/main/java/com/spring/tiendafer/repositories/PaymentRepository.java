/**
 * 
 */
package com.spring.tiendafer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tiendafer.models.Payment;

/**
 * @author Jonatan
 *
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
