package com.hardisgroup.api.controllerTests;

import com.hardisgroup.api.controllers.FichierController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FichierControllerIT {

    // Injection du composant à tester
    @Autowired
    private FichierController controller;

    @Test
    public void contextLoads() {
    }

    /**
     * TI. Nécessite l'injection de dépendances (le composant testé est marqué par "@Autowired"): le test doit donc
     * avoir accès au contexte Spring, via l'annotation "@SpringBootTest".
     */
    @Test
    public void testerApplicationIT() {
        String expected = "Hello World!";
        String result = this.controller.testerApplication();
        assertEquals(expected, result);
    }
}
