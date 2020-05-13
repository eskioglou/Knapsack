import java.util.ArrayList;

public class Plithos {
    public void minimumCoinBottomUp(int total, int coins[], int k) {
        int T[] = new int[total + 1];
        int R[] = new int[total + 1];
        T[0] = 0;
        for (int i = 1; i <= total; i++) {
            T[i] = Integer.MAX_VALUE - 1;
            R[i] = -1;
        }
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= total; i++) {
                if (i >= coins[j]) {
                    if (T[i - coins[j]] + 1 < T[i]) {
                        T[i] = 1 + T[i - coins[j]];
                        R[i] = j;
                    }
                }
            }
        }
        printCoinCombination(R, coins, k);
    }

    public void printCoinCombination(int R[], int coins[], int i) {
        int n1 = 0;
        int n2 = 0;
        int n7 = 0;
        int n11 = 0;

        ArrayList<Integer> num = new ArrayList<Integer>();
        int start = R.length - 1;
        System.out.print("Client " + i + ": ");
        while (start != 0) {
            int j = R[start];
            num.add(coins[j]);
            start = start - coins[j];
        }
        for (int k = 0; k < num.size(); k++) {
            if (num.get(k) == 11) {
                n11++;
            } else if (num.get(k) == 7) {
                n7++;

            } else if (num.get(k) == 2) {
                n2++;
            } else {
                n1++;
            }

        }
        System.out.print(n1+n2+n7+n11 +" VMs");
           System.out.print("\n");
    }
}