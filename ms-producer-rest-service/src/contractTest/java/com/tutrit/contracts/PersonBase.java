package com.tutrit.contracts;

//import com.tutrit.producer.ProducerApplication;
//import com.tutrit.producer.bean.Person;
//import com.tutrit.producer.controller.PersonRestController;
//import com.tutrit.producer.service.PersonService;

import com.tutrit.msproducerrestservice.MsProducerRestServiceApplication;
import com.tutrit.msproducerrestservice.bean.Person;
import com.tutrit.msproducerrestservice.controller.PersonRestController;
import com.tutrit.msproducerrestservice.service.PersonService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/** The name for this class is depends on location of contracts/../*.groovy contracts and
 * value of
 * i.e., contract is set in contracts/person/find_person_by_id.groovy, what will be evaluated to
 * PersonBase (by name of /person/ folder), and package where it should be located is
 * sets by packageWithBaseClasses = "com.tutrit.contracts" value. That gives
 * com.tutrit.contracts.PersonBase
 */

@SpringBootTest(classes = MsProducerRestServiceApplication.class)
public class PersonBase {
  @Autowired
  PersonRestController personRestController;

  @MockBean
  PersonService personService;

  @BeforeEach
  public void setup() {
    RestAssuredMockMvc.standaloneSetup(personRestController);

    Mockito.when(personService.findPersonById(1L))
        .thenReturn(new Person(1L, "foo", "bee"));
  }
}
