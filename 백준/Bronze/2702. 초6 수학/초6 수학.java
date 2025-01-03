import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a > b) {
				sb.append(getLCM(a, b)).append(" ").append(getGCD(a, b)).append("\n");
			} else {
				sb.append(getLCM(b, a)).append(" ").append(getGCD(a, b)).append("\n");
			}
		}
		System.out.println(sb);
	}

	public static int getGCD(int a, int b) {
		while (b != 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}

	public static int getLCM(int a, int b) {
		return a * (b / getGCD(a, b));
	}
}