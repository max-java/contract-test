package com.tutrit.client;

import com.tutrit.bean.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MsProducerClient {

  RestTemplate restTemplate;

  public String getPersonName(@PathVariable("personId") Long personId) {
    Person person = this.restTemplate.getForObject("http://localhost:8071/person/{personId}",
        Person.class, personId);
    return person.getName();
  }
}
