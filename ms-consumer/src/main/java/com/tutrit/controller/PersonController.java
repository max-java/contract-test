package com.tutrit.controller;

import com.tutrit.bean.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonController {
  RestTemplate restTemplate;

  @RequestMapping("/message/{personId}")
  String getMessage(@PathVariable("personId") Long personId) {
    Person person = this.restTemplate.getForObject("http://localhost:8071/person/{personId}", Person.class, personId);
    return "Hello " + person.getName();
  }
}
