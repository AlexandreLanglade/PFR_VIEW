import java.util.ArrayList;
import java.util.List;

public class BDDescripteurs {

    private List<Descripteur> bdDescripteurs = new ArrayList<>();

    private BDDescripteurs() {
    }

    private static class BDDescripteursHolder {
        private final static BDDescripteurs instance = new BDDescripteurs();
    }

    public static BDDescripteurs getInstance() {
        return BDDescripteursHolder.instance;
    }

    public void add(Descripteur descripteur) {
        bdDescripteurs.add(descripteur);
    }

    public List<Descripteur> getListeDescripteurs() {
        return bdDescripteurs;
    }

    public List<Descripteur> getListeDescripteurs(TypeRecherche type) {
        List<Descripteur> res = new ArrayList<>();
        for (Descripteur descripteur : bdDescripteurs) {
            if (descripteur.getType() == type) {
                res.add(descripteur);
            }
        }
        return res;
    }

    public String afficherListeDescripteurs(TypeRecherche type) {
        String res = "";
        for (Descripteur descripteur : bdDescripteurs) {
            if (descripteur.getType() == type) {
                res += descripteur;
                res += "\n";
            }
        }
        return res;
    }

    public String toString() {
        String res = "";
        res += afficherListeDescripteurs(TypeRecherche.TEXTE);
        res += afficherListeDescripteurs(TypeRecherche.IMAGE);
        res += afficherListeDescripteurs(TypeRecherche.AUDIO);
        return res;
    }
}
