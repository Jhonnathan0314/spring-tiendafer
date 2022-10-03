/**
 * 
 */
package com.spring.tiendafer.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tiendafer.models.Supplier;

/**
 * @author Jonatan
 *
 */
public interface SupplierRepository extends JpaRepository<Supplier, BigInteger> {

}
