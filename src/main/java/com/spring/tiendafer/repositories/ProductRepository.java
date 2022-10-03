/**
 * 
 */
package com.spring.tiendafer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tiendafer.models.Product;

/**
 * @author Jonatan
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
