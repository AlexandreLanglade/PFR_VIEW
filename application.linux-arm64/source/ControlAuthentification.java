public class ControlAuthentification {

    private Session session = Session.getInstance();
    private ControlVerifierID controlVerifierID;

    public ControlAuthentification(ControlVerifierID controlVerifierID) {
        this.controlVerifierID = controlVerifierID;
    }

    public void authentification(String mdp) {
        session.connexion(mdp);
    }

    public boolean verifierID() {
        return controlVerifierID.verifierID();
    }
}
