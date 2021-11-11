package com.hardisgroup.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de l'objet "Error".
 * Objet représentant une ligne n'ayant pu être traitée par le service "FichierService" (données en erreur).
 * Line: Numéro de ligne de la donnée en erreur dans le fichier initial.
 * Message: Message d'erreur indiquant le type d'erreur bloquant le traitement. Ex: "Incorrect value for color".
 * Value: Valeur (contenu) de la ligne en erreur.
 */
@Getter
@Setter
@AllArgsConstructor
public class Error {
    Integer line;
    String message;
    String value;
}
