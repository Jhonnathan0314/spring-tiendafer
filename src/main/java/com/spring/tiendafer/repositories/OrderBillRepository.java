/**
 * 
 */
package com.spring.tiendafer.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.tiendafer.models.OrderBill;

/**
 * @author Jonatan
 *
 */
public interface OrderBillRepository extends JpaRepository<OrderBill, Integer> {
	@Query(value = "select * from order_bill as OB join supplier as S on OB.id_supplier = S.id_supplier and S.id_supplier = ?1", nativeQuery = true)
	 List<OrderBill> findBySupplier(BigInteger idSupplier);
}
