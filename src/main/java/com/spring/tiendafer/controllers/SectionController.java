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

import com.spring.tiendafer.models.Section;
import com.spring.tiendafer.repositories.SectionRepository;

/**
 * @author Jonatan
 *
 */
@RestController
@RequestMapping(value = "/section")
@CrossOrigin("*")
public class SectionController {
	//Declaracion de variables
	@Autowired
	private SectionRepository sectionRepository;
	
	//Metodos propios
		@GetMapping("")
		public List<Section> findAll(){
			return sectionRepository.findAll();
		}
		
		@GetMapping("{id}")
		public Section findById(@PathVariable int id) {
			Section section = sectionRepository.findById(id).orElse(null);
			if(section != null) {
				return section;
			}
			return null;
		}
		
		@ResponseStatus(code = HttpStatus.CREATED)
		@PostMapping("")
		public Section create(@RequestBody Section section) {
			if(section != null) {
				return sectionRepository.save(section);
			}
			return null;
		}
		
		@PutMapping("{id}")
		public Section update(@PathVariable int id, @RequestBody Section newSection) {
			Section section = sectionRepository.findById(id).orElse(null);
			if(section != null && newSection != null) {
				section.setName(newSection.getName());
				return sectionRepository.save(section);
			}
			return null;
		}
		
		@ResponseStatus(code = HttpStatus.NO_CONTENT)
		@DeleteMapping("{id}")
		public void delete(@PathVariable int id) {
			Section section = sectionRepository.findById(id).orElse(null);
			if(section != null) {
				sectionRepository.deleteById(id);
			}
		}
}
