import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			long N = Long.parseLong(br.readLine());
			sb.append(toTrinary(N)).append("\n");
		}
		System.out.println(sb);
	}

	public static long toTrinary(long n) {
		long answer = 0;
		long pow = 1;

		while (n > 0) {
			if (n % 2 == 1) {
				answer += pow;
			}
			pow *= 3;
			n /= 2;
		}

		return answer;
	}
}