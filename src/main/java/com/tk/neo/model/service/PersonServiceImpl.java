package com.tk.neo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tk.neo.model.dto.PersonDTO;
import com.tk.neo.model.entity.Person;
import com.tk.neo.model.service.dao.PersonDAO;
import com.tk.neo.model.service.interfaccia.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	private final PersonDAO personDAO;
	
	@Autowired
	public PersonServiceImpl(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public List<PersonDTO> getAllPeople() {
		return personDAO.getAllPeople();
	}

	@Override
	public PersonDTO getPerson(long id) {
		Person person = personDAO.findPerson(id);
		return PersonDTO.toDto(person);
	}

	@Override
	public void savePerson(PersonDTO personDTO) {
		personDAO.savePerson(personDTO);
	}

	@Override
	public void removePerson(long id) {
		Person person = personDAO.findPerson(id);
		person.getBooks().stream().forEach(e -> e.getPerson().removeBook(e));
		personDAO.removePerson(person);
	}

	@Override
	public void updatePerson(long id, PersonDTO personDTO) {
		personDAO.updatePerson(id, personDTO);
	}
	

}
