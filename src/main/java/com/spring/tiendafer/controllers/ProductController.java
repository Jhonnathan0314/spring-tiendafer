/**
 * 
 */
package com.spring.tiendafer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	/**
	 * @return All Products existing in DB
	 */
	@GetMapping("")
	public List<Product> findAll(){
		return productRepository.findAll();
	}

	/**
	 * @param id => Product id that you want to find, it comes from URL
	 * @return Product with id received
	 */
	@GetMapping("{id}")
	public Product findById(@PathVariable int id) {
		Product product = productRepository.findById(id).orElse(null);
		if(product != null) {
			return product;
		}
		return null;
	}

	/**
	 * @param product => Product Object that contains the product name, quantity available and sale value
	 * @param idSection => Section id that you want to set in Product, it comes from URL
	 * @return Product created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/section/{idSection}")
	public Product create(@RequestBody Product product, @PathVariable int idSection) {
		Section section = sectionRepository.findById(idSection).orElse(null);
		if(product != null && section != null) {
			product.setSection(section);
			return productRepository.save(product);
		}
		return null;
	}

	/**
	 * @param id => Product id that you want to update, it comes from URL
	 * @param newProduct => Product Object that contains the new product name, quantity available and sale value
	 * @param idSection => Section id that you want to update in Product, it comes from URL
	 * @return Product updated
	 */
	@PutMapping("/{id}/section/{idSection}")
	public Product update(@PathVariable int id, @RequestBody Product newProduct, @PathVariable int idSection) {
		Product product = productRepository.findById(id).orElse(null);
		Section section = sectionRepository.findById(idSection).orElse(null);
		if(product != null && newProduct != null && section != null) {
			product.setName(newProduct.getName());
			product.setQuantityAvailable(newProduct.getQuantityAvailable());
			product.setSaleValue(newProduct.getSaleValue());
			product.setSection(section);
			return productRepository.save(product);
		}
		return null;
	}

	/**
	 * @param id => Product id that you want to delete, it comes from URL
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		var headers = new HttpHeaders();
		headers.add("Responded", "ProductController");
		String body = "Producto no encontrado!";
		Product product = productRepository.findById(id).orElse(null);
		if(product != null) {
			productRepository.deleteById(id);
			body = "Producto eliminado!";
		}
		return ResponseEntity.accepted().headers(headers).body(body);
	}
}
