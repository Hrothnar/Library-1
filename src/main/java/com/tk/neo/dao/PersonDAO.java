package com.tk.neo.dao;


import com.tk.neo.util.PersonMapper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tk.neo.dao.repository.PersonRepository;
import com.tk.neo.model.Person;

@Component
public class PersonDAO implements PersonRepository {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void save(Person person) {
		String sql = "INSERT INTO people(name, birth_year) VALUES (?, ?);";
		jdbcTemplate.update(sql, person.getName(), person.getBirthYear());
	}
	
	public void update(long id, Person person) {
		String sql = "UPDATE people SET name = ?, birth_year = ? WHERE id = ?;";
		jdbcTemplate.update(sql, person.getName(), person.getBirthYear(), id);
	}
	
	public void remove(long id) {
		String sql = "DELETE FROM people WHERE id = ?;";
		jdbcTemplate.update(sql, id);
	}
	
	public List<Person> getAll() {
		String sql = "SELECT * FROM people;";
		return jdbcTemplate.query(sql, new PersonMapper());
	}

	public Person getById(long id) {
		String sql = "SELECT * FROM people WHERE id = ?;";
		Optional<Person> optional = jdbcTemplate.query(sql, new PersonMapper(), id).stream().findAny();
		if (optional.isPresent()) {
			return optional.get();
		}
		return new Person();
	}
	
	public boolean isExist(Person person) {
		String sql = "SELECT * FROM people WHERE name = ? AND birth_year = ?;";
		Optional<Person> optional = jdbcTemplate.query(sql, new PersonMapper(), person.getName(), person.getBirthYear()).stream().findAny();
		return optional.isPresent();	
	}

}
