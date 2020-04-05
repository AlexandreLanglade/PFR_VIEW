import java.util.List;

public class ControlVisualiserHistorique {

    private Historique historique = Historique.getInstance();

    public String visualiserHistorique() {
        return historique.toString();
    }
    
    public List<Recherche> get() {
      return historique.get();
    }
}
