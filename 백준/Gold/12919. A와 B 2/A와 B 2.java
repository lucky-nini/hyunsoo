import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String S;
	static boolean canMake = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		String T = br.readLine();

		turnT(T);

		System.out.println(canMake ? 1 : 0);
	}

	public static void turnT(String T) {
		if (canMake)
			return;

		if (S.length() >= T.length()) {
			if (S.equals(T))
				canMake = true;
			return;
		}

		if (T.charAt(T.length() - 1) == 'A') {
			turnT(T.substring(0, T.length() - 1));
		}

		if (T.charAt(0) == 'B') {
			String reversed = "";
			for (int i = T.length() - 1; i >= 0; i--) {
				reversed += T.charAt(i);
			}
			reversed = reversed.substring(0, reversed.length() - 1);
			turnT(reversed);
		}
	}
}