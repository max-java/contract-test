package com.tutrit.msproducerrestservice.controller;

//import com.tutrit.producer.bean.Person;
//import com.tutrit.producer.service.PersonService;
import com.tutrit.msproducerrestservice.bean.Person;
import com.tutrit.msproducerrestservice.service.PersonService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonRestController {
	PersonService personService;

	@GetMapping("/person/{id}")
	public Person findPersonById(@PathVariable("id") Long id) {
		return personService.findPersonById(id);
	}
}
