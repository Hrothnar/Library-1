package com.tk.neo.model.service.dao;

import java.util.List;

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

	public List<Person> getAllPeople() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Person";
		return session.createQuery(hql, Person.class).getResultList();
	}

	public Person getPerson(long id) {
		Session session = sessionFactory.getCurrentSession();
		Person person = session.get(Person.class, id);
		if (person == null) {
			throw new RuntimeException("Such person does not exist");
		}
		return person;
	}

	public void savePerson(PersonDTO personDTO) {
		Session session = sessionFactory.getCurrentSession();
		Person person = new Person();
		person.setName(personDTO.name);
		person.setDateOfBirth(personDTO.dateOfBirth);
		session.persist(person);		
	}

	public void removePerson(long id) {
		Session session = sessionFactory.getCurrentSession();
		Person person = getPerson(id);
		person.getBooks().stream()
						.forEach(e -> e.setPerson(null));
		session.remove(person);
	}

	
}
