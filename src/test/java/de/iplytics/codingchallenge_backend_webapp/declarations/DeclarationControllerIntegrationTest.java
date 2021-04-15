package de.iplytics.codingchallenge_backend_webapp.declarations;


import com.google.gson.Gson;
import de.iplytics.codingchallenge_backend_webapp.controller.DeclarationController;
import de.iplytics.codingchallenge_backend_webapp.dto.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.service.DeclarationServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeclarationController.class)
public class DeclarationControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DeclarationServiceImpl declarationService;

    @Test
    @Rollback
    @DisplayName("Test should pass, add a new Declaration find it then rollback changes added")
    @Order(1)
    public void requestDeclaration_presentInRepo_returnsDeclarationCorrect() throws Exception {


        PatentResponse patentResponse= PatentResponse.builder()
                .publicationDate("2029-12-15")
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        DeclarationResponse declarationResponse = DeclarationResponse.builder()
                .patentResponse(patentResponse)
                .standardResponse(standardResponse)
                .build();

        given(declarationService.getSingleDeclaration(any())).willReturn(declarationResponse);

        mvc.perform(get("/declarations/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Test should fail, search for a Declaration which is not in the database and throw an error")
    @Order(2)
    public void requestDeclaration_notPresentInRepo_returns404notFound() throws Exception {
        given(declarationService.getSingleDeclaration("DE1234A1"))
                .willThrow(new ItemNotFoundException("Standard Not Found"));

        mvc.perform(get("/declarations/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Rollback
    @DisplayName("Test should pass, add a multiple Declarations List and rollback changes added")
    @Order(3)
    public void requestDeclaration_presentInRepo_returnsPatentList() throws Exception{

        PatentResponse patentResponse= PatentResponse.builder()
                .publicationDate("2029-12-15")
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        DeclarationResponse declarationResponse = DeclarationResponse.builder()
                .patentResponse(patentResponse)
                .standardResponse(standardResponse)
                .build();

        given(declarationService.findAll())
                .willReturn(Stream.of(declarationResponse).collect(Collectors.toList()));

        mvc.perform(get("/declarations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test should pass, returns empty list of Declaration")
    @Order(4)
    public void requestDeclaration_notPresentInRepo_returnsEmpty() throws Exception{
        Stream.empty().collect(Collectors.toList());
        given(declarationService.findAll()).willReturn(new ArrayList<>());

        mvc.perform(get("/declarations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Test should pass, add a single Declaration")
    @Order(5)
    public void addDeclaration_notPresentInRepo() throws Exception{

        DeclarationRequest declarationRequest = new DeclarationRequest("UTC!12xx","DE1234A1"
                ,"ISO");

        PatentResponse patentResponse= PatentResponse.builder()
                .publicationDate("2029-12-15")
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        DeclarationResponse declarationResponse = DeclarationResponse.builder()
                .patentResponse(patentResponse)
                .standardResponse(standardResponse)
                .build();

        when(declarationService.save(declarationRequest)).thenReturn(declarationResponse);

        Gson gson = new Gson();
        String patentJson = gson.toJson(declarationRequest);

        mvc.perform(post("/declarations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test should pass, update a single Declaration, Rollbacks changes")
    @Order(6)
    @Rollback
    public void updateDeclaration_presentInRepo() throws Exception{


        DeclarationRequest declarationRequest = new DeclarationRequest("UTC!12xx","DE1234A1"
                ,"ISO");

        PatentResponse patentResponse= PatentResponse.builder()
                .publicationDate("2029-12-15")
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();

        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        DeclarationResponse declarationResponse = DeclarationResponse.builder()
                .patentResponse(patentResponse)
                .standardResponse(standardResponse)
                .build();


        when(declarationService.save(declarationRequest)).thenReturn(declarationResponse);


        Gson gson = new Gson();
        String patentJson = gson.toJson(declarationRequest);

        mvc.perform(put("/declarations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test should fail, check for invalid Standard in Declaration object")
    @Order(7)
    public void addOrUpdateDeclaration_checkInputMistakes_InvalidStandard() throws Exception{

        DeclarationRequest declarationRequest = new DeclarationRequest("UTC!12xx","DE1234A1"
                ,null);

        PatentResponse patentResponse= PatentResponse.builder()
                .publicationDate("2029-12-15")
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();


        DeclarationResponse declarationResponse = DeclarationResponse.builder()
                .patentResponse(patentResponse)
                .standardResponse(null)
                .build();

        when(declarationService.save(declarationRequest)).thenReturn(declarationResponse);
        given(declarationService.save(declarationRequest)).willThrow(new InvalidArgumentException("Invalid Argument"));


        Gson gson = new Gson();
        String patentJson = gson.toJson(declarationRequest);

        mvc.perform(post("/declarations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Test should fail, check for invalid Patent in Declarations object")
    @Order(8)
    public void addOrUpdateDeclaration_checkInputMistakes_InvalidPatent() throws Exception{

        DeclarationRequest declarationRequest = new DeclarationRequest("UTC!12xx",null
                ,"ISO");


        StandardResponse standardResponse= StandardResponse.builder()
                .description("ISO standard description")
                .standardId("DE1234A1")
                .name("ISO")
                .build();

        DeclarationResponse declarationResponse = DeclarationResponse.builder()
                .patentResponse(null)
                .standardResponse(standardResponse)
                .build();

        when(declarationService.save(declarationRequest)).thenReturn(declarationResponse);
        given(declarationService.save(declarationRequest)).willThrow(new InvalidArgumentException("Invalid Argument"));

        Gson gson = new Gson();
        String patentJson = gson.toJson(declarationRequest);

        mvc.perform(post("/declarations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());

    }


    @Test
    @DisplayName("Test should pass, deletes Declaration")
    @Order(9)
    public void deleteDeclaration_Success() throws Exception{
        ResponseMessage responseMessage = new ResponseMessage(200, "Standard Deleted with ID DE1234A1");

        given(declarationService.delete("DE1234A1")).willReturn(responseMessage);

        mvc.perform(delete("/declarations/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test should fail, tries to delete Declaration but does not find it")
    @Order(10)
    public void deleteDeclaration_Fail() throws Exception{
        given(declarationService.delete("12343"))
                .willThrow(new ItemNotFoundException("Standard Not Found"));

        mvc.perform(delete("/declarations/12343")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}
