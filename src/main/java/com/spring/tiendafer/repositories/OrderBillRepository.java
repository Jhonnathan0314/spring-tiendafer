/**
 * 
 */
package com.spring.tiendafer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tiendafer.models.OrderBill;

/**
 * @author Jonatan
 *
 */
public interface OrderBillRepository extends JpaRepository<OrderBill, Integer> {

}
