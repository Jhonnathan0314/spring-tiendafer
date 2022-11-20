/**
 * 
 */
package com.spring.tiendafer.controllers;

import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.spring.tiendafer.models.Section;

/**
 * @author Jonatan
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SectionControllerTest {
	
	SectionController sectionController;
	
	@Autowired
	private TestRestTemplate client;
	
//	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
//		sectionController = mock(SectionController.class);
	}
	
	@Test
	@Order(1)
	void testFindAll() {
		ResponseEntity<Section[]> response = client.getForEntity("/section", Section[].class);
		List<Section> sections = Arrays.asList(response.getBody());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		
		assertFalse(sections.isEmpty());
	}
	
	@Test
	@Order(2)
	void testFindById() {
		ResponseEntity<Section> response = client.getForEntity("/section/4", Section.class);
		Section actual = response.getBody();
		
		Section expected = new Section("FRUTAS Y VERDURAS");
		expected.setIdSection(4);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		
		assertEquals(expected, actual, "ERROR AL BUSCAR POR ID");
	}
	
	@Test
	@Order(3)
	void testCreate() {
		Section section = new Section("JUNIT");
		ResponseEntity<Section> response = client.postForEntity("/section", section, Section.class);
		Section actual = response.getBody();
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "CODIGO DIFERENTE: " + response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		
		System.out.println("--------------ID: " + actual.getIdSection());
		assertEquals(section.getName(), actual.getName(), "ERROR AL CREAR");
	}
	
	@Test
	@Order(4)
	void testUpdate() {
		int idSection = 22;
		Section section = new Section("NEW JUNIT");
		client.put("/section/" + idSection, section);
		
		ResponseEntity<Section> response = client.getForEntity("/section/" + idSection, Section.class);
		Section actual = response.getBody();
		
		Section expected = new Section("NEW JUNIT");
		expected.setIdSection(idSection);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		
		assertEquals(expected, actual, "ERROR AL ACTUALIZAR");
	}
	
	@Test
	@Order(5)
	void deleteTest() {
		int idSection = 21;
		client.delete("/section/" + idSection);
		
		ResponseEntity<Section> response = client.getForEntity("/section/" + idSection, Section.class);
		Section actual = response.getBody();
		
		Section expected = new Section("NEW JUNIT");
		expected.setIdSection(idSection);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertEquals(null, actual, "ERROR AL ELIMINAR");
	}
	
//	/**
//	 * Test method for {@link com.spring.tiendafer.controllers.SectionController#findAll()}.
//	 */
//	@Test
//	void testFindAll() {
//		List<Section> sections = Arrays.asList(new Section("ASEO"), new Section("MEDICAMENTOS"), new Section("BEBIDAS"));
//		for(int i=0; i<sections.size(); i++) {
//			sections.get(i).setIdSection(i+1);
//		}
//		when(sectionController.findAll()).thenReturn(sections);
//		List<Section> actual = sectionController.findAll();
//		assertArrayEquals(sections.toArray(), actual.toArray());
//	}
//	
//
//	/**
//	 * Test method for {@link com.spring.tiendafer.controllers.SectionController#findById(int)}.
//	 */
//	@Test
//	void testFindById() {
//		Section sectionOne = new Section("BEBIDAS");
//		sectionOne.setIdSection(1);
//		Section sectionTwo = new Section("COMIDA");
//		sectionTwo.setIdSection(2);
//		when(sectionController.findById(1)).thenReturn(sectionOne);
//		when(sectionController.findById(2)).thenReturn(sectionTwo);
//		Section actual = sectionController.findById(2);
//		assertEquals(sectionTwo, actual, "Seccion diferente!");
//	}
//
//	/**
//	 * Test method for {@link com.spring.tiendafer.controllers.SectionController#create(com.spring.tiendafer.models.Section)}.
//	 */
//	@Test
//	void testCreate() {
//		Section sectionOne = new Section("BEBIDAS");
//		sectionOne.setIdSection(1);
//		Section sectionTwo = new Section("COMIDA");
//		sectionTwo.setIdSection(2);
//		when(sectionController.create(sectionOne)).thenReturn(sectionOne);
//		when(sectionController.create(sectionTwo)).thenReturn(sectionTwo);
//		Section actual = new Section("BEBIDAS");
//		actual.setIdSection(1);
//		sectionController.create(actual);
//		assertEquals(sectionOne, actual, "Fallo al crear la seccion!");
//	}
//
//	/**
//	 * Test method for {@link com.spring.tiendafer.controllers.SectionController#update(int, com.spring.tiendafer.models.Section)}.
//	 */
//	@Test
//	void testUpdate() {
//		Section sectionOne = new Section("BEBIDA");
//		sectionOne.setIdSection(1);
//		Section sectionTwo = new Section("COMIDA");
//		sectionTwo.setIdSection(2);
//		when(sectionController.update(1, sectionOne)).thenReturn(sectionOne);
//		when(sectionController.update(2, sectionTwo)).thenReturn(sectionTwo);
//		Section actual = new Section("BEBIDAS");
//		actual.setIdSection(1);
//		sectionController.update(actual.getIdSection(), actual);
//		assertEquals(sectionOne.getIdSection(), actual.getIdSection(), "Fallo al actualizar la seccion!");
//	}
//
//	/**
//	 * Test method for {@link com.spring.tiendafer.controllers.SectionController#delete(int)}.
//	 */
//	@Test
//	void testDelete() {
//		List<Section> sections = Arrays.asList(new Section("ASEO"), new Section("MEDICAMENTOS"), new Section("BEBIDAS"));
//		for(int i=0; i<sections.size(); i++) {
//			sections.get(i).setIdSection(i+1);
//		}
//		when(sectionController.delete(1)).thenReturn(true);
//		when(sectionController.delete(2)).thenReturn(true);
//		when(sectionController.delete(3)).thenReturn(true);
//		assertEquals(true, sectionController.delete(3), "No existe la seccion");
//	}

}
