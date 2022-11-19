/**
 * 
 */
package com.spring.tiendafer.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.tiendafer.models.ClientBill;

/**
 * @author Jonatan
 *
 */
public interface ClientBillRepository extends JpaRepository<ClientBill, Integer> {
	 @Query(value = "select * from client_bill as CB join client as C on CB.id_client = C.id_client and C.id_client = ?1", nativeQuery = true)
	 List<ClientBill> findByClient(BigInteger idClient);
}
