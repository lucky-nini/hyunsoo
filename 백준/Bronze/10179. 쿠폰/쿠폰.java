import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			double price = Double.parseDouble(br.readLine());
			price *= 0.8;
			System.out.println("$" + String.format("%.2f", price));
		}
	}
}