package service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import model.Person;

@Service
public class PersonService {
	
	ArrayList<Person> persons = new ArrayList<>();
	
	public PersonService() {
		Person p1 = new Person();
		p1.setId("1");
		p1.setFname("First");
		p1.setLname("Person");
		p1.setAge(40);
		
		persons.add(p1);
		
		Person p2 = new Person();
		p2.setId("2");
		p2.setFname("Second");
		p2.setLname("Person");
		p2.setAge(45);
		
		persons.add(p2);	
		
	}
	
	public Person getPerson(String id) {
		for(Person p:persons) {
			if(p.getId().equals(id)) {
				return p;
			}
		}
		
		return null;
	}
	
	public ArrayList<Person> getAll(){
		return persons;
	}
	
	public Person addPerson(Person newPerson) {
		persons.add(newPerson);
		return newPerson;
	}
	
	public Person updatePerson(Person updatePerson) {
		for(Person p:persons) {
			if(p.getId().equals(updatePerson.getId())) {
				
				p.setFname(updatePerson.getFname());
				p.setLname(updatePerson.getLname());
				p.setAge(updatePerson.getAge());
				
				
				return p;
			}
		}
		
		return null;
	}
	
	public boolean deletePerson(String id) {
		for(Person p:persons) {
			if(p.getId().equals(id)) {
				
				persons.remove(p);
				
				return true;
				
				}
		}
		
		return false;
	}

}
