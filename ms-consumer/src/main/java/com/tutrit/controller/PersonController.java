package com.tutrit.controller;

import com.tutrit.client.MsProducerClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonController {

  MsProducerClient producerClient;

  @RequestMapping("/message/{personId}")
  String getMessage(@PathVariable("personId") Long personId) {
    return "Hello " + producerClient.getPersonName(personId);
  }
}
