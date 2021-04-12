package de.iplytics.codingchallenge_backend_webapp.patents;

import de.iplytics.codingchallenge_backend_webapp.controller.PatentController;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.service.PatentServiceImpl;
import org.junit.jupiter.api.DisplayName;
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

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void requestPatent_presentInRepo_returnsPatentWithCorrectTitle() throws Exception {
        Patent mockPatent = Patent.builder()
                .publicationDate(LocalDate.of(2019,1,1))
                .publicationNumber("DE1234A1")
                .title("Method of making cheese")
                .build();

        given(patentService.getSinglePatent(any())).willReturn(mockPatent);

        mvc.perform(get("/patents/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is("Method of making cheese")));
    }

    @Test
    public void requestPatent_notPresentInRepo_returns404notFound() throws Exception {
        given(patentService.getSinglePatent("DE1234A1"))
                .willThrow(new IllegalArgumentException());

        mvc.perform(get("/patents/DE1234A1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}