import java.util.ArrayList;

public class Knapsack {
    ArrayList<Integer> cl = new ArrayList<Integer>();
    public void solve(ArrayList<Integer> wt, ArrayList<Double> val, int W, int N) {
        int NEGATIVE_INFINITY = Integer.MIN_VALUE;
        double[][] m = new double[N + 1][W + 1];
        double[][] sol = new double[N + 1][W + 1];
        int k = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= W; j++) {
                double m1 = m[i - 1][j];
                double m2 = NEGATIVE_INFINITY;
                if (j >= wt.get(k)) {
                    m2 = (m[i - 1][j - wt.get(k)] + val.get(k));
                }
                m[i][j] = Math.max(m1, m2);
                sol[i][j] = m2 > m1 ? 1 : 0;
            }
            k++;
        }
        int[] selected = new int[N + 1];
        k = N - 1;
        for (int n = N, w = W; n > 0; n--) {
            if (sol[n][w] != 0) {
                selected[n] = 1;
                w = w - wt.get(k);
            } else {
                selected[n] = 0;
            }
            k--;
        }
        for (int i = 1; i < N + 1; i++) {
            if (selected[i] == 1) {
                cl.add(i);
            }
        }
    }
}