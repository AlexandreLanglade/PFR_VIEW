public class ControlVerifierID {

    private Session session = Session.getInstance();

    public boolean verifierID() {
        return session.isAdmin();
    }
}
