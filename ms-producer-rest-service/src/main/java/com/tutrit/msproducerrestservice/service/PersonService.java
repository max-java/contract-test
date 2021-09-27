package com.tutrit.msproducerrestservice.service;

import com.tutrit.msproducerrestservice.bean.Person;
import java.util.Map;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonService {
	Map<Long, Person> personMap;
	
	{
		personMap = Map.of(
			1L, new Person(1L, "Richard", "Gere"),
			2L, new Person(2L, "Emma", "Choplin"),
			3L, new Person(3L, "Anna", "Carolina")
			);
	}
	
	public Person findPersonById(Long id) {
		return personMap.get(id);
	}
}
