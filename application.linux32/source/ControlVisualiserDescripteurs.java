public class ControlVisualiserDescripteurs {

    private BDDescripteurs bdDescripteurs = BDDescripteurs.getInstance();
    private ControlVerifierID controlVerifierID;

    public ControlVisualiserDescripteurs(ControlVerifierID controlVerifierID) {
        this.controlVerifierID = controlVerifierID;
    }

    public boolean verifierID() {
        return controlVerifierID.verifierID();
    }

    public String visualiserDescripteurs() {
        return bdDescripteurs.toString();
    }

    public String visualiserDescripteurs(TypeRecherche  type) {
        return bdDescripteurs.afficherListeDescripteurs(type);
    }
}
