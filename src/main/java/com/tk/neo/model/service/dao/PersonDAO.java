package com.tk.neo.model.service.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tk.neo.model.dto.PersonDTO;
import com.tk.neo.model.entity.Person;

@Component
public class PersonDAO {
	private final SessionFactory sessionFactory;
	
	@Autowired
	public PersonDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<PersonDTO> getAllPeople() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Person";
		List<Person> people = session.createQuery(hql, Person.class).getResultList();
		return people.stream()
				.map(PersonDTO::toDto)
				.collect(Collectors.toList());
	}

	public Person findPerson(long id) {
		Session session = sessionFactory.getCurrentSession();
		Person person = session.get(Person.class, id);
		if (person == null) {
			throw new RuntimeException("Such person does not exist");
		}
		return person;
	}

	public void savePerson(PersonDTO personDTO) {
		Person person = PersonDTO.toEntity(personDTO);
		Session session = sessionFactory.getCurrentSession();
		session.persist(person);		
	}

	public void removePerson(Person person) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(person);
	}

	public void updatePerson(long id, PersonDTO personDTO) {
		Person person = findPerson(id);
		person.setDateOfBirht(personDTO.dateOfBirht);
		person.setName(personDTO.name);
	}

	
}
