package com.tk.neo.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tk.neo.model.dto.PersonDTO;
import com.tk.neo.model.entity.Person;
import com.tk.neo.model.mapper.PersonMapper;
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
	 List<Person> people = personDAO.getAllPeople();
		return people.stream()
					.map(PersonMapper::toLazy)
					.collect(Collectors.toList());
	}

	@Override
	public PersonDTO getPerson(long id, boolean eager) {
		Person person = personDAO.getPerson(id);
		return eager ? PersonMapper.toEager(person) : PersonMapper.toLazy(person);
	}

	@Override
	public void savePerson(PersonDTO personDTO) {
		personDAO.savePerson(personDTO);
	}

	@Override
	public void removePerson(long id) {
		personDAO.removePerson(id);
	}

	@Override
	public void updatePerson(long id, PersonDTO personDTO) {
		Person person = personDAO.getPerson(id);
		person.setDateOfBirth(personDTO.dateOfBirth);
		person.setName(personDTO.name);
	}
	

}
