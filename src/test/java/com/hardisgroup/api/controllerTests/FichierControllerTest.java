package com.hardisgroup.api.controllerTests;

import com.hardisgroup.api.controllers.FichierController;
import com.hardisgroup.api.enums.FormatFichierEnSortie;
import com.hardisgroup.api.services.FichierService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// @RunWith(SpringRunner.class) // Inutile (UNKNOWN)
// @SpringBootTest // Ne pas lancer le contexte Spring pour un TU!
// https://spring.io/guides/gs/testing-web/
@WebMvcTest(controllers = FichierController.class)
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
     * Tester l'url du controller. Le test marque "fichierService" avec "@Mock": sans directive spéciale,
     * le mock renverra tjrs 200 ("Nothing to write: null body"). Ce test ne nécessite pas de contexte Spring, mais il
     * ne teste que l'url du controller et les paramètres qui lui sont associés, pas le service.
     * Le fichier de test est présent sous "src/test/resources/abc.txt".
     */
    @Test
    public void traiterFichierTestUrl_OK() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(fichierController).build();

        // MockMvc: Valorisation des PathVariable/RequestParam
        // - Le nom du RequestParam DOIT correspondre exactement avec celui déclaré dans le controller.
        // - Le nom du PathVariable n'est pas obligatoirement celui déclaré dans le controller.
        mockMvc.perform(get("/api/fichiers/traiter/{format}", FormatFichierEnSortie.JSON.toString())
                        .param("urlEntrante", "src/test/resources/abc.txt")
                        .param("urlSortante", "src/test/resources/abc.txt"))
                .andExpect(status().isOk());
    }

}
