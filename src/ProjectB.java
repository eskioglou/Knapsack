import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectB {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> meg = new ArrayList<Integer>();
        ArrayList<Double> meg1 = new ArrayList<Double>();
        Map<Integer, Integer> map = new HashMap<>();
        int coins[] = {1, 2, 7, 11};
        Plithos cc = new Plithos();
        PointClass Save = new PointClass();
        meg = Save.returnAll1();
        int m = 1;
        for (int i = 0; i < meg.size(); i++) {

            cc.minimumCoinBottomUp(meg.get(i), coins, m);
            m++;

        }
        Cost K = new Cost();
        meg1 = K.Cost(Save.returnAll1(), Save.returnAll2());
        Knapsack Ypol = new Knapsack();
        Ypol.solve(meg, meg1, Save.returnPirines(), Save.returnAll1().size());
        double n = 0;
        for (int l = 0; l < Ypol.cl.size(); l++) {
            n = n + meg1.get(Ypol.cl.get(l) - 1);
        }
        System.out.println("Total amount : " + n);
    }
}