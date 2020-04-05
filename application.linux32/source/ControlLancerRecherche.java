public class ControlLancerRecherche {

    private ControlEnregistrerDansHistorique controlEnregistrerDansHistorique;

    public ControlLancerRecherche(ControlEnregistrerDansHistorique controlEnregistrerDansHistorique) {
        this.controlEnregistrerDansHistorique = controlEnregistrerDansHistorique;
    }

    public Recherche lancerRecherche(TypeRecherche typeRecherche, String requete) {
        Recherche recherche = new Recherche(typeRecherche, requete);
        switch (typeRecherche) {
            case TEXTE:
                recherche.addResultat("/corpus/textes/Le_TFC_en_ligue_2.xml");
                recherche.addResultat("/corpus/textes/Resume_match.xml");
                recherche.addResultat("/corpus/textes/Une_revanche_StEtienne.xml");
                break;
            
            case IMAGE:
                recherche.addResultat("/corpus/Images/LapinNoir.png");
                recherche.addResultat("/corpus/Images/LapinGris.png");
                break;

            case AUDIO:
                recherche.addResultat("/corpus/Sons/Mozart_Sonate_G_Minor");
                recherche.addResultat("/corpus/Sons/Patrick_Bruel_4_Saisons");
                break;

            default:
                break;
        }
        return recherche;
    }

    public void enregistrerDansHistorique(Recherche recherche) {
        controlEnregistrerDansHistorique.enregistrerDansHistorique(recherche);
    }

    public String afficherRecherche(Recherche recherche) {
        return recherche.toString();
    }

}
