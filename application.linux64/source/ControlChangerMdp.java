public class ControlChangerMdp {

    private Session session = Session.getInstance();
    private ControlVerifierID controlVerifierID;

    public ControlChangerMdp(ControlVerifierID controlVerifierID) {
        this.controlVerifierID = controlVerifierID;
    }

    public boolean verifierID() {
        return controlVerifierID.verifierID();
    }

    public void changerMdp(String mdp) {
        session.setPassword(mdp);
    }
}
