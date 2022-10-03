/**
 * 
 */
package com.spring.tiendafer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tiendafer.models.ClientBill;

/**
 * @author Jonatan
 *
 */
public interface ClientBillRepository extends JpaRepository<ClientBill, Integer> {

}
