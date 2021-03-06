package com.hardisgroup.api.services;

import com.hardisgroup.api.enums.Color;
import com.hardisgroup.api.enums.FormatFichierEnSortie;
import com.hardisgroup.api.exceptions.IOExceptionApi;
import com.hardisgroup.api.models.Error;
import com.hardisgroup.api.models.FichierConverti;
import com.hardisgroup.api.models.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implémentation de l'interface "FichierService".
 * Regroupe tous les services ayant trait aux fichiers.
 */
@Slf4j
@Service
public class FichierServiceImpl implements FichierService {

    // Pattern Bloc n°1: Integer (exactement 10 chiffres).
    private String patternBloc1 = "(\\d{10})";
    // Pattern Bloc n°2: Code couleur (exactement 1 lettre).
    private String patternBloc2 = this.construirePatternCouleurs();
    // Pattern Bloc n°3: Nombre de type Float (valeur max non-spécifiée).
    private String patternBloc3 = "(\\d+\\.\\d\\d?)";
    // Pattern Bloc n°4: Nombre de type Integer (valeur max non-spécifiée).
    private final String patternBloc4 = "(\\d{1,5})";
    // Pattern de ligne: Regroupe les patterns 1/2/3/4 dans une même pattern (chaque bloc est séparé par un ";")
    private final String patternLigne = patternBloc1 + ";" + patternBloc2 + ";" + patternBloc3 + ";" + patternBloc4;

    /**
     * Méthode de test.
     * @return
     */
    public String testerApplication() {
        return "Hello World!";
    }

    /**
     * Méthode permettant de traiter un fichier. Les fichiers traitées contiennent 0 à N lignes à convertir
     * en autant d'objet "Reference" (en cas de succès) ou "Error" (si la ligne contient des données en erreur).
     *
     * @param urlEntrante:           Url du fichier à traiter.
     * @param formatFichierEnSortie: Format attendu en sortie (XML, JSON).
     * @param urlSortante:           Url du fichier une fois traité.
     */
    @Override
    public FichierConverti convertirFichierEntrant(
            String urlEntrante, FormatFichierEnSortie formatFichierEnSortie, String urlSortante) throws IOExceptionApi {

        File fichierEntrant = new File(urlEntrante);
        List<Reference> references = new ArrayList<>();
        List<Error> errors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichierEntrant))) {
            String ligne;
            // L'index débute à 1 pour référencer les lignes du fichier d'un point de vue "humain".
            // Exemple: La ligne 1 doit avoir "Index=1", et non "Index=0".
            int indexLigne = 1;

            while ((ligne = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(patternLigne);
                Matcher matcher = pattern.matcher(ligne);

                if (matcher.find()) {
                    Reference reference = this.traiterLigneEnSucces(ligne);
                    references.add(reference);
                } else {
                    Error error = this.traiterLigneEnErreur(ligne, indexLigne);
                    errors.add(error);
                }
                indexLigne++;
            }

        } catch (IOException ex) {
            throw new IOExceptionApi(HttpStatus.BAD_REQUEST, "fichier illisible ou corrompu.", ex.getMessage());
        }

        // Accès au nom du fichier via "File.getName()" (accès au fichier lui-même depuis un path)
        return new FichierConverti(fichierEntrant.getName(), references, errors);

    }



    /**
     * @param ligneEntrante Ligne en succès à traiter.
     * @return Un objet "Reference" correspondant à la ligne en succès traitée.
     */
    private Reference traiterLigneEnSucces(String ligneEntrante) {
        Reference reference = new Reference();
        Pattern pattern = Pattern.compile(patternLigne);
        Matcher matcher = pattern.matcher(ligneEntrante);

        if (matcher.find()) {
            reference.setNumReference(matcher.group(1));
            reference.setType(Color.valueOf(matcher.group(2)));
            reference.setPrice(Float.parseFloat(matcher.group(3)));
            reference.setSize(Integer.parseInt(matcher.group(4)));
        }

        return reference;
    }

    /**
     * Méthode permettant de convertir une ligne en erreur en nobjet "Error".
     *
     * @param ligneEntrante Ligne en erreur à traiter.
     * @param indexLigne    Index de la ligne dans le fichier initial.
     * @return Un objet "Error" correspondant à la ligne en erreur traitée.
     */
    private Error traiterLigneEnErreur(String ligneEntrante, Integer indexLigne) {
        return new Error(indexLigne, "Donnée incorrecte", ligneEntrante);
    }

    /**
     * Concatène une string à partir de toutes les valeurs disponibles de l'énumération "Color".
     *
     * @return Une string composée de tous les codes autorisés de couleur.
     */
    private String construirePatternCouleurs() {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(Color.values()).forEach(color -> stringBuilder.append(color.name()));
        return "(" + "[" + stringBuilder.toString() + "]" + ")";
    }
}
