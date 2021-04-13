package de.iplytics.codingchallenge_backend_webapp.patents;

import com.google.gson.Gson;
import de.iplytics.codingchallenge_backend_webapp.controller.PatentController;
import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.service.PatentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatentController.class)
class PatentControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatentServiceImpl patentService;

    @Test
    @Rollback
    @DisplayName("Test should pass, add a new patent find it then rollback changes added")
    @Order(1)
    public void requestPatent_presentInRepo_returnsPatentWithCorrectTitle() throws Exception {
        Patent mockPatent = Patent.builder()
                .publicationDate(LocalDate.of(2019,1,1))
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();

        PatentResponse patentResponse= new PatentResponse(mockPatent);

        given(patentService.getSinglePatent(any())).willReturn(patentResponse);

        mvc.perform(get("/patents/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is("Method of making cheese")));
    }

    @Test
    @DisplayName("Test should fail, search for a patent which is not in the database and throw an error")
    @Order(2)
    public void requestPatent_notPresentInRepo_returns404notFound() throws Exception {
        given(patentService.getSinglePatent("DE1234A1"))
                .willThrow(new ItemNotFoundException("Patent Not Found"));

        mvc.perform(get("/patents/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    @Rollback
    @DisplayName("Test should pass, add a multiple patents List and rollback changes added")
    @Order(3)
    public void requestPatents_presentInRepo_returnsPatentList() throws Exception{
        Patent mockPatent = Patent.builder()
                .publicationDate(LocalDate.of(2019,1,1))
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();
        Patent mockPatent1 = Patent.builder()
                .publicationDate(LocalDate.of(2019,1,1))
                .publicationNumber("DE2344A1")
                .title("Method of making wine")
                .build();

        PatentResponse patentResponse= new PatentResponse(mockPatent);

        PatentResponse patentResponse1= new PatentResponse(mockPatent1);
        given(patentService.findAll())
                .willReturn(Stream.of(patentResponse,patentResponse1).collect(Collectors.toList()));

        mvc.perform(get("/patents")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test should pass, returns empty list of patents")
    @Order(4)
    public void requestPatents_notPresentInRepo_returnsEmpty() throws Exception{
        Stream.empty().collect(Collectors.toList());
        given(patentService.findAll()).willReturn(new ArrayList<>());

        mvc.perform(get("/patents")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test should pass, add a single patent")
    @Order(5)
    public void addPatent_notPresentInRepo() throws Exception{
        Patent mockPatent = Patent.builder()
                .publicationDate(LocalDate.of(2019,1,1))
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();

        PatentRequest patentRequest = new PatentRequest("DE1234A1"
                                                        ,"01/01/2019"
                                                        ,"Method of making cheese");
        PatentResponse patentResponse= new PatentResponse(mockPatent);

        when(patentService.save(patentRequest)).thenReturn(patentResponse);

        Gson gson = new Gson();
        String patentJson = gson.toJson(patentRequest);
        System.out.println(patentJson);

        mvc.perform(post("/patents")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("Test should pass, update a single patent, Rollbacks changes")
    @Order(5)
    @Rollback
    public void updatePatent_presentInRepo() throws Exception{
        Patent mockPatent = Patent.builder()
                .publicationDate(LocalDate.of(2019,1,1))
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();


        PatentRequest patentRequest = new PatentRequest("DE1234A1"
                ,"01/01/2019"
                ,"Method of making cheese");
        PatentResponse patentResponse= new PatentResponse(mockPatent);

        when(patentService.save(patentRequest)).thenReturn(patentResponse);


        Gson gson = new Gson();
        String patentJson = gson.toJson(patentRequest);

        mvc.perform(put("/patents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patentJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

    }
    @Test
    @DisplayName("Test should fail, check for all input mistakes in Patent object")
    @Order(5)
    public void addOrUpdatePatent_checkInputMistakes() throws Exception{

    }

}