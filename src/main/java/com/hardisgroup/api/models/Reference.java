package com.hardisgroup.api.models;

import com.hardisgroup.api.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de l'objet "Reference".
 * Objet représentant une ligne traitée avec succès par le service "FichierService".
 * numReference: Référence métier de l'objet (exactement 10 digits).
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reference {
    String numReference;
    Color type;
    Float price;
    Integer size;

}
