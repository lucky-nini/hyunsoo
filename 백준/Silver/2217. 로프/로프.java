import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(numbers);

		int maxWeight = 0;
		for (int i = 0; i < N; i++) {
			maxWeight = Math.max(maxWeight, numbers[i] * (N - i));
		}

		System.out.println(maxWeight);
	}
}