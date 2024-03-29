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

import com.spring.tiendafer.models.Section;
import com.spring.tiendafer.repositories.SectionRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class SectionController {
	//Declaracion de variables
	@Autowired
	private SectionRepository sectionRepository;

	//Metodos propios
	/**
	 * @return All Sections existing in DB
	 */
	@GetMapping("sections")
	public List<Section> findAll(){
		return sectionRepository.findAll();
	}

	/**
	 * @param id => Section id that you want to find, it comes from URL
	 * @return Section with id received
	 */
	@GetMapping("section/{id}")
	public Section findById(@PathVariable int id) {
		Section section = sectionRepository.findById(id).orElse(null);
		if(section != null) {
			return section;
		}
		return null;
	}

	/**
	 * @param section => Section Object that contains the section name
	 * @return Section created
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("section")
	public Section create(@RequestBody Section section) {
		if(section != null) {
			return sectionRepository.save(section);
		}
		return null;
	}

	/**
	 * @param id => Section id that you want to update, it comes from URL
	 * @param newSection => Section Object that contains the new section name
	 * @return Section created
	 */
	@PutMapping("section/{id}")
	public Section update(@PathVariable int id, @RequestBody Section newSection) {
		Section section = sectionRepository.findById(id).orElse(null);
		if(section != null && newSection != null) {
			section.setName(newSection.getName());
			return sectionRepository.save(section);
		}
		return null;
	}

	/**
	 * @param id => Section id that you want to delete, it comes from URL
	 */
	@DeleteMapping("section/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		var headers = new HttpHeaders();
		headers.add("Responded", "ProductController");
		String body = "Seccion no encontrada!";
		Section section = sectionRepository.findById(id).orElse(null);
		if(section != null) {
			sectionRepository.deleteById(id);
			body = "Seccion eliminada!";
		}
		return ResponseEntity.accepted().headers(headers).body(body);
	}
}
