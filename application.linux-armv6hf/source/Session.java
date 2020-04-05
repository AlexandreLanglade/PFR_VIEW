public class Session {

    private static String password = "admin";
    private boolean admin = false;

    private Session() {
    }

    private static class SessionHolder {
        private final static Session instance = new Session();
    }

    public static Session getInstance() {
        return SessionHolder.instance;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void connexion(String mdp) {
        if (mdp.equals(password)) {
            admin = true;
        }
    }

    public void setPassword(String password) {
        Session.password = password;
    }
}
