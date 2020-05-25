import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Name: Maria Eskioglou
 * AEM: 3237
 * Email: eskioglou@csd.auth.gr
 * The algorithm used to implement is the Knapsack.
 */
public class Cores {
    /**
     * In this class I open the test cases and save the main core, the core from every customer and the values
     */
    public static class PointClass {
        private final ArrayList<Integer> core = new ArrayList<>(); // This arraylist stores the core from each customer.
        private final ArrayList<Double> value = new ArrayList<>(); //This arraylist stores the values.
        private int main; //Main Core.
        PointClass(String str) throws  IOException {
            Scanner scanner1 = new Scanner(new File(str));
            scanner1.useLocale(Locale.ENGLISH);
            int i = 0;
            while (scanner1.hasNextDouble()) {
                if (i == 0) {
                    main = scanner1.nextInt();
                } else if (i % 2 == 1) {
                    core.add(scanner1.nextInt());
                } else {
                    value.add((scanner1.nextDouble()));
                }
                i++;
            }
        }
        //Getters for the variables.
        public ArrayList<Integer> returnCore() { return core; }
        public ArrayList<Double> returnValue() { return value; }
        public int returnMain() { return main; }
    }
    /**
     * Bottom up way of solving this problem. Keep input sorted.
     * Otherwise temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which can be very low negative number
     * Returns Integer.MAX_VALUE - 1 if solution is not possible.
     */
    public static class Quantity {
        public void minimumVMBottomUp(int total, int[] vms, int k) {
            int[] T = new int[total + 1];
            int[] R = new int[total + 1];
            T[0] = 0;
            for (int i = 1; i <= total; i++) {
                T[i] = Integer.MAX_VALUE - 1;
                R[i] = -1;
            }
            for (int j = 0; j < vms.length; j++) {
                for (int i = 1; i <= total; i++) {
                    if (i >= vms[j]) {
                        if (T[i - vms[j]] + 1 < T[i]) {
                            T[i] = 1 + T[i - vms[j]];
                            R[i] = j;
                        }
                    }
                }
            }
            printVMCombination(R, vms, k);
        }
        /**
         * Method that prints the best combination with the smallest amount of VMs for the client examined..
         */
        public void printVMCombination(int[] R, int[] cores, int i) {
            int n1 = 0;
            int n2 = 0;
            int n7 = 0;
            int n11 = 0;
        //Keeps track of how many VMs of each VM category are needed.
            ArrayList<Integer> num = new ArrayList<>();
            int start = R.length - 1;
            System.out.print("Client " + i + ": ");
            while (start != 0) { //While-loop that fills the ArrayList mentioned above
                int j = R[start];
                num.add(cores[j]);
                start = start - cores[j];
            }
            for (Integer integer : num) {
                if (integer == 11) {
                    n11++;
                } else if (integer == 7) {
                    n7++;
                } else if (integer == 2) {
                    n2++;
                } else {
                    n1++;
                }
            }
            System.out.print(n1 + n2 + n7 + n11 + " VMs");
            System.out.print("\n");
        }
    }

    /**
     * This class is taken from: http://www.sanfoundry.com/java-program-knapsack-algorithm/
     * I changed it a bit to work with ArrayLists and not with Arrays.
     */
    public static class Knapsack {
        ArrayList<Integer> clients = new ArrayList<>(); //Here accepted clients are saved.

        /**
         *
         * @param want is the number of cores that each customer wants.
         * @param val number of cores(each customer wants)*the money they are willing to give.
         * @param mCore main core.
         * @param noOfCustomers number of customers.
         */
        public void solve(ArrayList<Integer> want, ArrayList<Double> val, int mCore, int noOfCustomers) {
            int NEGATIVE_INFINITY = Integer.MIN_VALUE;
            double[][] m = new double[noOfCustomers + 1][mCore + 1];
            double[][] sol = new double[noOfCustomers + 1][mCore + 1];
            int k = 0;
            for (int i = 1; i <= noOfCustomers; i++) {
                for (int j = 0; j <= mCore; j++) {
                    double m1 = m[i - 1][j];
                    double m2 = NEGATIVE_INFINITY;
                    if (j >= want.get(k)) {
                        m2 = (m[i - 1][j - want.get(k)] + val.get(k));
                    }
                    m[i][j] = Math.max(m1, m2); //select max of m1,m2.
                    sol[i][j] = m2 > m1 ? 1 : 0;
                }
                k++;
            }
            //make list of all items to finally select.
            int[] selected = new int[noOfCustomers + 1];
            k = noOfCustomers - 1;
            for (int n = noOfCustomers, w = mCore; n > 0; n--) {
                if (sol[n][w] != 0) {
                    selected[n] = 1;
                    w = w - want.get(k);
                } else {
                    selected[n] = 0;
                }
                k--;
            }
            for (int i = 1; i < noOfCustomers + 1; i++) {
                if (selected[i] == 1) {
                    clients.add(i);
                }
            }
        }
    }

    /**
     * In this class we count the amount of money a client gives if we accept his offer.
     */
    public static class Cost {
        public ArrayList<Double> Cost(ArrayList<Integer> ar1, ArrayList<Double> ar2) {
            ArrayList<Double> cos = new ArrayList<>();

            for (int i = 0; i < ar1.size(); i++) {
                cos.add((ar1.get(i) * ar2.get(i)));
            }
            return cos;
        }
    }

    /**
     * Here is the main that calls the other classes necessary and prints the appropriate
     * messages to fit the requested.
     */
        public static void main(String[] args) throws IOException {
        ArrayList<Integer> num; //Number of cores that every customer wants.
        ArrayList<Double> values; //Here stores the values.
        int[] cores = {1, 2, 7, 11}; //All the available cores.
            Quantity cc = new Quantity();
        PointClass Save = new PointClass(args[0]); //Calls Point with argument args[0] to get the file.
        num = Save.returnCore();
        int m = 1;
            for (Integer integer : num) {
                cc.minimumVMBottomUp(integer, cores, m);
                m++;
            }
        Cost K = new Cost();
        values = K.Cost(Save.returnCore(), Save.returnValue());
        Knapsack knap = new Knapsack();
        knap.solve(num, values, Save.returnMain(), Save.returnCore().size());
        double n = 0;
        for (int l = 0; l < knap.clients.size(); l++) {
            n = n + values.get(knap.clients.get(l) - 1);
        }
        System.out.println("Total amount: " + n);
    }
}