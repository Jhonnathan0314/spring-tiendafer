/**
 * 
 */
package com.spring.tiendafer.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tiendafer.models.Client;

/**
 * @author Jonatan
 *
 */
public interface ClientRepository extends JpaRepository<Client, BigInteger> {

}
