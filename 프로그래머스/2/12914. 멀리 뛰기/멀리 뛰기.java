class Solution {
    static long[] jump;

    public long solution(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        jump = new long[n + 1];
        jump[1] = 1 % 1234567;
        jump[2] = 2 % 1234567;

        return getJump(n);
    }

    static public long getJump(int n) {
        if (jump[n] == 0) {
            jump[n] = (getJump(n - 1) + getJump(n - 2)) % 1234567;
        }
        return jump[n];
    }
}
