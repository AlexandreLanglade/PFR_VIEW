import java.util.Random;

public class ControlLancerIndexation {

    private BDDescripteurs bdDescripteurs = BDDescripteurs.getInstance();
    private ControlVerifierID controlVerifierID;

    public ControlLancerIndexation(ControlVerifierID controlVerifierID) {
        this.controlVerifierID = controlVerifierID;
    }

    public boolean verifierID() {
        return controlVerifierID.verifierID();
    }

    public void lancerIndexation() {
        Random r = new Random();

        Descripteur texte1 = new Descripteur(TypeRecherche.TEXTE);
        texte1.addInfoCorps("Fichier : /corpus/textes/Resume_match.xml");
        texte1.addInfoCorps("foot 13");
        texte1.addInfoCorps("ballon 5");
        texte1.addInfoCorps("but 2");
        bdDescripteurs.add(texte1);

        Descripteur texte2 = new Descripteur(TypeRecherche.TEXTE);
        texte2.addInfoCorps("Fichier : /corpus/textes/orchestre.xml");
        texte2.addInfoCorps("musique 11");
        texte2.addInfoCorps("trompette 7");
        texte2.addInfoCorps("pouetpouet 4");
        texte2.addInfoCorps("concert 2");
        bdDescripteurs.add(texte2);

        Descripteur image1 = new Descripteur(TypeRecherche.IMAGE);
        image1.addInfoCorps("Fichier : /corpus/Images/LapinNoir.png");
        image1.addInfoCorps("1 1 3");
        for (int i = 0; i < 20; i++) {
            image1.addInfoCorps(r.nextInt(1000));
        }
        bdDescripteurs.add(image1);

        Descripteur audio1 = new Descripteur(TypeRecherche.AUDIO);
        audio1.addInfoCorps("Fichier : /corpus/Sons/Mozart_Sonate_G_Minor");
        for (int i = 0; i < 20; i++) {
            audio1.addInfoCorps(r.nextInt(1500));
        }
        bdDescripteurs.add(audio1);

    }
    
}
