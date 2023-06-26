package de.casestudy.casestudydb;

import de.casestudy.casestudydb.exceptions.XmlFileNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TrainControllerIntegrationTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getShouldReturnCorrectSectionsForExistingParameters() throws Exception {
        mockMvc.perform(get("/station/FF/train/2310/waggon/10"))
                .andExpect(status().is(200))
                .andExpect(content().json("""
                            {
                                "sections": [
                                    "B",
                                    "B",
                                    "B",
                                    "B, C",
                                    "B",
                                    "C, D"
                                ]
                            }
                        """));
    }
    @Test
    void getShouldReturnEmptyListForNotExistingTrainNumber() throws Exception {
        mockMvc.perform(get("/station/FF/train/9999/waggon/10"))
                .andExpect(status().is(200))
                .andExpect(content().json("""
                            {
                                "sections": []
                            }
                        """));
    }

    @Test
    void getShouldReturnEmptyListForNotExistingWaggonNumber() throws Exception {
        mockMvc.perform(get("/station/FF/train/2310/waggon/99"))
                .andExpect(status().is(200))
                .andExpect(content().json("""
                            {
                                "sections": []
                            }
                        """));
    }

    @Test
    void getShouldReturnExceptionForNotExistingRil100() throws Exception {
        mockMvc.perform(get("/station/ABC/train/2310/waggon/10"))
                .andExpect(status().is(404))
                .andExpect((result -> assertThat(result.getResolvedException())
                        .isInstanceOf(XmlFileNotFoundException.class)));

    }
}
