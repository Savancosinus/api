package com.hardisgroup.api.services;

import com.hardisgroup.api.enums.FormatFichierEnSortie;
import com.hardisgroup.api.exceptions.IOExceptionApi;
import com.hardisgroup.api.models.FichierConverti;

/**
 * Interface: "FichierService".
 */
public interface FichierService {

    FichierConverti convertirFichierEntrant(
            String urlEntrante, FormatFichierEnSortie formatFichierEnSortie, String urlSortante) throws IOExceptionApi;

    String testerApplication();
}
