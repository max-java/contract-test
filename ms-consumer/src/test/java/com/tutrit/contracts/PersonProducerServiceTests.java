package com.tutrit.contracts;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tutrit.bean.Person;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = WebEnvironment.NONE) //enable this if no mockMvc in test.
@AutoConfigureStubRunner(
    ids = {"com.tutrit:ms-producer-rest-service:0.0.1-SNAPSHOT:stubs:8071"},
    stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class PersonProducerServiceTests {

  @Autowired
  private MockMvc mockMvc;

  /**
   * This verifies that current service could make request to producer with expected result
   */
  @Test
  public void get_person_from_service_contract() {
    // given:
    RestTemplate restTemplate = new RestTemplate();

    // when:
    ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity(
        "http://localhost:8071/person/1", Person.class);

    // then:
    BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
    BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
    BDDAssertions.then(personResponseEntity.getBody().getName()).isEqualTo("foo");
    BDDAssertions.then(personResponseEntity.getBody().getSurname()).isEqualTo("bee");
  }

  /**
   * This is more wide test. It verifies that current service controller is working, receiving
   * request and make call to the RestTemplate client, witch is stubbed with the contract.
   *
   * @throws Exception
   */
  @Test
  public void get_person_from_service_contract_mockMvc() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/message/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
