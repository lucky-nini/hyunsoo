import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        long n = Long.parseLong(N);

        if (isPrime(n)) {
            String reversedString = reverseAndValidate(N);
            if (reversedString == null) {
                System.out.println("no");
                return;
            }

            long reversedNumber = Long.parseLong(reversedString);

            if (isPrime(reversedNumber)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        } else {
            System.out.println("no");
        }
    }

    static public boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static public String reverseAndValidate(String N) {
        StringBuilder reversed = new StringBuilder();
        for (int i = N.length() - 1; i >= 0; i--) {
            char digit = N.charAt(i);
            switch (digit) {
                case '0':
                case '1':
                case '2':
                case '5':
                case '8':
                    reversed.append(digit);
                    break;
                case '6':
                    reversed.append('9');
                    break;
                case '9':
                    reversed.append('6');
                    break;
                default: 
                    return null;
            }
        }
        return reversed.toString();
    }
}