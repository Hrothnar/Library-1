package com.tk.neo.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tk.neo.model.Person;

public class PersonMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getLong("id"));
		person.setName(rs.getString("name"));
		person.setBirthYear(rs.getInt("birth_year"));
		return person;	
	}

}
