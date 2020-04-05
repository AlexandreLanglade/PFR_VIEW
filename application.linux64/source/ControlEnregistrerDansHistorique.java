public class ControlEnregistrerDansHistorique {

    private Historique historique = Historique.getInstance();

    public void enregistrerDansHistorique(Recherche recherche) {
        historique.add(recherche);
    }
}
