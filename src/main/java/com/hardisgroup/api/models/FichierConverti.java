package com.hardisgroup.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO "FichierConverti".
 * Objet représentant un fichier converti par le service "FichierService".
 * Le fichier converti peut contenir de 0 à N lignes.
 * Chaque ligne est représentée en sortie par 1 objet "Reference" OU 1 objet "Error".
 * - 1 objet "Reference" si elle a été traitée avec succès.
 * - 1 objet "Error" si la ligne comporte une ou plusieurs données en erreur.
 */
@Getter
@Setter
@AllArgsConstructor
public class FichierConverti {
    String inputFile;
    List<Reference> references = new ArrayList<>();
    List<Error> errors = new ArrayList<>();

}
