public class ControlEffacerHistorique {

    private Historique historique = Historique.getInstance();
    private ControlVerifierID controlVerifierID;

    public ControlEffacerHistorique(ControlVerifierID controlVerifierID) {
        this.controlVerifierID = controlVerifierID;
    }

    public boolean verifierID() {
        return controlVerifierID.verifierID();
    }

    public void effacerHistorique() {
        historique.clear();
    }
    
}
