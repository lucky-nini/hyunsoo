import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while (true) {
			String s = sc.next();
			if (s.equals("end"))
				break;

			boolean isValid = true;

			boolean hasVowels = false;
			boolean prevIsVowel = false;
			int vCnt = 1;
			char prevChar = s.charAt(0);

			if (isVowel(s.charAt(0))) {
				hasVowels = true;
				prevIsVowel = true;
			}

			for (int i = 1; i < s.length(); i++) {
				// 1. 모음 포함
				if (!hasVowels && isVowel(s.charAt(i))) {
					hasVowels = true;
				}
				// 2. 모음 또는 자음 3개 연속 X
				if (isVowel(s.charAt(i)) == prevIsVowel) {
					vCnt++;
				} else {
					vCnt = 1;
					prevIsVowel = !prevIsVowel;
				}

				if (vCnt == 3) {
					isValid = false;
					break;
				}
				// 3. 같은 글자 두번 연속 X, ee와 oo는 허용
				if (prevChar != 'e' && prevChar != 'o' && s.charAt(i) == prevChar) {
					isValid = false;
					break;
				} else {
					prevChar = s.charAt(i);
				}
			}

			if (!hasVowels)
				isValid = false;

			if (!isValid) {
				sb.append("<" + s + "> is not acceptable.\n");
			} else {
				sb.append("<" + s + "> is acceptable.\n");
			}
		}
		System.out.println(sb);
	}

	public static boolean isVowel(char c) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
			return true;
		return false;
	}
}