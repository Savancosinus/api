package com.hardisgroup.api.controllers;

import com.hardisgroup.api.config.SwaggerConfig;
import com.hardisgroup.api.enums.FormatFichierEnSortie;
import com.hardisgroup.api.exceptions.IOExceptionApi;
import com.hardisgroup.api.models.ExceptionResponse;
import com.hardisgroup.api.models.FichierConverti;
import com.hardisgroup.api.services.FichierService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller ayant trait aux fichiers.
 */
@RestController
@Slf4j
@RequestMapping("/api/fichiers")
@Api(tags = {SwaggerConfig.TAG_FICHIER_CONTROLLER})
public class FichierController {

    private final FichierService fichierService;

    @Autowired
    public FichierController(FichierService fichierService) {
        this.fichierService = fichierService;
    }

    /**
     * Méthode permettant de traiter un fichier. Les fichiers traitées contiennent 0 à N lignes à convertir
     * en autant d'objet "Reference" (en cas de succès) ou "Error" (si la ligne contient des données en erreur).
     *
     * @param urlEntrante: Url du fichier à traiter (ex: "C:/Users/M/Desktop/mon-fichier0.txt").
     * @param format:      Format attendu en sortie (XML, JSON).
     * @param urlSortante: Url du fichier une fois traité.
     */
    @GetMapping("/traiter/{format}")
    public FichierConverti traiterFichier(
            @PathVariable FormatFichierEnSortie format,
            // Les URL ne peuvent être passées sous forme de "PathVariable"!
            @RequestParam String urlEntrante,
            @RequestParam String urlSortante) {
        return this.fichierService.convertirFichierEntrant(urlEntrante, format, urlSortante);
    }

    @GetMapping("/tester")
    public String testerApplication() {
        return this.fichierService.testerApplication();
    }

    /**
     * Méthode permettant de traiter les exceptions de type "IOExceptionApi".
     *
     * @param ex Exception capturée par la méthode.
     * @return Un objet "ExceptionResponse".
     */
    @ExceptionHandler(IOExceptionApi.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse traiterIOExceptionApi(IOExceptionApi ex) {
        // Le logger fournit un message si:
        // 1) L'exception custom dispose d'un constructeur avec "message" (voir constructeur avec "super(message)").
        // 2) Lors de la levée d'exception, le constructeur avec "message" est appelé.
        // 3) Si le message est "null": pas de logs.
        log.error("FichierController: " + ex.getMessage());
        return new ExceptionResponse(ex.getCode(), ex.getDetails());
    }


}
