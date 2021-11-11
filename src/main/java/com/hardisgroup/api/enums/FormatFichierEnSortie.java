package com.hardisgroup.api.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Enumération: Format de sortie disponible pour les fichiers traités par le service "FichierService".
 */
@Getter
@NoArgsConstructor
public enum FormatFichierEnSortie {
    XML,
    JSON;
}
