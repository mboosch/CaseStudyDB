package de.casestudy.casestudydb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DBControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetTrackSection() throws Exception {

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
}
