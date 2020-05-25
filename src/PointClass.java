import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PointClass {

    private ArrayList<Integer> pos1 = new ArrayList<Integer>();
    private ArrayList<Double> pos2 = new ArrayList<Double>();
    private int pirines;

    PointClass() throws  IOException {

        Scanner scanner1 = new Scanner(new File("/home/eski/IdeaProjects/Knapsack/src/test3.txt"));
        scanner1.useLocale(Locale.ENGLISH);
        int i = 0;
        while (scanner1.hasNextDouble()) {
            if (i == 0) {
                pirines = scanner1.nextInt();
            } else if (i % 2 == 1) {
                pos1.add(scanner1.nextInt());
            } else {
                pos2.add((scanner1.nextDouble()));
            }
            i++;
        }
    }
    public ArrayList<Integer> returnAll1() {
        return pos1;
    }
    public ArrayList<Double> returnAll2() {
        return pos2;
    }
    public int returnPirines() {
        return pirines;
    }
}