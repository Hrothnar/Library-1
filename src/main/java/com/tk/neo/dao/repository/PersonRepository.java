package com.tk.neo.dao.repository;

import java.util.List;

import com.tk.neo.model.Person;

public interface PersonRepository {
	void save(Person person);
	void update(long id, Person person);
	void remove(long id);
	List<Person> getAll();
	Person getById(long id);

}
