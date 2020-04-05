import java.util.ArrayList;
import java.util.List;

public class Descripteur {

    private TypeRecherche type;
    private List<String> corps = new ArrayList<>();

    public Descripteur(TypeRecherche type) {
        this.type = type;
    }

    public void addInfoCorps(String s) {
        corps.add(s);
    }

    public void addInfoCorps(int i) {
        corps.add(String.valueOf(i));
    }

    public TypeRecherche getType() {
        return type;
    }

    @Override
    public String toString() {
        String res =  "Descripteur : " + type + "\n";
        for (String info : corps) {
            res = res + info + "\n";
        }
        return res;
    }

}
