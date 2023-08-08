package com.tk.neo.model.service.interfaccia;

import java.util.List;

import com.tk.neo.model.dto.PersonDTO;

public interface PersonService {
	List<PersonDTO> getAllPeople();
	PersonDTO getPerson(long id, boolean eager);
	void savePerson(PersonDTO personDTO);
	void removePerson(long id);
	void updatePerson(long id, PersonDTO personDTO);

}
