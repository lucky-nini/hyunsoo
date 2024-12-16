class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        int LCM = arr[0];
        
        for (int i=1; i<arr.length; i++) {
            LCM = LCM > arr[i] ? getLCM(LCM, arr[i]) : getLCM(arr[i], LCM);
        }
        
        return LCM;
    }
    
    public static int getGCD(int n1, int n2) {
        if (n1 % n2 == 0) {
            return n2;
        }
        return getGCD(n2, n1 % n2);
    }
    
    public static int getLCM(int n1, int n2) {
        int GCD = getGCD(n1, n2);
        
        // 최소공배수 구하기
        return (n1 * n2) / GCD;
    }
}