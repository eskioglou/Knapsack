import java.util.ArrayList;
public class Cost {
    public ArrayList<Double> Cost(ArrayList<Integer> ar1, ArrayList<Double> ar2) {
        ArrayList<Double> kostos = new ArrayList<Double>();

        for (int i = 0; i < ar1.size(); i++) {
            kostos.add((ar1.get(i) * ar2.get(i)));
        }
        return kostos;
    }
}