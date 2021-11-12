package com.hardisgroup.api.controllerTests;

import com.hardisgroup.api.controllers.FichierController;
import com.hardisgroup.api.enums.FormatFichierEnSortie;
import com.hardisgroup.api.services.FichierService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FichierControllerTest {

    FichierController fichierController;

    @Mock
    FichierService fichierService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        // Initialisation du composant
        fichierController = new FichierController(fichierService);
    }

    /**
     * Tester la lecture de fichiers.
     * Le fichier est présent sous "src/test/resources/abc.txt".
     */
    @Test
    public void traiterFichierTestUrl_OK() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(fichierController).build();

        // MockMvc: Valorisation des RequestParam
        mockMvc.perform(get("/api/fichier/traiter")
                        .param("urlEntrante", "src/test/resources/abc.txt")
                        .param("formatFichierEnSortie", FormatFichierEnSortie.JSON.toString())
                        .param("urlSortante", "src/test/resources/abc.txt"))
                .andExpect(status().isOk());
    }

}
