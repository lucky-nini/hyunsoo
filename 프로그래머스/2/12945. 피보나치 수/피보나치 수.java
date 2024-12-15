class Solution {
    static int[] fibo;
    public int solution(int n) {
        int answer = 0;
        
        fibo = new int[n+1];
        fibo[0] = 0;
        fibo[1] = 1;
        
        return findFibo(n);
    }
    
    public int findFibo(int n) {
        if (n!=0 && fibo[n]==0) {
            fibo[n] = (findFibo(n-1) + findFibo(n-2)) % 1234567;
        }
        return fibo[n];
    }
}