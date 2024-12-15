import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] U = new int[N];
        for (int i = 0; i < N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(U);

        Set<Integer> sumSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sumSet.add(U[i] + U[j]);
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (sumSet.contains(U[i] - U[j])) {
                    System.out.println(U[i]);
                    return;
                }
            }
        }
    }
}