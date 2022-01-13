package com.hardisgroup.api.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FichierControllerMockMvcIT2Ocr {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEmployees() throws Exception {
        // Lancer une requête Rest de type GET pour l'url '/api/fichiers/tester'
        mockMvc.perform(get("/api/fichiers/tester"))
                //Assert le statut de la réponse http est égal a OK.
                .andExpect(status().isOk())
                //Assert l’existence d'une réponse json.
                .andExpect(jsonPath("$").exists())
                //Afficher la réponse.
                .andDo(print());
    }
}
