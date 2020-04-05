public class ControlChangerConfiguration {

    private Configuration configuration = Configuration.getInstance();
    private ControlVerifierID controlVerifierID;

    public ControlChangerConfiguration(ControlVerifierID controlVerifierID) {
        this.controlVerifierID = controlVerifierID;
    }

    public boolean verifierID() {
        return controlVerifierID.verifierID();
    }

    public void changerConfiguration(int param, int valeur) {
        switch (param) {
            case 1:
                configuration.setValeurLimiteTexte(valeur);
                break;

            case 2:
                configuration.setSeuilTexte(valeur);
                break;

            case 3:
                configuration.setNombreBitsImage(valeur);
                break;
            
            case 4:
                configuration.setNombreEchantillonsAudio(valeur);
                break;
            
            case 5:
                configuration.setNombreIntervallesAudio(valeur);
                break;
        
            default:
                break;
        }
    }

    public String afficherConfig() {
        return configuration.toString();
    }

}
