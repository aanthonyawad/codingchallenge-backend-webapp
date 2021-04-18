package de.iplytics.codingchallenge_backend_webapp.standards;

import com.google.gson.Gson;
import de.iplytics.codingchallenge_backend_webapp.config.security.SecurityConfigurer;
import de.iplytics.codingchallenge_backend_webapp.controller.PatentController;
import de.iplytics.codingchallenge_backend_webapp.controller.StandardController;
import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.service.PatentServiceImpl;
import de.iplytics.codingchallenge_backend_webapp.service.StandardServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StandardController.class)
@ActiveProfiles(value = "test")
class StandardControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StandardServiceImpl standardService;

    @Test
    @Rollback
    @DisplayName("Test should pass, add a new standard find it then rollback changes added")
    @Order(1)
    public void requestStandard_presentInRepo_returnsStandardWithCorrectValues() throws Exception {

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        given(standardService.getSingleStandard(any())).willReturn(standardResponse);

        mvc.perform(get("/standards/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("ISO")))
                .andExpect(jsonPath("description", is("ISO standard description")))
                .andExpect(jsonPath("standardId", is("DE1234A1")));
    }


    @Test
    @DisplayName("Test should fail, search for a standard which is not in the database and throw an error")
    @Order(2)
    public void requestStandard_notPresentInRepo_returns404notFound() throws Exception {
        given(standardService.getSingleStandard("DE1234A1"))
                .willThrow(new ItemNotFoundException("Standard Not Found"));

        mvc.perform(get("/patents/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Rollback
    @DisplayName("Test should pass, add a multiple Standards List and rollback changes added")
    @Order(3)
    public void requestStandards_presentInRepo_returnsPatentList() throws Exception{

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        StandardResponse standardResponse1= StandardResponse.builder()
                .description("MIT standard description")
                .standardId("US1234A1")
                .name("MIT")
                .build();

        given(standardService.findAll())
                .willReturn(Stream.of(standardResponse,standardResponse1).collect(Collectors.toList()));

        mvc.perform(get("/standards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test should pass, returns empty list of Standards")
    @Order(4)
    public void requestStandard_notPresentInRepo_returnsEmpty() throws Exception{
        Stream.empty().collect(Collectors.toList());
        given(standardService.findAll()).willReturn(new ArrayList<>());

        mvc.perform(get("/standards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Test should pass, add a single Standard")
    @Order(5)
    public void addStandard_notPresentInRepo() throws Exception{

        StandardRequest standardRequest = new StandardRequest("DE1234A1"
                ,"ISO"
                ,"ISO standard description");

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        when(standardService.save(standardRequest)).thenReturn(standardResponse);

        Gson gson = new Gson();
        String patentJson = gson.toJson(standardRequest);

        mvc.perform(post("/standards")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test should pass, update a single Standard, Rollbacks changes")
    @Order(6)
    @Rollback
    public void updateStandard_presentInRepo() throws Exception{


        StandardRequest standardRequest = new StandardRequest("DE1234A1"
                ,"ISO"
                ,"ISO standard description");

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();


        when(standardService.save(standardRequest)).thenReturn(standardResponse);


        Gson gson = new Gson();
        String patentJson = gson.toJson(standardRequest);

        mvc.perform(put("/standards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test should fail, check for invalid id in Standard object")
    @Order(7)
    public void addOrUpdateStandard_checkInputMistakes_InvalidStandardId() throws Exception{

        StandardRequest standardRequest = new StandardRequest(null
                ,"ISO"
                ,"ISO standard description");

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId(null)
                .name("ISO")
                .build();

        when(standardService.save(standardRequest)).thenReturn(standardResponse);
        given(standardService.save(standardRequest)).willThrow(new InvalidArgumentException("Invalid Argument"));


        Gson gson = new Gson();
        String patentJson = gson.toJson(standardRequest);

        mvc.perform(post("/standards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Test should fail, check for invalid name in Standard object")
    @Order(8)
    public void addOrUpdateStandard_checkInputMistakes_InvalidName() throws Exception{

        StandardRequest standardRequest = new StandardRequest("DE1234A1"
                ,null
                ,"ISO standard description");

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name(null)
                .build();

        when(standardService.save(standardRequest)).thenReturn(standardResponse);
        given(standardService.save(standardRequest)).willThrow(new InvalidArgumentException("Invalid Argument"));

        Gson gson = new Gson();
        String patentJson = gson.toJson(standardRequest);

        mvc.perform(post("/standards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Test should fail, check for invalid description null case  in Standard object")
    @Order(9)
    public void addOrUpdateStandard_checkInputMistakes_InvalidDescription_null() throws Exception{

        StandardRequest standardRequest = new StandardRequest("DE1234A1"
                ,"ISO"
                ,null);

        StandardResponse standardResponse= StandardResponse.builder()
                .description(null)
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        when(standardService.save(standardRequest)).thenReturn(standardResponse);
        given(standardService.save(standardRequest)).willThrow(new InvalidArgumentException("Invalid Argument"));


        Gson gson = new Gson();
        String patentJson = gson.toJson(standardRequest);

        mvc.perform(post("/standards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Test should pass, deletes Standard")
    @Order(10)
    public void deleteStandard_Success() throws Exception{
        ResponseMessage responseMessage = new ResponseMessage(200, "Standard Deleted with ID DE1234A1");

        given(standardService.delete("DE1234A1")).willReturn(responseMessage);

        mvc.perform(delete("/standards/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test should fail, tries to delete Standard but does not find it")
    @Order(11)
    public void deleteStandard_Fail() throws Exception{
        given(standardService.delete("12343"))
                .willThrow(new ItemNotFoundException("Standard Not Found"));

        mvc.perform(delete("/standards/12343")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }



}
