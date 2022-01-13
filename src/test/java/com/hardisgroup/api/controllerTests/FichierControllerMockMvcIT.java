package com.hardisgroup.api.controllerTests;

import com.hardisgroup.api.config.RestConfigTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// https://blog.zenika.com/2016/11/25/spring-mvc-test-dans-un-contexte-securise/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RestConfigTest.class})
@SpringBootTest // Facultatif: Plus d'infos dans la console
public class FichierControllerMockMvcIT {

    // Injecter l'instance de WebApplicationContext
    @Autowired
    private WebApplicationContext webApplicationContext;

    // Sauvegarder l'objet mockMvc
    private MockMvc mockMvc;

    // Initialiser et builder le mockMVC, via la méthode ".webAppContextSetup()"
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void simpleTest() throws Exception {
        // Lancer une requête Rest de type GET pour l'url '/api/fichiers/tester'
        mockMvc.perform(get("/api/fichiers/tester"))
                //Assert le statut de la réponse http est égal a OK.
                .andExpect(status().isOk())
                //Assert l’existence d'une réponse json.
                .andExpect(jsonPath("$").exists())
                //Afficher la réponse.
                .andDo(print());
    }

    /**
     @Test public void simpleTestZenika() throws Exception {
     //Lancer une requête Rest de type GET pour l'url '/data'
     mockMvc.perform(get("/data"))
     //Assert le statut de la réponse http est égal a OK.
     .andExpect(status().isOk())
     //Assert le type de contenue de réponse.
     .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
     //Assert l’existence d'une réponse json.
     .andExpect(jsonPath("$").exists())
     //Assert l’existence d'un attribut 'java-version' dans la réponse json.
     .andExpect(jsonPath("$.java-version").exists())
     //Assert le type de l'attribut 'java-version'
     .andExpect(jsonPath("$.java-version").isString())
     //Assert la valeur de l'attribut 'java-version' dans la réponse json.
     .andExpect(jsonPath("$.java-version").value("8.24"))
     //Afficher la réponse.
     .andDo(print());
     }
     */

}
