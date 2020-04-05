import java.util.ArrayList;
import java.util.List;

public class Recherche {

    private TypeRecherche typeRecherche;
    private String requete;
    private List<String> resultats = new ArrayList<>();

    public Recherche(TypeRecherche typeRecherche, String requete) {
        this.typeRecherche = typeRecherche;
        this.requete = requete;
    }

    public String getRequete() {
        return requete;
    }

    public List<String> getResultats() {
        return resultats;
    }

    public void addResultat(String resultat) {
        resultats.add(resultat);
    }

    @Override
    public String toString() {
        String res = "Type : " + typeRecherche + ", requete : " + requete + "\n  Résultats :";
        for (String resultat : resultats) {
            res = res + "\n     " + resultat;
        }
        return res;
    }

}
