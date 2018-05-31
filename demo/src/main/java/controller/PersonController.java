package controller;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.Person;
import service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	PersonService ps;
	
	@GetMapping("/all")
	public ArrayList<Person> getAll(){
		return ps.getAll();
		
	}
	
	@GetMapping("{id}")
	public Person getPerson(@PathVariable("id") String id) {
		return ps.getPerson(id);
	}
	
	@PostMapping
	public ResponseEntity<Void> addPerson(@RequestBody Person newPerson){
		
			Person person = ps.addPerson(newPerson);
			
			if (person == null)
				return ResponseEntity.noContent().build();

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(person.getId()).toUri();

			return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping
	public ResponseEntity<Void> updatePerson(@RequestBody Person updatePerson){
		
		Person person = ps.updatePerson(updatePerson);
		
		if (person == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(person.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
 
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable("id") String id){
		if (ps.deletePerson(id))
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	
}
