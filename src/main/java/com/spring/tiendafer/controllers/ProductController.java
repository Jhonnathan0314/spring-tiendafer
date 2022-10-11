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

import com.spring.tiendafer.models.Product;
import com.spring.tiendafer.models.Section;
import com.spring.tiendafer.repositories.ProductRepository;
import com.spring.tiendafer.repositories.SectionRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
public class ProductController {
	//Declaracion de variables
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SectionRepository sectionRepository;

	//Metodos propios
	@GetMapping("")
	public List<Product> findAll(){
		return productRepository.findAll();
	}

	@GetMapping("{id}")
	public Product findById(@PathVariable int id) {
		Product product = productRepository.findById(id).orElse(null);
		if(product != null) {
			return product;
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/section/{id_section}")
	public Product create(@RequestBody Product product, @PathVariable int id_section) {
		Section section = sectionRepository.findById(id_section).orElse(null);
		if(product != null && section != null) {
			product.setSection(section);
			return productRepository.save(product);
		}
		return null;
	}

	@PutMapping("/{id}/section/{id_section}")
	public Product update(@PathVariable int id, @RequestBody Product newProduct, @PathVariable int id_section) {
		Product product = productRepository.findById(id).orElse(null);
		Section section = sectionRepository.findById(id_section).orElse(null);
		if(product != null && newProduct != null && section != null) {
			product.setName(newProduct.getName());
			product.setQuantityAvailable(newProduct.getQuantityAvailable());
			product.setSaleValue(newProduct.getSaleValue());
			product.setSection(section);
			return productRepository.save(product);
		}
		return null;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		Product product = productRepository.findById(id).orElse(null);
		if(product != null) {
			productRepository.deleteById(id);
		}
	}
}
